import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class BookWordReader {


    private String path;

    public BookWordReader(String name) {
        this.path = name;
    }

    public Stream<String> getBookWordStream() {
        try {
            return Files.readAllLines(Paths.get(path))
                    .parallelStream()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .parallel()
                    .filter(w -> w.matches("\\w+") && w.length() > 2)
                    .map(String::toLowerCase);
        } catch (IOException e) {
            return Stream.empty();
        }
    }
}
