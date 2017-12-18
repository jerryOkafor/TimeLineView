package me.jerryhanks.timelineview.interfaces

import android.view.View
import android.widget.FrameLayout

import me.jerryhanks.timelineview.model.TimeLine

/**
 * @author <@Po10cio> on 10/18/17 for StepViewApp
 */

interface TimeLineViewCallback<in T : TimeLine> {
    fun onBindView(model: T, container: FrameLayout, position: Int): View
}
