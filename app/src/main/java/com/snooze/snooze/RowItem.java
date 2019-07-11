package com.snooze.snooze;

public class RowItem {

    private String text;
    private Boolean available;

    public Integer getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(Integer timeFrame) {
        this.timeFrame = timeFrame;
    }

    private Integer timeFrame;

    RowItem(String text2, Boolean available2, Integer timeFrame){
        this.text = text2;
        this.available = available2;
        this.timeFrame = timeFrame;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
