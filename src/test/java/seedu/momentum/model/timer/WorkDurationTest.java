package seedu.momentum.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.momentum.commons.core.DateTimeWrapper;
import seedu.momentum.model.timer.WorkDuration;
import seedu.momentum.testutil.TypicalWorkDuration;

public class WorkDurationTest {

    @Test
    public void isSameDuration() {
        // different phone and email -> returns false
        //        new ProjectBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        //        assertFalse(ALICE.isSameProject(editedAlice));

        // different name -> returns false
        //        Project editedAlice = editedAlice = new ProjectBuilder(ALICE).withName(VALID_NAME_BOB).build();
        //        assertFalse(ALICE.isSameProject(editedAlice));

        // same name, same phone, different attributes -> returns true
        //        editedAlice = new ProjectBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
        //                .withTags(VALID_TAG_HUSBAND).build();
        //        assertTrue(ALICE.isSameProject(editedAlice));

        // same name, same email, different attributes -> returns true
        //        editedAlice = new ProjectBuilder(ALICE).withPhone(VALID_PHONE_BOB)
        //                .withTags(VALID_TAG_HUSBAND).build();
        //        assertTrue(ALICE.isSameProject(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        //        editedAlice = new ProjectBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        //        assertTrue(ALICE.isSameProject(editedAlice));

        // same values -> returns true
        WorkDuration durationCopy = new WorkDuration(TypicalWorkDuration.DURATION_ONE_DAY.getStartTime(),
                TypicalWorkDuration.DURATION_ONE_DAY.getStopTime());
        assertTrue(TypicalWorkDuration.DURATION_ONE_DAY.isSameAs(durationCopy));

        // same object -> returns true
        assertTrue(TypicalWorkDuration.DURATION_ONE_DAY.isSameAs(TypicalWorkDuration.DURATION_ONE_DAY));

        // null -> returns false
        assertFalse(TypicalWorkDuration.DURATION_ONE_DAY.isSameAs(null));

        // different project -> returns false
        assertFalse(TypicalWorkDuration.DURATION_ONE_DAY.isSameAs(TypicalWorkDuration.DURATION_ONE_MONTH));

        // different startTime -> returns false
        WorkDuration diffStart = new WorkDuration(new DateTimeWrapper(LocalDateTime.now()),
                TypicalWorkDuration.DURATION_ONE_DAY.getStopTime());
        assertFalse(TypicalWorkDuration.DURATION_ONE_DAY.isSameAs(diffStart));

        // different stopTime -> returns false
        WorkDuration diffEnd = new WorkDuration(TypicalWorkDuration.DURATION_ONE_DAY.getStartTime(),
                new DateTimeWrapper(LocalDateTime.now()));
        assertFalse(TypicalWorkDuration.DURATION_ONE_DAY.isSameAs(diffEnd));
    }
}
