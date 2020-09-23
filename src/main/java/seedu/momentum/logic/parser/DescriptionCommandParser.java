package seedu.momentum.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.momentum.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.momentum.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.momentum.commons.core.index.Index;
import seedu.momentum.commons.exceptions.IllegalValueException;
import seedu.momentum.logic.commands.DescriptionCommand;
import seedu.momentum.logic.parser.exceptions.ParseException;
import seedu.momentum.model.project.Description;

public class DescriptionCommandParser implements Parser<DescriptionCommand> {

    /**
     * Parses the Description Command from arguments, args.
     * @param args arguments by user.
     * @return DescriptionCommand object.
     * @throws ParseException if IllegalValueException thrown.
     */
    public DescriptionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_DESCRIPTION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DescriptionCommand.MESSAGE_USAGE), ive);
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("");

        return new DescriptionCommand(index, new Description(description));
    }
}
