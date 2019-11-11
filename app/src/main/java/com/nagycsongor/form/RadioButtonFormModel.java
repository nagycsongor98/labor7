package com.nagycsongor.form;

public class RadioButtonFormModel extends FormModel {
    private String string;
    private String name;
    public RadioButtonFormModel(String name) {
        super(FormAdapter.INPUT_VWH_RADIO_BUTTON_TYPE);
        this.name = name;
    }
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
        super.setTostring(string);
    }


    public String getName() {
        return name;
    }
}
