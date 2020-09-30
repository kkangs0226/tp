package seedu.momentum.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.momentum.model.project.Description;
import seedu.momentum.model.project.Name;
import seedu.momentum.model.project.Project;
import seedu.momentum.model.tag.Tag;
import seedu.momentum.model.timer.Timer;
import seedu.momentum.model.timer.UniqueDurationList;
import seedu.momentum.model.timer.WorkDuration;
import seedu.momentum.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_DESCRIPTION = "Likes coding";

    private Name name;
    private Description description;
    private Set<Tag> tags;
    private UniqueDurationList durations;
    private Timer timer;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
        durations = new UniqueDurationList();
        timer = new Timer();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        name = projectToCopy.getName();
        description = projectToCopy.getDescription();
        tags = new HashSet<>(projectToCopy.getTags());
        durations = new UniqueDurationList();
        for (WorkDuration duration : projectToCopy.getDurationList()) {
            durations.add(duration);
        }
        timer = projectToCopy.getTimer();
    }

    /**
     * Sets the {@code Name} of the {@code Project} that we are building.
     */
    public ProjectBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Project} that we are building to an empty string.
     */
    public ProjectBuilder withDescription() {
        this.description = Description.EMPTY_DESCRIPTION;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code durations} into a {@code UniqueDurationList} and set it to the {@code Project} that we
     * are building.
     */
    public ProjectBuilder withDurations(WorkDuration... durations) {
        this.durations = SampleDataUtil.getDurationList(durations);
        return this;
    }

    /**
     * Parses the {@code timer} into a {@code Timer} and set it to the {@code Project} that we
     * are building.
     */
    public ProjectBuilder withTimer(Timer timer) {
        this.timer = timer;
        return this;
    }

    public Project build() {
        return new Project(name, description, tags, durations, timer);
    }
}
