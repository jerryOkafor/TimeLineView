package me.jerryhanks.stepviewapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import me.jerryhanks.stepview.IndicatorAdapter
import me.jerryhanks.stepview.interfaces.TimeLineViewCallback
import me.jerryhanks.stepview.model.Status
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeLineView = timelineView

        val timeLines = ArrayList<MyTimeLine>()
        timeLines.add(MyTimeLine(Status.COMPLETED, getString(R.string.title_1), getString(R.string.content_1)))
        timeLines.add(MyTimeLine(Status.UN_COMPLETED, getString(R.string.title_2), getString(R.string.content_2)))
        timeLines.add(MyTimeLine(Status.COMPLETED, getString(R.string.title_3), getString(R.string.content_3)))
        timeLines.add(MyTimeLine(Status.COMPLETED, getString(R.string.title_4), getString(R.string.content_4)))
        timeLines.add(MyTimeLine(Status.ATTENTION, getString(R.string.title_5), getString(R.string.content_5)))
        timeLines.add(MyTimeLine(Status.COMPLETED, getString(R.string.title_6), getString(R.string.content_6)))


        val adapter = IndicatorAdapter(timeLines, this, object : TimeLineViewCallback<MyTimeLine> {
            override fun onBindView(model: MyTimeLine, container: FrameLayout, position: Int): View {
                val view = layoutInflater
                        .inflate(R.layout.sample_time_line,
                                container, false)
                (view.findViewById<View>(R.id.tv_title) as TextView).text = model.title
                (view.findViewById<View>(R.id.tv_content) as TextView).text = model.content

                return view
            }
        })
        timeLineView.setIndicatorAdapter(adapter)
        adapter.swapItems(timeLines)
    }
}
