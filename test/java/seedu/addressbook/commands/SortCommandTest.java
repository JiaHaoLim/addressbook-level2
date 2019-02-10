package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() {

        assertSortCommandBehavior("ascend", Arrays.asList(td.amy, td.bill, td.candy, td.dan));
        assertSortCommandBehavior("descend", Arrays.asList(td.dan, td.candy, td.bill, td.amy));

    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertSortCommandBehavior(String order, List<ReadOnlyPerson> expectedPersonList) {
        SortCommand command = createSortCommand(order);
        CommandResult result = command.execute();
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_SORTED_OVERVIEW);

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedPersonList, allPersons);
    }

    private SortCommand createSortCommand(String order) {
        SortCommand command = new SortCommand(order);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
}
