package engine.managers;

import game.architecture.Constants;
import game.architecture.Manager;

import java.util.List;

import model.Coords;
import model.ship.Ship;
import model.ship.task.MovementTask;
import model.ship.task.Task;

/**
 * Manages the ship tasks and designations.
 */
public class TaskManager extends Manager<Task> {

    /* ========== PUBLIC ========== */
    public void assignTask(Ship ship, Task task) {
        ship.getControl().getTaskList().add(task);
    }

    public void executeTask(Ship ship) {
        List<Task> taskList = ship.getControl().getTaskList();

        if (!taskList.isEmpty()) {
            Task currentTask = taskList.get(0);
            if (currentTask.execute(ship)) {
                taskList.remove(currentTask);
            }
        } else {
//            Random random = new Random();
            // int rand = new Random().nextInt(2);

            Task task;
            // if (rand == 0) {
            // task = new MovementTask(new Coords(random.nextInt(20000 - 10000), 0, random.nextInt(20000 - 10000)));
            task = new MovementTask(new Coords(Constants.AU_PARAMETER, 0, Constants.AU_PARAMETER));

            // } else {
            // task = new OrbitTask(celestialBodyRepository.getById(random.nextInt(30)), random.nextInt(20 + 5), 0, 0);
            // }

            taskList.add(task);
        }
    }
}