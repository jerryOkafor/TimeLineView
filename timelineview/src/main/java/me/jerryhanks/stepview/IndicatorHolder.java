package me.jerryhanks.stepview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

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

    void setContext(Context context) {
        this.context = context;
    }

    Context getContext() {
        return context;
    }
}