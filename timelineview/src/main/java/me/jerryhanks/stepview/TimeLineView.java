package me.jerryhanks.stepview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author <@Po10cio> on 10/17/17 for StepViewApp
 *         <p>
 *         Copyright (c) 2017 MAX. All rights reserved.
 */

public class TimeLineView extends RelativeLayout {
    private RecyclerView recyclerView;

    public TimeLineView(Context context) {
        super(context);
        init();
    }


    public TimeLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimeLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.timeline_view, this);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public void setIndicatorAdapter(IndicatorAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

}



