package me.jerryhanks.timelineview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import me.jerryhanks.timelineview.interfaces.TimeLineViewCallback
import me.jerryhanks.timelineview.model.Status
import me.jerryhanks.timelineview.model.TimeLine

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

        holder.topLineIndicator.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
        holder.bottomLineIndicator.visibility = if (position == itemCount - 1) View.INVISIBLE else View.VISIBLE

        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val child = _callback?.onBindView(timeLine, holder.container, position) ?:
                LayoutInflater.from(holder.itemView.context).inflate(R.layout.sample_time_line, holder.container, false)
        child.layoutParams = params

        //clean the container
        if (holder.container.childCount > 0) {
            holder.container.removeAllViews()
        }

        //start adding new views
        holder.container.addView(child)
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
    private fun updateItem(timeline: T, position: Int) {
        this.timeLines[position] = timeline
        notifyItemChanged(position)
    }


    /**
     * Adds all the given item to the list
     *
     * @param items List of items to be added
     * @param index Index of the list to start the addition
     * */
    @JvmOverloads
    fun addItems(vararg items: T, index: Int = itemCount) {
        //if the index is less than the total item count
        //then we are adding to the top of the list
        val isTop = index < this.itemCount

        if (index < 0) {
            swapItems(items.toList())
            return
        }
        this.timeLines.addAll(index, items.toList())
        notifyItemRangeInserted(index, items.size)
        if (isTop) {
            notifyItemRangeChanged(index, items.size + 1)
        } else {
            notifyItemRangeChanged(index - 1, items.size)
        }


    }

}