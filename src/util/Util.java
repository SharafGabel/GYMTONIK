package util;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by axel on 24/02/15.
 */
public class Util {
    public static String getCanonical(String string) {
        String normalizedString = Normalizer.normalize(string, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        normalizedString = pattern.matcher(normalizedString).replaceAll("");

        return normalizedString.replaceAll("[^A-Za-z0-9]", "").toLowerCase().trim();
    }
}
