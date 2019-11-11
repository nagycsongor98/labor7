package com.nagycsongor.form;

public class CheckBoxFormModel extends FormModel {
    private String string;
    private String name;
    public CheckBoxFormModel(String name) {
        super(FormAdapter.INPUT_VWH_CHECK_BOX_TYPE);
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
