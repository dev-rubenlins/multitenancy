package se.callista.blog.service.util;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class UUIDUtils {

    public static boolean isValid(@NotBlank String strUUID) {
        try {
            UUID.fromString(strUUID);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
