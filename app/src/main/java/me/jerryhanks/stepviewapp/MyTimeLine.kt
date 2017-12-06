package me.jerryhanks.stepviewapp

import me.jerryhanks.stepview.model.Status
import me.jerryhanks.stepview.model.TimeLine

/**
 * @author <@Po10cio> on 10/18/17 for StepViewApp
 */

class MyTimeLine(status: Status, var title: String?, var content: String?) : TimeLine(status) {


    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is MyTimeLine) return false

        val that = o as MyTimeLine?

        if (if (title != null) title != that!!.title else that!!.title != null) return false
        return if (content != null) content == that.content else that.content == null
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
