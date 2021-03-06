package seedu.momentum.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.momentum.testutil.TypicalProjects.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DescriptionContainsKeywordsPredicateTest {

    private static final List<String> SINGLE_KEYWORD = Collections.singletonList("coding");
    private static final List<String> MULTIPLE_KEYWORDS = Arrays.asList("likes", "coding");
    private static final List<String> ONE_MATCHING_KEYWORD = Arrays.asList("coding", "starbucks");
    private static final List<String> MIXED_CASE_KEYWORDS = Arrays.asList("lIkEs", "cOdiNg");
    private static final List<String> NO_MATCHING_KEYWORDS = Arrays.asList("nothing", "matches");
    private static final List<String> ONLY_MATCHES_NAME = Arrays.asList("Alice", "Pauline");
    private static final List<String> ONLY_MATCHES_TAG = Collections.singletonList("friends");

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstAnyPredicate =
                new DescriptionContainsKeywordsPredicate(FindType.ANY, firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondAnyPredicate =
                new DescriptionContainsKeywordsPredicate(FindType.ANY, secondPredicateKeywordList);

        DescriptionContainsKeywordsPredicate firstAllPredicate =
                new DescriptionContainsKeywordsPredicate(FindType.ALL, firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondAllPredicate =
                new DescriptionContainsKeywordsPredicate(FindType.ALL, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstAnyPredicate.equals(firstAnyPredicate));
        assertTrue(firstAllPredicate.equals(firstAllPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstAnyPredicateCopy =
                new DescriptionContainsKeywordsPredicate(FindType.ANY, firstPredicateKeywordList);
        assertTrue(firstAnyPredicate.equals(firstAnyPredicateCopy));
        DescriptionContainsKeywordsPredicate firstAllPredicateCopy =
                new DescriptionContainsKeywordsPredicate(FindType.ALL, firstPredicateKeywordList);
        assertTrue(firstAllPredicate.equals(firstAllPredicateCopy));

        // different types -> returns false
        assertFalse(firstAnyPredicate.equals(1));
        assertFalse(firstAllPredicate.equals(1));

        // null -> returns false
        assertFalse(firstAnyPredicate.equals(null));
        assertFalse(firstAllPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstAnyPredicate.equals(secondAnyPredicate));
        assertFalse(firstAllPredicate.equals(secondAllPredicate));

        // different findType -> returns false
        assertFalse(firstAnyPredicate.equals(firstAllPredicate));
        assertFalse(secondAnyPredicate.equals(secondAllPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords() {
        // One keyword (Any)
        DescriptionContainsKeywordsPredicate anyPredicate =
                new DescriptionContainsKeywordsPredicate(FindType.ANY, SINGLE_KEYWORD);
        assertTrue(anyPredicate.test(ALICE));

        // Multiple keywords (Any)
        anyPredicate = new DescriptionContainsKeywordsPredicate(FindType.ANY, MULTIPLE_KEYWORDS);
        assertTrue(anyPredicate.test(ALICE));

        // Only one matching keyword (Any)
        anyPredicate = new DescriptionContainsKeywordsPredicate(FindType.ANY, ONE_MATCHING_KEYWORD);
        assertTrue(anyPredicate.test(ALICE));

        // Mixed-case keywords (Any)
        anyPredicate = new DescriptionContainsKeywordsPredicate(FindType.ANY, MIXED_CASE_KEYWORDS);
        assertTrue(anyPredicate.test(ALICE));

        // One keyword (All)
        DescriptionContainsKeywordsPredicate allPredicate =
                new DescriptionContainsKeywordsPredicate(FindType.ALL, SINGLE_KEYWORD);
        assertTrue(allPredicate.test(ALICE));

        // Multiple keywords (All)
        allPredicate = new DescriptionContainsKeywordsPredicate(FindType.ALL, MULTIPLE_KEYWORDS);
        assertTrue(allPredicate.test(ALICE));

        // Only one matching keyword (All)
        allPredicate = new DescriptionContainsKeywordsPredicate(FindType.ALL, ONE_MATCHING_KEYWORD);
        assertFalse(allPredicate.test(ALICE));

        // Mixed-case keywords (All)
        allPredicate = new DescriptionContainsKeywordsPredicate(FindType.ALL, MIXED_CASE_KEYWORDS);
        assertTrue(allPredicate.test(ALICE));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword (Any)
        DescriptionContainsKeywordsPredicate anyPredicate =
                new DescriptionContainsKeywordsPredicate(FindType.ANY, NO_MATCHING_KEYWORDS);
        assertFalse(anyPredicate.test(ALICE));

        // Keywords match name, but does not match description (Any)
        anyPredicate = new DescriptionContainsKeywordsPredicate(FindType.ANY, ONLY_MATCHES_NAME);
        assertFalse(anyPredicate.test(ALICE));

        // Keywords match tag, but does not match description (Any)
        anyPredicate = new DescriptionContainsKeywordsPredicate(FindType.ANY, ONLY_MATCHES_TAG);
        assertFalse(anyPredicate.test(ALICE));

        // Non-matching keyword (All)
        DescriptionContainsKeywordsPredicate allPredicate =
                new DescriptionContainsKeywordsPredicate(FindType.ALL, NO_MATCHING_KEYWORDS);
        assertFalse(allPredicate.test(ALICE));

        // Keywords match name, but does not match description (All)
        allPredicate = new DescriptionContainsKeywordsPredicate(FindType.ALL, ONLY_MATCHES_NAME);
        assertFalse(allPredicate.test(ALICE));

        // Keywords match tag, but does not match description (All)
        allPredicate = new DescriptionContainsKeywordsPredicate(FindType.ALL, ONLY_MATCHES_TAG);
        assertFalse(allPredicate.test(ALICE));
    }
}
