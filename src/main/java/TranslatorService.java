import java.util.HashMap;
import java.util.Map;

/*
Mock translator, returns a map with the translations keeping the same keys.
Words or symbols that do not need to be translated ej: blank spaces, are ignored and
not added to the translated map.
*/
public enum TranslatorService implements ITranslatorService {

    INSTANCE;

    public Map<String, String> translate(Map<String, String> toBeTranslatedList) {
        String hallo = "Hallooooo";
        String welt = "Welt";
        String potato = "Kartoffel";
        Map<String, String> result = new HashMap<>();
        toBeTranslatedList.forEach((k, v) ->
        {
            if (v.equalsIgnoreCase("hello")) {
                result.put(k, hallo);
            }

            if (v.equalsIgnoreCase("world")) {
                result.put(k, welt);
            }
            if (v.equalsIgnoreCase("potato")) {
                result.put(k, potato);
            }

        });
        return result;
    }
}
