package me.jerryhanks.stepview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import java.util.ArrayList

import me.jerryhanks.stepview.interfaces.TimeLineViewCallback
import me.jerryhanks.stepview.model.Status
import me.jerryhanks.stepview.model.TimeLine

class IndicatorAdapter<in T : TimeLine>(private val timeLines: MutableList<T>, private val context: Context, private val _callback: TimeLineViewCallback<T>?) : RecyclerView.Adapter<IndicatorHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.timeline_indicator, parent, false)

        return IndicatorHolder(view)
    }

    override fun onBindViewHolder(holder: IndicatorHolder, position: Int) {
        val prevTimeline = if (position > 0) timeLines[position - 1] else null
        if (prevTimeline != null) {
            when (prevTimeline.status) {
                Status.COMPLETED -> holder.topLineIndicator.background = ContextCompat.getDrawable(context, R.drawable.box_enabled)
                Status.UN_COMPLETED, Status.ATTENTION -> holder.topLineIndicator.background = ContextCompat.getDrawable(context, R.drawable.box_disabled)
            }

        }
        val timeLine = timeLines[position]
        when (timeLine.status) {
            Status.COMPLETED -> {
            }
            Status.UN_COMPLETED, Status.ATTENTION -> {
                holder.dotIndicator.background = ContextCompat.getDrawable(context, R.drawable.dot_disabled)
                holder.bottomLineIndicator.background = ContextCompat.getDrawable(context, R.drawable.box_disabled)
            }
        }
        if (position == 0) {
            holder.topLineIndicator.visibility = View.INVISIBLE
        }
        if (position == itemCount - 1) {
            holder.bottomLineIndicator.visibility = View.INVISIBLE
        }

        if (_callback != null) {
            val child = _callback.onBindView(timeLine, holder.container, position)
            val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            child.layoutParams = params
            holder.container.addView(child)
            params.gravity = Gravity.CENTER_VERTICAL or Gravity.START
            return
        }

        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val view = LayoutInflater.from(holder.itemView.context).inflate(R.layout.sample_time_line, holder.container, false)
        view.layoutParams = params
        holder.container.addView(view)
        params.gravity = Gravity.CENTER_VERTICAL or Gravity.START


    }

    override fun getItemCount(): Int {
        return this.timeLines.size
    }

    /**
     * Swaps the old items with the new one
     *
     * @param timeLines The List of the new items that we want to swap
     * */
    fun swapItems(timeLines: List<T>) {
        this.timeLines.clear()
        this.timeLines.addAll(timeLines)
        notifyDataSetChanged()
    }

    /**
     * Replaces the item at the given position with the
     * given item
     *
     * @param timeline The item to be replaces
     * @param position The index of the item to be replaced
     * */
    fun updatItem(timeline: T, position: Int) {
        this.timeLines[position] = timeline
        notifyItemChanged(position)
    }


    /**
     * Adds all the given item to the list
     *
     * @param items List of items to be added
     * */
    fun addItems(vararg items: T) {
        this.timeLines.addAll(items)
        notifyDataSetChanged()
    }
}