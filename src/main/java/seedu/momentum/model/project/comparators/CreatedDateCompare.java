package seedu.momentum.model.project.comparators;

import java.util.Comparator;

import seedu.momentum.commons.core.DateWrapper;
import seedu.momentum.model.project.TrackedItem;

/**
 * Compares created date of two tracked items.
 */
public class CreatedDateCompare implements Comparator<TrackedItem> {

    /**
     * Compares the created date of two tracked items.
     *
     * @param t1 first tracked item to compare.
     * @param t2 second tracked item to compare.
     * @return integer values for comparison.
     */
    public int compare(TrackedItem t1, TrackedItem t2) {

        DateWrapper p1DateWrapper = t1.getCreatedDate();
        DateWrapper p2DateWrapper = t2.getCreatedDate();

        NameCompare nameCompare = new NameCompare();

        if (p1DateWrapper.get().isBefore(p2DateWrapper.get())) {
            return -1;
        } else if (p1DateWrapper.get().isAfter(p2DateWrapper.get())) {
            return 1;
        } else {
            return nameCompare.compare(t1, t2);
        }
    }
}
