/**
 * Created by Rodrigo on 8/3/16.
 */
public class ParserServiceFactory {
    public static ParserService createMyClass() {
       return new ParserService(TranslatorService.INSTANCE);
    }
}
