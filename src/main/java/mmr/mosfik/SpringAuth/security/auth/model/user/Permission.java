package mmr.mosfik.SpringAuth.security.auth.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    // Admin Permission Authority
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),

    // Manager Permission Authority
    MANAGER_READ("management:read"),
    MANAGER_CREATE("management:create"),
    MANAGER_UPDATE("management:update"),
    MANAGER_DELETE("management:delete"),

    // User Permission Authority
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete")
    ;

    private final String permission;
}
