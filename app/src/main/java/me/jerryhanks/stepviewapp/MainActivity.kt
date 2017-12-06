package me.jerryhanks.stepviewapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

import java.util.ArrayList

import me.jerryhanks.stepview.IndicatorAdapter
import me.jerryhanks.stepview.TimeLineView
import me.jerryhanks.stepview.interfaces.TimeLineViewCallback
import me.jerryhanks.stepview.model.Status
import me.jerryhanks.stepview.model.TimeLine

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeLineView = findViewById<TimeLineView>(R.id.timeline_view)

        val timeLines = ArrayList<MyTimeLine>()
        timeLines.add(MyTimeLine(Status.COMPLETED, "Sample Title 1", "Sample content 1"))
        timeLines.add(MyTimeLine(Status.UN_COMPLETED, "Sample Title 2", "Sample contentlnldnfln 2sjnlf dfnldnlfndlnf dlflsnfndlnfd fdlfndnlfdf ldnflndlnfd fdlnfldnf dlnflndlfnld"))
        timeLines.add(MyTimeLine(Status.COMPLETED, "Sample Title 3", "Sample content 3 kdkjsnxjnfs s f sf snfnslkdnflndlkfnd fdnlfndlnflndfldnflndlnf dfldnfndlnfldf dfnldnflndlnfd fndlnfdnlfd lfndnflndf dlnfldnlfnd fdnlfnd"))
        timeLines.add(MyTimeLine(Status.COMPLETED, "Sample Title 4", "Sample content 4 sjdnlsnfndlnflndlfndllnfdnlfknd"))
        timeLines.add(MyTimeLine(Status.ATTENTION, "Sample Title 5", "Sample content 5"))
        timeLines.add(MyTimeLine(Status.COMPLETED, "Sample Title 6", "Sample content 6 dflndlnfndlnflnd dlnflndlnldnfl dlfndnlndlnldnlfndlnfldnlfndln"))
        timeLines.add(MyTimeLine(Status.UN_COMPLETED, "Sample Title 7", "Sample content 7 sdpfjdfpdfjpdjpojdpofm;dfpmpmdpkdk[k[kdfm;dfm[l[dfl][kdf[omd[pkfkdkf'mdmf';mmvwejfopj2wpowmlskbokr"))


        val adapter = IndicatorAdapter(timeLines, this, object : TimeLineViewCallback<MyTimeLine> {
            override fun onBindView(model: MyTimeLine, container: FrameLayout, position: Int): View {
                val view = layoutInflater
                        .inflate(me.jerryhanks.stepview.R.layout.sample_time_line,
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
