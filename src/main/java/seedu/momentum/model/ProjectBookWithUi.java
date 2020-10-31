package seedu.momentum.model;

import java.util.function.Predicate;

import seedu.momentum.model.project.Project;
import seedu.momentum.model.project.TrackedItem;

public class ProjectBookWithUi extends ProjectBook {

    private final ViewMode viewMode;
    private final Project project;
    private final Predicate<TrackedItem> predicate;
    /**
     * Constructs a {@code ProjectBookWithUi}
     */
    public ProjectBookWithUi(ReadOnlyProjectBook projectBook, ViewMode viewMode, Project project,
                             Predicate<TrackedItem> predicate) {
        super(projectBook);
        this.viewMode = viewMode;
        this.project = project;
        this.predicate = predicate;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public Project getProject() {
        return project;
    }

    public Predicate<TrackedItem> getPredicate() {
        return predicate;
    }

}
