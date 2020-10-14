package seedu.momentum.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.momentum.testutil.TypicalProjects.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TagListContainsKeywordsPredicateTest {

    private static final List<String> SINGLE_KEYWORD = Collections.singletonList("friends");
    private static final List<String> MULTIPLE_KEYWORDS = Arrays.asList("friends", "owesMoney");
    private static final List<String> ONE_MATCHING_KEYWORD = Arrays.asList("dogs", "owesMoney");
    private static final List<String> MIXED_CASE_KEYWORDS = Arrays.asList("fRiEnDs", "oWeSmOneY");
    private static final List<String> NO_MATCHING_KEYWORDS = Arrays.asList("nothing", "matches");
    private static final List<String> ONLY_MATCHES_NAME = Arrays.asList("Benson", "Meier");
    private static final List<String> ONLY_MATCHES_DESCRIPTION = Collections.singletonList("dogs");

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagListContainsKeywordsPredicate firstAnyPredicate =
                new TagListContainsKeywordsPredicate(FindType.ANY, firstPredicateKeywordList);
        TagListContainsKeywordsPredicate secondAnyPredicate =
                new TagListContainsKeywordsPredicate(FindType.ANY, secondPredicateKeywordList);

        TagListContainsKeywordsPredicate firstAllPredicate =
                new TagListContainsKeywordsPredicate(FindType.ALL, firstPredicateKeywordList);
        TagListContainsKeywordsPredicate secondAllPredicate =
                new TagListContainsKeywordsPredicate(FindType.ALL, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstAnyPredicate.equals(firstAnyPredicate));
        assertTrue(firstAllPredicate.equals(firstAllPredicate));

        // same values -> returns true
        TagListContainsKeywordsPredicate firstAnyPredicateCopy =
                new TagListContainsKeywordsPredicate(FindType.ANY, firstPredicateKeywordList);
        assertTrue(firstAnyPredicate.equals(firstAnyPredicateCopy));
        TagListContainsKeywordsPredicate firstAllPredicateCopy =
                new TagListContainsKeywordsPredicate(FindType.ALL, firstPredicateKeywordList);
        assertTrue(firstAllPredicate.equals(firstAllPredicateCopy));

        // different types -> returns false
        assertFalse(firstAnyPredicate.equals(1));
        assertFalse(firstAllPredicate.equals(1));

        // null -> returns false
        assertFalse(firstAnyPredicate.equals(null));
        assertFalse(firstAllPredicate.equals(null));

        // different project -> returns false
        assertFalse(firstAnyPredicate.equals(secondAnyPredicate));
        assertFalse(firstAllPredicate.equals(secondAllPredicate));
    }

    @Test
    public void test_tagListContainsKeywords() {
        // One keyword (Any)
        TagListContainsKeywordsPredicate anyPredicate =
                new TagListContainsKeywordsPredicate(FindType.ANY, SINGLE_KEYWORD);
        assertTrue(anyPredicate.test(BENSON));

        // Multiple keywords (Any)
        anyPredicate = new TagListContainsKeywordsPredicate(FindType.ANY, MULTIPLE_KEYWORDS);
        assertTrue(anyPredicate.test(BENSON));

        // Only one matching keyword (Any)
        anyPredicate = new TagListContainsKeywordsPredicate(FindType.ANY, ONE_MATCHING_KEYWORD);
        assertTrue(anyPredicate.test(BENSON));

        // Mixed-case keywords (Any)
        anyPredicate = new TagListContainsKeywordsPredicate(FindType.ANY, MIXED_CASE_KEYWORDS);
        assertTrue(anyPredicate.test(BENSON));

        // One keyword (All)
        TagListContainsKeywordsPredicate allPredicate =
                new TagListContainsKeywordsPredicate(FindType.ALL, SINGLE_KEYWORD);
        assertTrue(allPredicate.test(BENSON));

        // Multiple keywords (All)
        allPredicate = new TagListContainsKeywordsPredicate(FindType.ALL, MULTIPLE_KEYWORDS);
        assertTrue(allPredicate.test(BENSON));

        // Only one matching keyword (All)
        allPredicate = new TagListContainsKeywordsPredicate(FindType.ALL, ONE_MATCHING_KEYWORD);
        assertFalse(allPredicate.test(BENSON));

        // Mixed-case keywords (All)
        allPredicate = new TagListContainsKeywordsPredicate(FindType.ALL, MIXED_CASE_KEYWORDS);
        assertTrue(allPredicate.test(BENSON));
    }

    @Test
    public void test_tagListDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword (Any)
        TagListContainsKeywordsPredicate anyPredicate =
                new TagListContainsKeywordsPredicate(FindType.ANY, NO_MATCHING_KEYWORDS);
        assertFalse(anyPredicate.test(BENSON));

        // Keywords match name, but does not match tags (Any)
        anyPredicate = new TagListContainsKeywordsPredicate(FindType.ANY, ONLY_MATCHES_NAME);
        assertFalse(anyPredicate.test(BENSON));

        // Keywords match description, but does not match tags (Any)
        anyPredicate = new TagListContainsKeywordsPredicate(FindType.ANY, ONLY_MATCHES_DESCRIPTION);
        assertFalse(anyPredicate.test(BENSON));

        // Non-matching keyword (All)
        TagListContainsKeywordsPredicate allPredicate =
                new TagListContainsKeywordsPredicate(FindType.ALL, NO_MATCHING_KEYWORDS);
        assertFalse(allPredicate.test(BENSON));

        // Keywords match name, but does not match tags (All)
        allPredicate = new TagListContainsKeywordsPredicate(FindType.ALL, ONLY_MATCHES_NAME);
        assertFalse(allPredicate.test(BENSON));

        // Keywords match description, but does not match tags (All)
        allPredicate = new TagListContainsKeywordsPredicate(FindType.ALL, ONLY_MATCHES_DESCRIPTION);
        assertFalse(allPredicate.test(BENSON));
    }
}
