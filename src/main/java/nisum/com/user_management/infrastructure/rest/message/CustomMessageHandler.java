package nisum.com.user_management.infrastructure.rest.message;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;

public final class CustomMessageHandler {

    private static final CustomMessageHandler INSTANCE = new CustomMessageHandler();

    private final Map<String, String> messages = new HashMap<>();

    public static CustomMessageHandler getInstance() {
        return INSTANCE;
    }

    public String resolve(String key, Object... args) {
        String message = messages.get(key);
        if (args != null) {
            return String.format(StringUtils.defaultString(message), args);
        }
        return message;
    }

    public void loadProps(InputStream inputStream) throws IOException {
        ResourceBundle resources = new PropertyResourceBundle(inputStream);
        Enumeration<String> keys = resources.getKeys();
        String key;
        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            messages.put(key, resources.getString(key));
        }
    }

}
