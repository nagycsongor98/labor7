package com.nagycsongor.form;

public class FormModel {
    protected int TYPE;
    protected String tostring;

    public FormModel(int TYPE) {
        this.TYPE = TYPE;
    }

    public int getTYPE() {
        return TYPE;
    }

    public String getTostring() {
        return tostring;
    }

    public void setTostring(String tostring) {
        this.tostring = tostring;
    }
}
