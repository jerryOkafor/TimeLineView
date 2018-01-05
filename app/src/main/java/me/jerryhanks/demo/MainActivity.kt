package me.jerryhanks.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import me.jerryhanks.timelineview.IndicatorAdapter
import me.jerryhanks.timelineview.interfaces.TimeLineViewCallback
import me.jerryhanks.timelineview.model.Status

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: IndicatorAdapter<MyTimeLine>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeLines = mutableListOf<MyTimeLine>()
                .apply {
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_1), getString(R.string.s_content_1)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_2), getString(R.string.s_content_2)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_3), getString(R.string.s_content_3)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_4), getString(R.string.s_content_4)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_5), getString(R.string.s_content_5)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_6), getString(R.string.s_content_6)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_7), getString(R.string.s_content_7)))

                    // more data
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_8), getString(R.string.s_content_8)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_9), getString(R.string.s_content_9)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_10), getString(R.string.s_content_10)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_11), getString(R.string.s_content_11)))
                    add(MyTimeLine(Status.COMPLETED, getString(R.string.s_title_12), getString(R.string.s_content_12)))
                }


        adapter = IndicatorAdapter(mutableListOf(), this, object : TimeLineViewCallback<MyTimeLine> {
            override fun onBindView(model: MyTimeLine, container: FrameLayout, position: Int): View {
                val view = layoutInflater
                        .inflate(R.layout.sample_time_line,
                                container, false)

                (view.findViewById<TextView>(R.id.tv_title)).text = model.title
                (view.findViewById<TextView>(R.id.tv_content)).text = model.content

                return view
            }
        })
        timelineView.setIndicatorAdapter(adapter)
        adapter.swapItems(timeLines)

        //set the title
//        caption.text = getString(R.string.timeline_of_world_war_i)
        caption.text = getString(R.string.delivery_status)

        //set add buttons onclick lsisteners
        btnAddToTop.setOnClickListener({ addToTop() })
        btnAddToBottom.setOnClickListener({ addToBottom() })

    }

    /**
     * Adds an item to the top of the list and scroll to the top
     */
    fun addToTop() {
        val timeLine = MyTimeLine(Status.ATTENTION, "New Top One", "heiheihei")
        adapter.addItems(timeLine, index = 2)
        timelineView.scrollToTop()
    }

    /**
     * Add an item to the bottom of the list and scroll to the bottom
     */
    fun addToBottom() {
        val timeLine = MyTimeLine(Status.ATTENTION, "New Bottom One", "hahahaha")
        adapter.addItems(timeLine)
        timelineView.scrollToBottom()
    }
}
