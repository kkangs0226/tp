package seedu.momentum.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.momentum.commons.core.DateWrapper;
import seedu.momentum.commons.core.UniqueItemList;
import seedu.momentum.model.project.CompletionStatus;
import seedu.momentum.model.project.Deadline;
import seedu.momentum.model.project.Description;
import seedu.momentum.model.project.Name;
import seedu.momentum.model.project.Task;
import seedu.momentum.model.project.TrackedItem;
import seedu.momentum.model.reminder.Reminder;
import seedu.momentum.model.tag.Tag;
import seedu.momentum.model.timer.TimerWrapper;
import seedu.momentum.model.timer.WorkDuration;
import seedu.momentum.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Henry Chia";
    public static final String DEFAULT_DESCRIPTION = "Likes teaching";
    public static final String DEFAULT_CREATED_DATE = "2000-12-12";
    public static final String DEFAULT_DEADLINE_DATE = "2020-11-02";
    public static final String DEFAULT_DEADLINE_TIME = "01:23:45";

    private Name name;
    private Description description;
    private CompletionStatus completionStatus;
    private DateWrapper createdDateWrapper;
    private Deadline deadline;
    private Reminder reminder;
    private Set<Tag> tags;
    private UniqueItemList<WorkDuration> durations;
    private TimerWrapper timerWrapper;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        completionStatus = new CompletionStatus();
        createdDateWrapper = new DateWrapper(DEFAULT_CREATED_DATE);
        deadline = new Deadline(DEFAULT_DEADLINE_DATE, DEFAULT_DEADLINE_TIME, createdDateWrapper);
        reminder = new Reminder();
        tags = new HashSet<>();
        durations = new UniqueItemList<>();
        timerWrapper = new TimerWrapper();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(TrackedItem taskToCopy) {
        name = taskToCopy.getName();
        description = taskToCopy.getDescription();
        completionStatus = taskToCopy.getCompletionStatus();
        createdDateWrapper = taskToCopy.getCreatedDate();
        deadline = taskToCopy.getDeadline();
        reminder = taskToCopy.getReminder();
        tags = new HashSet<>(taskToCopy.getTags());
        durations = new UniqueItemList<>();
        for (WorkDuration duration : taskToCopy.getDurationList()) {
            durations.add(duration);
        }
        timerWrapper = taskToCopy.getTimer();
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code CompletionStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building to an empty string.
     */
    public TaskBuilder withEmptyDescription() {
        this.description = Description.EMPTY_DESCRIPTION;
        return this;
    }

    /**
     * Sets the {@code createdDateWrapper} of the {@code Task} that we are building with current date.
     */
    public TaskBuilder withCurrentCreatedDate(String createdDate) {
        this.createdDateWrapper = new DateWrapper(createdDate);
        return this;
    }

    /**
     * Sets the {@code CreatedDate} of the {@code Task} that we are building.
     */
    public TaskBuilder withCreatedDate(String createdDate) {
        this.createdDateWrapper = new DateWrapper(createdDate);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building with an empty deadline.
     */
    public TaskBuilder withEmptyDeadline() {
        this.deadline = new Deadline();
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String date, String createdDate) {
        this.deadline = new Deadline(date, new DateWrapper(createdDate));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String date, String time, String createdDate) {
        this.deadline = new Deadline(date, time, new DateWrapper(createdDate));
        return this;
    }

    /**
     * Sets the {@code Reminder} of the {@code Task} that we are building with an empty reminder.
     */
    public TaskBuilder withEmptyReminder() {
        this.reminder = new Reminder();
        return this;
    }

    /**
     * Sets the {@code Reminder} of the {@code Task} that we are building.
     */
    public TaskBuilder withReminder(String dateTime) {
        this.reminder = new Reminder(dateTime);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code durations} into a {@code UniqueDurationList} and set it to the {@code Task} that we
     * are building.
     */
    public TaskBuilder withDurations(WorkDuration... durations) {
        this.durations = SampleDataUtil.getDurationList(durations);
        return this;
    }

    /**
     * Parses the {@code timerWrapper} into a {@code TimerWrapper} and set it to the {@code Task} that we
     * are building.
     */
    public TaskBuilder withTimer(TimerWrapper timerWrapper) {
        this.timerWrapper = timerWrapper;
        return this;
    }

    /**
     * Builds a {@code Task}.
     */
    public Task build() {
        return new Task(name, description, completionStatus, createdDateWrapper, deadline, reminder, tags, durations,
                timerWrapper);
    }
}
