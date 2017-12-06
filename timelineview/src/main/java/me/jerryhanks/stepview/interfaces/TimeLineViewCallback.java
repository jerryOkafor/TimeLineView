package me.jerryhanks.stepview.interfaces;

import android.view.View;
import android.widget.FrameLayout;

import me.jerryhanks.stepview.model.TimeLine;

/**
 * @author <@Po10cio> on 10/18/17 for StepViewApp
 */

public interface TimeLineViewCallback<T extends TimeLine> {
    View onBindView(T model, FrameLayout container, int position);
}
