package me.jerryhanks.stepview.model

/**
 * @author <@Po10cio> on 10/18/17 for StepViewApp
 */

open class TimeLine {
    var status: Status? = null

    constructor(status: Status) {
        this.status = status
    }

    constructor() {
        this.status = Status.COMPLETED
    }
}
