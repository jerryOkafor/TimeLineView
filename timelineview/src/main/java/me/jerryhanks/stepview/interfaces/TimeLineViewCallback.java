package me.jerryhanks.stepview.interfaces;

import android.widget.FrameLayout;

/**
 * @author <@Po10cio> on 10/18/17 for StepViewApp
 */

public interface TimeLineViewCallback<T> {
    void onBindView(T model, FrameLayout container);
}
