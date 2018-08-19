import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LanguageLearner {
    public static final String LEARNING_PATH = "src/main/resources/train";


    private final String languageName;

    public LanguageLearner(String name) {
        this.languageName = name;
    }

    public List<String> getAllLanguageWords() {
        File[] files = new File("src/main/resources/" + languageName).listFiles();
        return Arrays.stream(files)
                .map(File::getName)
                .map(r -> new BookWordReader("src/main/resources/" +languageName +"/" + r).getBookWordStream())
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .collect(Collectors.toList());
    }



}
