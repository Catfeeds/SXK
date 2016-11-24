package com.example.cfwifine.sxk.Section.PublishNC.Model;

/**
 * Created by cfwifine on 16/11/23.
 */

public class TestModel {
    String text;
    boolean state;

    public TestModel(String text,boolean state){
        this.text = text;
        this.state = state;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
