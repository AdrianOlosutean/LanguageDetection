import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class LanguageDetection {


    public static final String PATH = "src/resources/";

    public static void main(String[] args) {
        final SparkConf conf = new SparkConf()
                .setAppName("sada")
                .setMaster("local[*]");

        final SparkSession spark = new SparkSession.Builder()
                .config(conf)
                .getOrCreate();

        Dataset<String> ds = spark.read().textFile("src/main/resources/train/spanish/trafalgar.txt");
        Dataset<Object> objectDataset = ds.flatMap(r -> r.split(""));


        /*LanguageLearner italian = new LanguageLearner("train/italian", spark);
        LanguageLearner spanish = new LanguageLearner("train/spanish", spark);
        LanguageLearner portuguese = new LanguageLearner("train/portuguese", spark);

        List<LanguageLearner> allLanguages = new ArrayList<>();
        allLanguages.add(italian);
        allLanguages.add(spanish);
        allLanguages.add(portuguese);

        for (LanguageLearner language : allLanguages) {
            language.computeLanguageFrequentWords();
        }*/
    }
}
