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
            + ": Sorts the address book by name based on the input order.\n"
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

    static class Ascend implements Comparator<Person> {

        /**
         * compare()
         * Overrides Comparator compare.
         * Compare input strings.
         *
         * @param p1 String 1
         * @param p2 String 2
         * @return 1 if p1 p2 is in ascending order
         *         -1 if p1 p2 is in descending order
         *         0 if p1 and p2 are the same
         */
        public int compare(Person p1, Person p2) {
            return p1.getName().toString().compareTo(p2.getName().toString());
        }
    }

    static class Descend implements Comparator<Person> {

        /**
         * compare()
         * Overrides Comparator compare.
         * Compare input strings.
         *
         * @param p1 String 1
         * @param p2 String 2
         * @return 1 if p1 p2 is in ascending order
         *         -1 if p1 p2 is in descending order
         *         0 if p1 and p2 are the same
         */
        public int compare(Person p1, Person p2) {
            return p2.getName().toString().compareTo(p1.getName().toString());
        }
    }
}
