package seedu.momentum.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.momentum.model.ProjectBook;
import seedu.momentum.model.ReadOnlyProjectBook;
import seedu.momentum.model.project.Description;
import seedu.momentum.model.project.Name;
import seedu.momentum.model.project.Project;
import seedu.momentum.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ProjectBook} with sample data.
 */
public class SampleDataUtil {

    public static final Description DUMMY_DESCRIPTION = new Description("DUMMY");

    public static Project[] getSampleProjects() {
        return new Project[] {
            new Project(new Name("Alex Yeoh"), DUMMY_DESCRIPTION, getTagSet("friends")),
            new Project(new Name("Bernice Yu"), DUMMY_DESCRIPTION, getTagSet("colleagues", "friends")),
            new Project(new Name("Charlotte Oliveiro"), DUMMY_DESCRIPTION, getTagSet("neighbours")),
            new Project(new Name("David Li"), DUMMY_DESCRIPTION, getTagSet("family")),
            new Project(new Name("Irfan Ibrahim"), DUMMY_DESCRIPTION, getTagSet("classmates")),
            new Project(new Name("Roy Balakrishnan"), DUMMY_DESCRIPTION, getTagSet("colleagues"))
        };
    }

    public static ReadOnlyProjectBook getSampleProjectBook() {
        ProjectBook sampleAb = new ProjectBook();
        for (Project sampleProject : getSampleProjects()) {
            sampleAb.addProject(sampleProject);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
