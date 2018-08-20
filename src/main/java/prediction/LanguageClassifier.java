package prediction;

import preprocessing.BookWordReader;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class LanguageClassifier {


    private List<LanguageMatcher> matchers;

    public LanguageClassifier(List<LanguageMatcher> matchers) {
        this.matchers = matchers;
    }

    public String predictLanguage(String bookName) {
        BookWordReader bookWordReader = new BookWordReader(bookName);
        Stream<String> bookWordStream = bookWordReader.getBookWordStream();

        return matchers.stream()
                .map(matcher -> new MatchingResult(matcher.getName(), matcher.matchBookCounts(bookWordStream)))
                .max(Comparator.comparingInt(MatchingResult::getMatchBookCounts))
                .orElse(new MatchingResult("none", 0))
                .getLanguageName();
    }
}
