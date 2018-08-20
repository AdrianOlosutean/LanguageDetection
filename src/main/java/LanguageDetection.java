import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;
import prediction.LanguageClassifier;
import prediction.LanguageMatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageDetection {


    public static final String PATH = "src/resources/";

    public static void main(String[] args) throws IOException {
        final SparkConf conf = new SparkConf()
                .setAppName("sada")
                .setMaster("local[*]");

        final SparkSession spark = new SparkSession.Builder()
                .config(conf)
                .getOrCreate();
        LanguageWordProcessor italian = new LanguageWordProcessor("italian");
        LanguageWordProcessor spanish = new LanguageWordProcessor("spanish");
        LanguageWordProcessor portuguese = new LanguageWordProcessor("portuguese");

        List<LanguageWordProcessor> allLanguages = new ArrayList<>();
        allLanguages.add(italian);
        allLanguages.add(spanish);
        allLanguages.add(portuguese);

        List<LanguageMatcher> matchers = allLanguages.stream()
                .map(langProcessor -> {
                    List<String> languageWords = langProcessor.getAllLanguageWords();
                    Dataset<String> dataset = spark.createDataset(languageWords, Encoders.STRING());
                    Dataset<Row> mostFrequentWords = dataset.groupBy(new Column("value")).count().sort(new Column("count").desc())
                            .limit(languageWords.size() / 20);
                    return new LanguageMatcher(langProcessor.getLanguageName(), mostFrequentWords);
                }).collect(Collectors.toList());

        LanguageClassifier languageClassifier = new LanguageClassifier(matchers);
        languageClassifier.predictLanguage("/test/spanish/cadiz.txt");
    }
}
