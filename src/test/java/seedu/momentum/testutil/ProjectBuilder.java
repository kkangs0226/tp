package seedu.momentum.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.momentum.commons.core.Clock;
import seedu.momentum.commons.core.DateWrapper;
import seedu.momentum.commons.core.UniqueItemList;
import seedu.momentum.model.project.CompletionStatus;
import seedu.momentum.model.project.Deadline;
import seedu.momentum.model.project.Description;
import seedu.momentum.model.project.Name;
import seedu.momentum.model.project.Project;
import seedu.momentum.model.project.Task;
import seedu.momentum.model.project.TrackedItem;
import seedu.momentum.model.reminder.Reminder;
import seedu.momentum.model.tag.Tag;
import seedu.momentum.model.timer.TimerWrapper;
import seedu.momentum.model.timer.WorkDuration;
import seedu.momentum.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_DESCRIPTION = "Likes coding";
    public static final String DEFAULT_CREATED_DATE = "2000-11-05";
    public static final String DEFAULT_DEADLINE_DATE = "2020-11-05";
    public static final String DEFAULT_DEADLINE_TIME = "11:11:11";

    private Name name;
    private Description description;
    private CompletionStatus completionStatus;
    private DateWrapper createdDateWrapper;
    private Deadline deadline;
    private Reminder reminder;
    private Set<Tag> tags;
    private UniqueItemList<WorkDuration> durations;
    private TimerWrapper timerWrapper;
    private UniqueItemList<TrackedItem> taskList;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        completionStatus = new CompletionStatus();
        createdDateWrapper = new DateWrapper(DEFAULT_CREATED_DATE);
        deadline = new Deadline(DEFAULT_DEADLINE_DATE, DEFAULT_DEADLINE_TIME, createdDateWrapper);
        reminder = new Reminder();
        tags = new HashSet<>();
        durations = new UniqueItemList<>();
        timerWrapper = new TimerWrapper();
        taskList = new UniqueItemList<>();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(TrackedItem trackedItemToCopy) {
        name = trackedItemToCopy.getName();
        description = trackedItemToCopy.getDescription();
        completionStatus = trackedItemToCopy.getCompletionStatus();
        createdDateWrapper = trackedItemToCopy.getCreatedDate();
        deadline = trackedItemToCopy.getDeadline();
        reminder = trackedItemToCopy.getReminder();
        tags = new HashSet<>(trackedItemToCopy.getTags());
        durations = new UniqueItemList<>();
        for (WorkDuration duration : trackedItemToCopy.getDurationList()) {
            durations.add(duration);
        }
        timerWrapper = trackedItemToCopy.getTimer();
        taskList = new UniqueItemList<>();
        for (TrackedItem task : ((Project) trackedItemToCopy).getTaskList()) {
            taskList.add(task);
        }
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
     * Sets the {@code CompletionStatus} of the {@code Project} that we are building.
     */
    public ProjectBuilder withCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Project} that we are building to an empty string.
     */
    public ProjectBuilder withEmptyDescription() {
        this.description = Description.EMPTY_DESCRIPTION;
        return this;
    }

    /**
     * Sets the {@code createdDateWrapper} of the {@code Project} that we are building with current date.
     */
    public ProjectBuilder withCurrentCreatedDate() {
        this.createdDateWrapper = Clock.now().getDateWrapper();
        return this;
    }

    /**
     * Sets the {@code CreatedDate} of the {@code Project} that we are building.
     */
    public ProjectBuilder withCreatedDate(String createdDate) {
        this.createdDateWrapper = new DateWrapper(createdDate);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Project} that we are building with an empty deadline.
     */
    public ProjectBuilder withEmptyDeadline() {
        this.deadline = new Deadline();
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDeadline(String date, String createdDate) {
        this.deadline = new Deadline(date, new DateWrapper(createdDate));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDeadline(String date, String time, String createdDate) {
        this.deadline = new Deadline(date, time, new DateWrapper(createdDate));
        return this;
    }

    /**
     * Sets the {@code Reminder} of the {@code Project} that we are building with an empty reminder.
     */
    public ProjectBuilder withEmptyReminder() {
        this.reminder = new Reminder();
        return this;
    }

    /**
     * Sets the {@code Reminder} of the {@code Project} that we are building.
     */
    public ProjectBuilder withReminder(String dateTime) {
        this.reminder = new Reminder(dateTime);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withTags(String... tags) {
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
     * Parses the {@code timerWrapper} into a {@code TimerWrapper} and set it to the {@code Project} that we
     * are building.
     */
    public ProjectBuilder withTimer(TimerWrapper timerWrapper) {
        this.timerWrapper = timerWrapper;
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code UniqueItemList<TrackedItem>} and set it to the {@code Project} that we
     * are building.
     */
    public ProjectBuilder withTasks(Task... tasks) {
        this.taskList = SampleDataUtil.getTaskList(tasks);
        return this;
    }

    /**
     * Builds a {@code Project}.
     */
    public Project build() {
        return new Project(name, description, completionStatus, createdDateWrapper, deadline, reminder, tags, durations,
                timerWrapper, taskList);
    }
}
