package prediction;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.stream.Stream;

public class LanguageMatcher {
    private String name;
    private Dataset<Row> mostFrequentWords;

    public LanguageMatcher(String name, Dataset<Row> mostFrequentWords) {
        this.name = name;
        this.mostFrequentWords = mostFrequentWords;
    }

    public int matchBookCounts(Stream<String> bookWordStream) {
        return (int) mostFrequentWords
                .filter(new Column("value").isin(bookWordStream.toArray(String[]::new))).count();
    }

    public String getName() {
        return name;
    }
}
