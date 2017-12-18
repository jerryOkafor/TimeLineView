package me.jerryhanks.stepviewapp

import me.jerryhanks.timelineview.model.Status
import me.jerryhanks.timelineview.model.TimeLine

/**
 * @author <@Po10cio> on 10/18/17 for StepViewApp
 */

 class MyTimeLine(status: Status, var title: String?, var content: String?) : TimeLine(status) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MyTimeLine

        if (title != other.title) return false
        if (content != other.content) return false

        return true
    }


    override fun hashCode(): Int {
        var result = if (title != null) title!!.hashCode() else 0
        result = 31 * result + if (content != null) content!!.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "MyTimeLine{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}'
    }


}
