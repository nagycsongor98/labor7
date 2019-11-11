package com.nagycsongor.form;

public class TextFormModel extends FormModel {
    private String string = "";
    public TextFormModel() {
        super(FormAdapter.INPUT_VWH_TEXT_TYPE);
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
        super.setTostring(string);
    }
}
