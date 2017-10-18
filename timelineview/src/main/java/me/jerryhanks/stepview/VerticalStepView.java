package me.jerryhanks.stepview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.jerryhanks.stepview.interfaces.TimeLineViewCallback;
import me.jerryhanks.stepview.model.TimeLine;

/**
 * @author <@Po10cio> on 10/17/17 for StepViewApp
 *         <p>
 *         Copyright (c) 2017 MAX. All rights reserved.
 */

public class VerticalStepView extends RelativeLayout {
    private IndicatorAdapter indicatorAdapter;
    private TimeLineViewCallback _callback;

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
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        this.indicatorAdapter = new IndicatorAdapter(Collections.emptyList(), getContext());
        recyclerView.setAdapter(indicatorAdapter);

    }

    public void setTimeLines(List<? extends TimeLine> timeLines) {
        this.indicatorAdapter.swapItems(timeLines);
    }

    public void setTimelineCallback(TimeLineViewCallback callback) {
        _callback = callback;

    }

    class IndicatorHolder extends RecyclerView.ViewHolder {

        final View topLineIndicator;
        final View dotIndicator;
        final View bottomLineIndicator;
        final FrameLayout container;
        private Context context;


        IndicatorHolder(View itemView) {
            super(itemView);
            this.container = itemView.findViewById(R.id.content);
            this.topLineIndicator = itemView.findViewById(R.id.line_indicator_top);
            this.dotIndicator = itemView.findViewById(R.id.dot_indicator);
            this.bottomLineIndicator = itemView.findViewById(R.id.line_indicator_bottom);

        }

        public void setContext(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return context;
        }
    }

    class IndicatorAdapter<T extends TimeLine> extends RecyclerView.Adapter<IndicatorHolder> {

        private List<T> timeLines = new ArrayList<>();
        private Context context;

        IndicatorAdapter(List<T> timeLines, Context context) {
            this.timeLines = timeLines;
            this.context = context;
        }

        @Override
        public IndicatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_step_indicator, parent, false);

            IndicatorHolder holder = new IndicatorHolder(view);
            holder.setContext(parent.getContext());
            return holder;
        }

        @Override
        public void onBindViewHolder(IndicatorHolder holder, int position) {
            T prevTimeline = position > 0 ? timeLines.get(position - 1) : null;
            if (prevTimeline != null) {
                switch (prevTimeline.getStatus()) {
                    case COMPLETED:
                        holder.topLineIndicator.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.box_enabled));
                        break;
                    case UN_COMPLETED:
                    case ATTENTION:
                        holder.topLineIndicator.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.box_disabled));
                        break;
                }

            }
            T timeLine = timeLines.get(position);
            switch (timeLine.getStatus()) {
                case COMPLETED:
                    break;
                case UN_COMPLETED:
                case ATTENTION:
                    holder.dotIndicator.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.dot_disabled));
                    holder.bottomLineIndicator.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.box_disabled));
                    break;
            }
            if (position == 0) {
                holder.topLineIndicator.setVisibility(View.INVISIBLE);
            }
            if (position == getItemCount() - 1) {
                holder.bottomLineIndicator.setVisibility(View.INVISIBLE);
            }

            if (_callback != null) {
                _callback.onBindView(timeLine, holder.container);
                return;
            }

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            View view = LayoutInflater.from(holder.getContext()).inflate(R.layout.sample_time_line, holder.container, false);
            view.setLayoutParams(params);
            holder.container.addView(view);
            params.gravity = Gravity.CENTER_VERTICAL | Gravity.START;


        }

        @Override
        public int getItemCount() {
            return this.timeLines.size();
        }

        void swapItems(List<T> timeLines) {
            this.timeLines = timeLines;
            notifyDataSetChanged();
        }

        Context getContext() {
            return context;
        }

    }
}



