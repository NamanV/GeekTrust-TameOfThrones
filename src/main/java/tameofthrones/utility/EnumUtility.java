package tameofthrones.utility;

import java.util.Objects;

public class EnumUtility {
    public static <T extends Enum<T>> T loadUpperCase(String input, Class<T> c, T defaultVal) {
        if (Objects.isNull(input) || input.isEmpty()) {
            return defaultVal;
        }
        return load(input.toUpperCase(), c, defaultVal);
    }

    public static <T extends Enum<T>> T load(String input, Class<T> c, T defaultVal) {
        if (Objects.nonNull(input) && !input.isEmpty()) {
            try {
                return Enum.valueOf(c, input);
            } catch (IllegalArgumentException ex) {
            }
        }
        return defaultVal;
    }
}
