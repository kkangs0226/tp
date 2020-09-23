package seedu.momentum.logic.commands;

import static seedu.momentum.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.momentum.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import java.util.List;

import seedu.momentum.commons.core.Messages;
import seedu.momentum.commons.core.index.Index;
import seedu.momentum.logic.commands.exceptions.CommandException;
import seedu.momentum.model.Model;
import seedu.momentum.model.project.Description;
import seedu.momentum.model.project.Project;

/**
 * Changes the description of an existing project in the project book.
 */
public class DescriptionCommand extends Command {

    public static final String COMMAND_WORD = "description";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the description of the project identified "
            + "by the index number used in the last project listing. "
            + "Existing description will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "d/ [DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/ Likes to swim.";

    public static final String MESSAGE_ADD_DESCRIPTION_SUCCESS = "Added description to Person: %1$s";
    public static final String MESSAGE_INVALID_DESCRIPTION = "Invalid description";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Description: %2$s";

    private final Index index;
    private final Description description;

    /**
     * @param index of the person in the filtered person list to edit the description
     * @param description of the person to be updated to
     */
    public DescriptionCommand(Index index, Description description) {
        requireAllNonNull(index, description);

        this.index = index;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        Project editedProject = new Project(projectToEdit.getName(), description, projectToEdit.getTags());
        if (!Description.isValidDescription(editedProject.getDescription().toString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        model.setProject(projectToEdit, editedProject);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(generateSuccessMessage(editedProject));
    }

    /**
     * Generates a command execution success message based on whether the description is added to or removed from
     * {@code projectToEdit}.
     */
    private String generateSuccessMessage(Project projectToEdit) {
        String message = MESSAGE_ADD_DESCRIPTION_SUCCESS;
        return String.format(message, projectToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DescriptionCommand)) {
            return false;
        }

        // state check
        DescriptionCommand e = (DescriptionCommand) other;
        return index.equals(e.index)
                && description.equals(e.description);
    }
}
