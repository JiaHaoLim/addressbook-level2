package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sorts the address book based on the input order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the address book based on the input order.\n"
            + "Parameters: ascend, descend\n"
            + "Example: " + COMMAND_WORD + " ascend";

    private final String commandArgs;

    public SortCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public CommandResult execute() {
        if (executeSortList(commandArgs)) {
            List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
            return new CommandResult(Messages.MESSAGE_PERSONS_SORTED_OVERVIEW, allPersons);
        } else {
            return new CommandResult(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }

    /**
     * Sorts all persons in the address book by name according to the input order.
     *
     * @param commandArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private boolean executeSortList(String commandArgs) {
        Iterable<Person> iterable = addressBook.getAllPersons();
        ArrayList<Person> list = new ArrayList<>();
        iterable.forEach(list::add);
        Comparator order;

        switch (commandArgs) {
            case "ascend":
                order = new Ascend();
                break;
            case "descend":
                order = new Descend();
                break;
            default :
                return false;
        }

        list.sort(order);

        addressBook.clear();

        try {
            for (Person each : list) {
                addressBook.addPerson(each);
            }
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return false;
        }

        return true;
    }

}
