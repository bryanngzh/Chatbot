package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.formatMessage("Unable to load file.");
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(userInput);
                c.execute(ui, storage, taskList);
                isRunning = c.isRunning();
            } catch (DukeException e) {
                ui.formatMessage(String.valueOf(e));
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
