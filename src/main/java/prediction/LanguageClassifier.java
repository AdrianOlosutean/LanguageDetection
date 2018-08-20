package prediction;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import preprocessing.BookWordReader;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageClassifier {


    private List<LanguageMatcher> matchers;
    private SparkSession spark;

    public LanguageClassifier(List<LanguageMatcher> matchers, SparkSession spark) {
        this.matchers = matchers;
        this.spark = spark;
    }

    public String predictLanguage(String bookName) {
        BookWordReader bookWordReader = new BookWordReader(bookName);
        Dataset<String> bookWords = spark.createDataset(bookWordReader.getBookWordStream().collect(Collectors.toList()), Encoders.STRING());

        return matchers.stream()
                .map(matcher -> new MatchingResult(matcher.getName(), matcher.matchBookCounts(bookWords)))
                .max(Comparator.comparingInt(MatchingResult::getMatchBookCounts))
                .orElse(new MatchingResult("none", 0))
                .getLanguageName();
    }
}
