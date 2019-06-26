package com.snooze.snooze;

public class RowItem {

    private String text;
    private Boolean available;

    RowItem(String text2, Boolean available2){
        this.text = text2;
        this.available = available2;
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
