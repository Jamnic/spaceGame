package model.ship.parts;

import java.util.LinkedList;
import java.util.List;

import model.ship.task.Task;

public class Control {

    private List<Task> taskList = new LinkedList<Task>();

    private boolean turbo;
    private boolean accelerating;
    private boolean braking;

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isTurbo() {
        return turbo;
    }

    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    public boolean isAccelerating() {
        return accelerating;
    }

    public void setAccelerating(boolean accelerating) {
        this.accelerating = accelerating;
    }

    public boolean isBraking() {
        return braking;
    }

    public void setBraking(boolean braking) {
        this.braking = braking;
    }
}
