package seedu.addressbook.commands;

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
        return null;
    }
}
