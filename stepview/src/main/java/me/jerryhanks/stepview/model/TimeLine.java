package me.jerryhanks.stepview.model;

/**
 * @author <@Po10cio> on 10/18/17 for StepViewApp
 */

public class TimeLine {
    private Status status;

    public TimeLine(Status status) {
        this.status = status;
    }

    public TimeLine() {
        this.status = Status.COMPLETED;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
