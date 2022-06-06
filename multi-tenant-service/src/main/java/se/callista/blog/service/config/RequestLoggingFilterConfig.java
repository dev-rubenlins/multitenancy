package se.callista.blog.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * This filter requires that log level is set to DEBUG, in order to log http requests
 * To enable DEBUG mode, add the line below to application.properties:
 *
 * logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=DEBUG
 */
@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
                = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("HTTP REQUEST: ");
        return filter;
    }
}