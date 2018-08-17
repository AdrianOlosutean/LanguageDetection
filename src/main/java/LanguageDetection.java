import entities.Language;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

public class LanguageDetection {


    public static final String PATH = "src/resources/";

    public static void main(String[] args) {
        final SparkConf conf = new SparkConf()
                .setAppName("sada")
                .setMaster("local[*]");

        final SparkSession spark = new SparkSession.Builder()
                .config(conf)
                .getOrCreate();

        Language italian = new Language("train/italian", spark);
        Language spanish = new Language("train/spanish", spark);
        Language portuguese = new Language("train/portuguese", spark);

        List<Language> allLanguages  = new ArrayList<>();
        allLanguages.add(italian);
        allLanguages.add(spanish);
        allLanguages.add(portuguese);

        for (Language language : allLanguages) {
            language.learn();
        }
    }
}
