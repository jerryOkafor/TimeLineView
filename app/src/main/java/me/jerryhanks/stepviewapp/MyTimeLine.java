package me.jerryhanks.stepviewapp;

import me.jerryhanks.stepview.model.Status;
import me.jerryhanks.stepview.model.TimeLine;

/**
 * @author <@Po10cio> on 10/18/17 for StepViewApp
 */

public class MyTimeLine extends TimeLine {
    private String title;
    private String content;

    public MyTimeLine(Status status, String title, String content) {
        super(status);
        this.title = title;
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTimeLine)) return false;

        MyTimeLine that = (MyTimeLine) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyTimeLine{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
