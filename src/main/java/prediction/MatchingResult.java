package prediction;

public class MatchingResult {
    private final String languageName;
    private final int matchBookCounts;

    public MatchingResult(String name, int matchBookCounts) {
        languageName = name;
        this.matchBookCounts = matchBookCounts;
    }

    public int getMatchBookCounts() {
        return matchBookCounts;
    }

    public String getLanguageName() {
        return languageName;
    }
}
