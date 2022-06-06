package se.callista.blog.service.multitenancy.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import se.callista.blog.service.multitenancy.util.TenantContext;

import java.util.UUID;

import static se.callista.blog.service.util.UUIDUtils.isValid;

@Slf4j
@Component
public class TenantInterceptor implements WebRequestInterceptor {

    public static final String X_TENANT_ID = "X-TENANT-ID";

    @Override
    public void preHandle(WebRequest request) throws Exception {

        String errorMsg;
        String logErrorMsg;

        if (request.getHeader(X_TENANT_ID) == null){
            errorMsg = "Tenant id not found";
            logErrorMsg = errorMsg + ": {}";
            log.info( logErrorMsg, request.getHeaderNames());
            throw new IllegalArgumentException(errorMsg);
        }

        String strUUID = request.getHeader(X_TENANT_ID);
        if (!isValid(strUUID)){
            errorMsg = "Tenant ID invalid";
            logErrorMsg = errorMsg + ": {}";
            log.info(logErrorMsg, request.getHeader(X_TENANT_ID));
            throw new IllegalArgumentException(errorMsg);
        }

        UUID tenantId = UUID.fromString(request.getHeader(X_TENANT_ID));
        TenantContext.setTenantId(tenantId);
    }

    @Override
    public void postHandle(@NonNull WebRequest request, ModelMap model) throws Exception {
        TenantContext.clear();
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        //do nothing
    }

}
