package me.jerryhanks.stepviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import me.jerryhanks.stepview.VerticalStepView;
import me.jerryhanks.stepview.model.Status;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VerticalStepView timeLineView = findViewById(R.id.timeline_view);

        List<MyTimeLine> timeLines = new ArrayList<>();
        timeLines.add(new MyTimeLine(Status.COMPLETED, "Sample Title 1", "Sample content 1"));
        timeLines.add(new MyTimeLine(Status.UN_COMPLETED, "Sample Title 2", "Sample content 2"));
        timeLines.add(new MyTimeLine(Status.COMPLETED, "Sample Title 3", "Sample content 3"));
        timeLines.add(new MyTimeLine(Status.COMPLETED, "Sample Title 4", "Sample content 4"));
        timeLines.add(new MyTimeLine(Status.ATTENTION, "Sample Title 5", "Sample content 5"));
        timeLines.add(new MyTimeLine(Status.COMPLETED, "Sample Title 6", "Sample content 6"));
        timeLines.add(new MyTimeLine(Status.UN_COMPLETED, "Sample Title 7", "Sample content 7"));


        timeLineView.setTimeLines(timeLines);
    }
}
