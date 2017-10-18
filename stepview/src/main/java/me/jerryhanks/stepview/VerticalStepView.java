package me.jerryhanks.stepview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * @author <@Po10cio> on 10/17/17 for StepViewApp
 *         <p>
 *         Copyright (c) 2017 MAX. All rights reserved.
 */

public class VerticalStepView extends RelativeLayout {
    private RecyclerView recyclerView;

    public VerticalStepView(Context context) {
        super(context);
        init();
    }


    public VerticalStepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.vertical_step_view, this);
        this.recyclerView = findViewById(R.id.recycler);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(new IndicatorAdapter());

    }
}


class IndicatorHolder extends RecyclerView.ViewHolder {

    FrameLayout container;
    private Context context;

    IndicatorHolder(View itemView) {
        super(itemView);
        this.container = itemView.findViewById(R.id.content);

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}

class IndicatorAdapter extends RecyclerView.Adapter<IndicatorHolder> {

    @Override
    public IndicatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_step_indicator, parent, false);

        IndicatorHolder holder = new IndicatorHolder(view);
        holder.setContext(parent.getContext());
        return holder;
    }

    @Override
    public void onBindViewHolder(IndicatorHolder holder, int position) {
        View view = LayoutInflater.from(holder.getContext()).inflate(R.layout.sample_time_line, holder.container, false);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        holder.container.addView(view);
        params.gravity = Gravity.CENTER;


    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
