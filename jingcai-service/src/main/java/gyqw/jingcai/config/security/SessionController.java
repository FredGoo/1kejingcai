package gyqw.jingcai.config.security;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class SessionController extends BaseHandler {

    public static JSONObject getJSON(Authentication authentication) {
        JSONObject object = new JSONObject();

        if (authentication != null) {
            String name = authentication.getName();
            boolean isLogged = authentication.isAuthenticated() && !"anonymousUser".equals(name);
            if (isLogged) {
                object.put("userName", name);
                object.put("isLogged", true);
                object.put("userRoles", getUserRoles(authentication));
                return object;
            }
        }

        object.put("userName", "Guest");
        object.put("isLogged", false);
        object.put("userRoles", new JSONArray());
        return object;
    }

    private static JSONArray getUserRoles(Authentication authentication) {
        JSONArray userRoles = new JSONArray();
        authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(userRoles::put);
        return userRoles;
    }

}
