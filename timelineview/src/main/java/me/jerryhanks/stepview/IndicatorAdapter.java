package me.jerryhanks.stepview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import me.jerryhanks.stepview.interfaces.TimeLineViewCallback;
import me.jerryhanks.stepview.model.TimeLine;

public class IndicatorAdapter<T extends TimeLine> extends RecyclerView.Adapter<IndicatorHolder> {

    private List<T> timeLines = new ArrayList<>();
    private Context context;
    private TimeLineViewCallback<T> _callback;

    public IndicatorAdapter(List<T> timeLines, Context context, TimeLineViewCallback<T> callback) {
        this.timeLines = timeLines;
        this.context = context;
        this._callback = callback;
    }

    @Override
    public IndicatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_indicator, parent, false);

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
            View child = _callback.onBindView(timeLine, holder.container, position);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            child.setLayoutParams(params);
            holder.container.addView(child);
            params.gravity = Gravity.CENTER_VERTICAL | Gravity.START;
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

    public void swapItems(List<T> timeLines) {
        this.timeLines = timeLines;
        notifyDataSetChanged();
    }

    private Context getContext() {
        return context;
    }
}