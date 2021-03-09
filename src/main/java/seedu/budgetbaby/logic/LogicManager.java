package seedu.budgetbaby.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.budgetbaby.commons.core.GuiSettings;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.logic.commands.Command;
import seedu.budgetbaby.logic.commands.CommandResult;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.AddressBookParser;
import seedu.budgetbaby.logic.parser.BudgetBabyParser;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.Model;
import seedu.budgetbaby.model.ReadOnlyAddressBook;
import seedu.budgetbaby.model.person.Person;
import seedu.budgetbaby.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final BudgetBabyParser budgetBabyParser;


    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        budgetBabyParser = new BudgetBabyParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
//        Command command = addressBookParser.parseCommand(commandText);
        Command command = budgetBabyParser.parseCommand(commandText);
        commandResult = command.execute(model);

//        try {
//            storage.saveAddressBook(model.getAddressBook());
//        } catch (IOException ioe) {
//            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
//        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
