import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Rodrigo on 8/3/16.
 */
public class ParserServiceTest {

    @Test
    public void testText1() throws Exception {
        String expectedResult = "b12:16:s9:Hallooooob4:7:s1: s4:Welt";
        String result;
        ParserService parserService = ParserServiceFactory.createMyClass();
        List<String> text;
        URL resource = ParserServiceTest.class.getResource("text.txt");
        text = Files.lines(Paths.get(resource.toURI()))
                .distinct()
                .collect(Collectors.toList());
        result = parserService.parse(text);
        assertThat(result, is(expectedResult));
        System.out.println(result);

    }

    @Test
    public void testText2() throws Exception {
        String expectedResult = "b12:35:s9:Hallooooob4:25:s1: b7:12:s4:Welts9:Kartoffel";
        String result;
        ParserService parserService = ParserServiceFactory.createMyClass();
        List<String> text;
        URL resource = ParserServiceTest.class.getResource("text2.txt");
        text = Files.lines(Paths.get(resource.toURI()))
                .distinct()
                .collect(Collectors.toList());
        result = parserService.parse(text);
        assertThat(result, is(expectedResult));
        System.out.println(result);

    }

    @Test
    public void testText3() throws Exception {
        String expectedResult = "b12:7:s9:Hallooooos4:Welt";
        String result;
        ParserService parserService = ParserServiceFactory.createMyClass();
        List<String> text;
        URL resource = ParserServiceTest.class.getResource("text3.txt");
        text = Files.lines(Paths.get(resource.toURI()))
                .distinct()
                .collect(Collectors.toList());
        result = parserService.parse(text);
        assertThat(result, is(expectedResult));
        System.out.println(result);

    }

    @Test
    public void testText4() throws Exception {
        String expectedResult = "b16:16:b4:7:s1: s4:Weltb4:7:s1: s4:Welt";
        String result;
        ParserService parserService = ParserServiceFactory.createMyClass();
        List<String> text;
        URL resource = ParserServiceTest.class.getResource("text4.txt");
        text = Files.lines(Paths.get(resource.toURI()))
                .distinct()
                .collect(Collectors.toList());
        result = parserService.parse(text);
        assertThat(result, is(expectedResult));
        System.out.println(result);

    }

}

