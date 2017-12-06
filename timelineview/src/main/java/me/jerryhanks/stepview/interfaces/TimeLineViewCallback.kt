package me.jerryhanks.stepview.interfaces

import android.view.View
import android.widget.FrameLayout

import me.jerryhanks.stepview.model.TimeLine

/**
 * @author <@Po10cio> on 10/18/17 for StepViewApp
 */

interface TimeLineViewCallback<in T : TimeLine> {
    fun onBindView(model: T, container: FrameLayout, position: Int): View
}
