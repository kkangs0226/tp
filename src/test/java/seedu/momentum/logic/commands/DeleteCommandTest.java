package seedu.momentum.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.momentum.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.momentum.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.momentum.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.momentum.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.momentum.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.momentum.testutil.TypicalProjects.getTypicalProjectBook;

import org.junit.jupiter.api.Test;

import seedu.momentum.commons.core.Messages;
import seedu.momentum.commons.core.index.Index;
import seedu.momentum.model.Model;
import seedu.momentum.model.ModelManager;
import seedu.momentum.model.UserPrefs;
import seedu.momentum.model.project.Project;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalProjectBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        ModelManager expectedModel = new ModelManager(model.getProjectBook(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        Model expectedModel = new ModelManager(model.getProjectBook(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        showNoProject(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of project book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjectBook().getProjectList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PROJECT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PROJECT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PROJECT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProject(Model model) {
        model.updateFilteredProjectList(p -> false);

        assertTrue(model.getFilteredProjectList().isEmpty());
    }
}