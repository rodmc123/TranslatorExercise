import java.util.Map;

/**
 * Created by Rodrigo on 8/3/16.
 */
public interface ITranslatorService {
    Map<String, String> translate(Map<String, String> toBeTranslated);
}
