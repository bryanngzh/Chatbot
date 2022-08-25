package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Command that deletes the task.
 *
 * @author Bryan Ng Zi Hao
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task as determined by the index number.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @throws DukeException There is an error in execution.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(this.index);
        taskList.remove(this.index - 1);
        int numTasks = taskList.size();
        ui.formatMessage("Noted. I've removed this task:");
        ui.formatMessage(task.toString());
        if (taskList.size() == 1) {
            ui.formatMessage(String.format("Now you have %d task in the list.", taskList.size()));
        } else {
            ui.formatMessage(String.format("Now you have %d tasks in the list.", taskList.size()));
        }
    }

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
