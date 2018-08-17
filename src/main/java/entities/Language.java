package entities;

import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

public class Language {
    public static final String PATH = "src/main/resources/";


    private final String languageName;
    private SparkSession spark;

    private List<Book> books;


    public Language(String name, SparkSession spark) {
        this.languageName = name;
        this.spark = spark;
        this.books = new ArrayList<>();
    }

    public void computeFrequentBooks() {

    }



}
