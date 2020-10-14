package seedu.momentum.model.project.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.momentum.commons.util.StringUtil;
import seedu.momentum.model.project.Project;

/**
 * Tests that a {@code Project}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Project> {
    private final List<String> keywords;
    private final FindType findType;

    /**
     * Predicate to check whether the {@code Description} of a {@code Project} contains a
     * certain keyword.
     *
     * @param findType enum to indicate whether the find type to be used for this find command.
     * @param keywords list of keywords to check for matches.
     */
    public DescriptionContainsKeywordsPredicate(FindType findType, List<String> keywords) {
        this.findType = findType;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Project project) {
        Predicate<String> predicate = keyword ->
                StringUtil.containsPartialIgnoreCase(project.getDescription().value, keyword);
        switch (findType) {
        case ALL:
            return keywords.stream().allMatch(predicate);
        case ANY:
            // Fallthrough
        default:
            return keywords.stream().anyMatch(predicate);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords)) // state check
                && findType == ((DescriptionContainsKeywordsPredicate) other).findType;
    }

}