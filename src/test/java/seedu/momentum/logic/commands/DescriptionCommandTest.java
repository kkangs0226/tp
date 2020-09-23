package seedu.momentum.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.momentum.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
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
import seedu.momentum.model.ProjectBook;
import seedu.momentum.model.UserPrefs;
import seedu.momentum.model.project.Description;
import seedu.momentum.model.project.Project;
import seedu.momentum.testutil.ProjectBuilder;

class DescriptionCommandTest {

    private static final String DUMMY_DESCRIPTION = "Random Description";

    private Model model = new ModelManager(getTypicalProjectBook(), new UserPrefs());

    @Test
    void execute_addDescriptionUnfilteredList_success() {

        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Project editedProject = new ProjectBuilder(firstProject).withDescription(DUMMY_DESCRIPTION).build();

        DescriptionCommand descriptionCommand = new DescriptionCommand(INDEX_FIRST_PROJECT,
                new Description(editedProject.getDescription().value));

        String expectedMessage = String.format(DescriptionCommand.MESSAGE_ADD_DESCRIPTION_SUCCESS, editedProject);
        Model expectedModel = new ModelManager(new ProjectBook(model.getProjectBook()), new UserPrefs());
        expectedModel.setProject(firstProject, editedProject);

        assertCommandSuccess(descriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        Project editedProject = new ProjectBuilder(model.getFilteredProjectList()
                .get(INDEX_FIRST_PROJECT.getZeroBased())).withDescription(DUMMY_DESCRIPTION).build();

        DescriptionCommand descriptionCommand = new DescriptionCommand(INDEX_FIRST_PROJECT,
                new Description(editedProject.getDescription().value));

        String expectedMessage = String.format(DescriptionCommand.MESSAGE_ADD_DESCRIPTION_SUCCESS, editedProject);

        Model expectedModel = new ModelManager(new ProjectBook(model.getProjectBook()), new UserPrefs());
        expectedModel.setProject(firstProject, editedProject);

        assertCommandSuccess(descriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        DescriptionCommand descriptionCommand = new DescriptionCommand(outOfBoundIndex,
                new Description(VALID_DESCRIPTION_BOB));

        assertCommandFailure(descriptionCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidProjectIndexFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);
        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjectBook().getProjectList().size());

        DescriptionCommand descriptionCommand =
                new DescriptionCommand(outOfBoundIndex, new Description(VALID_DESCRIPTION_BOB));

        assertCommandFailure(descriptionCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DescriptionCommand standardCommand = new DescriptionCommand(INDEX_FIRST_PROJECT,
                new Description(VALID_DESCRIPTION_AMY));
        // same values -> returns true
        DescriptionCommand commandWithSameValues = new DescriptionCommand(INDEX_FIRST_PROJECT,
                new Description(VALID_DESCRIPTION_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new DescriptionCommand(INDEX_SECOND_PROJECT,
                new Description(VALID_DESCRIPTION_AMY))));
        // different description -> returns false
        assertFalse(standardCommand.equals(new DescriptionCommand(INDEX_FIRST_PROJECT,
                new Description(VALID_DESCRIPTION_BOB))));
    }
}
