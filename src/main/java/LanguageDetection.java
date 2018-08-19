import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDetection {


    public static final String PATH = "src/resources/";

    public static void main(String[] args) throws IOException {
        /*final SparkConf conf = new SparkConf()
                .setAppName("sada")
                .setMaster("local[*]");

        final SparkSession spark = new SparkSession.Builder()
                .config(conf)
                .getOrCreate();*/

//        Dataset<String> ds = spark.read().textFile("src/main/resources/train/spanish/trafalgar.txt");
//        Dataset<Object> objectDataset = ds.flatMap(r -> r.split(""));


        LanguageLearner italian = new LanguageLearner("train/italian");
        LanguageLearner spanish = new LanguageLearner("train/spanish");
        LanguageLearner portuguese = new LanguageLearner("train/portuguese");

        List<LanguageLearner> allLanguages = new ArrayList<>();
        allLanguages.add(italian);
        allLanguages.add(spanish);
        allLanguages.add(portuguese);

        for (LanguageLearner language : allLanguages) {
            List<String> allLanguageWords = language.getAllLanguageWords();
            System.out.println(allLanguageWords.size());
        }
    }
}
