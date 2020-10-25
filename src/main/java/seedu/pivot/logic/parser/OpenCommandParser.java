package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.Messages.MESSAGE_INCORRECT_CASE_PAGE;
import static seedu.pivot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.Command.TYPE_CASE;
import static seedu.pivot.logic.commands.Command.TYPE_DOC;
import static seedu.pivot.logic.parser.PivotParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.OpenCommand;
import seedu.pivot.logic.commands.casecommands.OpenCaseCommand;
import seedu.pivot.logic.commands.documentcommands.OpenDocumentCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;

/**
 * Parses input arguments and creates a new OpenCommand Object.
 */
public class OpenCommandParser implements Parser<OpenCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OpenCommand
     * and returns an OpenCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public OpenCommand parse(String args) throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE));
        }
        final String openType = matcher.group("commandWord");
        final String indexString = matcher.group("arguments");

        Index index = ParserUtil.getParsedIndex(indexString, OpenCommand.MESSAGE_USAGE);

        switch(openType) {
        case TYPE_CASE:
            return new OpenCaseCommand(index);
        case TYPE_DOC:
            if (StateManager.atMainPage()) {
                throw new ParseException(MESSAGE_INCORRECT_CASE_PAGE);
            }
            return new OpenDocumentCommand(index);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE));
        }


    }




}
