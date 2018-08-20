package prediction;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;


public class LanguageMatcher {
    private String name;
    private Dataset<Row> mostFrequentWords;

    public LanguageMatcher(String name, Dataset<Row> mostFrequentWords) {
        this.name = name;
        this.mostFrequentWords = mostFrequentWords;
    }

    int matchBookCounts(Dataset<String> bookWords) {
        return (int) mostFrequentWords.join(bookWords,
                mostFrequentWords.col("value").equalTo(bookWords.col("value"))).count();
    }

    public String getName() {
        return name;
    }
}
