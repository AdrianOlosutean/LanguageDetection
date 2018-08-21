import preprocessing.BookWordReader;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LanguageWordProcessor {

    private final String languageName;

    public LanguageWordProcessor(String name) {
        this.languageName = name;
    }

    public List<String> getAllLanguageWords() {
        File[] files = new File("src/main/resources/train/" + languageName).listFiles();
        return Arrays.stream(files)
                .map(File::getName)
                .map(r -> new BookWordReader("src/main/resources/train/" + languageName + "/" + r).getBookWordStream())
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .collect(Collectors.toList());
    }

    public String getLanguageName() {
        return languageName;
    }

}
