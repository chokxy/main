package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HISTORY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddHistCommand;

public class AddHistCommandParserTest {
    private AddHistCommandParser parser = new AddHistCommandParser();
    private final String nonEmptyMedHistory = "Some medical history.";

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_HISTORY + nonEmptyMedHistory;
        AddHistCommand expectedCommand = new AddHistCommand(INDEX_FIRST_PERSON, nonEmptyMedHistory);
        assertParseSuccess(parser, userInput, expectedCommand);
        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_HISTORY;
        expectedCommand = new AddHistCommand(INDEX_FIRST_PERSON, "");
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHistCommand.MESSAGE_USAGE);
        // no parameters
        assertParseFailure(parser, AddHistCommand.COMMAND_WORD, expectedMessage);
        // no index
        assertParseFailure(parser, AddHistCommand.COMMAND_WORD + " " + nonEmptyMedHistory, expectedMessage);
    }
}
