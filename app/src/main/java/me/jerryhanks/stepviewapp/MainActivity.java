package me.jerryhanks.stepviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.jerryhanks.stepview.IndicatorAdapter;
import me.jerryhanks.stepview.TimeLineView;
import me.jerryhanks.stepview.interfaces.TimeLineViewCallback;
import me.jerryhanks.stepview.model.Status;
import me.jerryhanks.stepview.model.TimeLine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimeLineView timeLineView = findViewById(R.id.timeline_view);

        List<MyTimeLine> timeLines = new ArrayList<>();
        timeLines.add(new MyTimeLine(Status.COMPLETED, "Sample Title 1", "Sample content 1"));
        timeLines.add(new MyTimeLine(Status.UN_COMPLETED, "Sample Title 2", "Sample contentlnldnfln 2sjnlf dfnldnlfndlnf dlflsnfndlnfd fdlfndnlfdf ldnflndlnfd fdlnfldnf dlnflndlfnld"));
        timeLines.add(new MyTimeLine(Status.COMPLETED, "Sample Title 3", "Sample content 3 kdkjsnxjnfs s f sf snfnslkdnflndlkfnd fdnlfndlnflndfldnflndlnf dfldnfndlnfldf dfnldnflndlnfd fndlnfdnlfd lfndnflndf dlnfldnlfnd fdnlfnd"));
        timeLines.add(new MyTimeLine(Status.COMPLETED, "Sample Title 4", "Sample content 4 sjdnlsnfndlnflndlfndllnfdnlfknd"));
        timeLines.add(new MyTimeLine(Status.ATTENTION, "Sample Title 5", "Sample content 5"));
        timeLines.add(new MyTimeLine(Status.COMPLETED, "Sample Title 6", "Sample content 6 dflndlnfndlnflnd dlnflndlnldnfl dlfndnlndlnldnlfndlnfldnlfndln"));
        timeLines.add(new MyTimeLine(Status.UN_COMPLETED, "Sample Title 7", "Sample content 7 sdpfjdfpdfjpdjpojdpofm;dfpmpmdpkdk[k[kdfm;dfm[l[dfl][kdf[omd[pkfkdkf'mdmf';mmvwejfopj2wpowmlskbokr"));


        IndicatorAdapter<MyTimeLine> adapter = new IndicatorAdapter<>(timeLines, this, new TimeLineViewCallback<MyTimeLine>() {
            @Override
            public View onBindView(MyTimeLine model, FrameLayout container, int position) {
                View view = getLayoutInflater()
                        .inflate(me.jerryhanks.stepview.R.layout.sample_time_line,
                                container, false);
                ((TextView) view.findViewById(R.id.tv_title)).setText(model.getTitle());
                ((TextView) view.findViewById(R.id.tv_content)).setText(model.getContent());

                return view;
            }
        });
        timeLineView.setIndicatorAdapter(adapter);
        adapter.swapItems(timeLines);
    }
}
