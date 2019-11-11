package com.nagycsongor.form;

public class SpinnerFormModel extends FormModel {
    private String string;
    private int itemsName;
    private String name;
    public SpinnerFormModel(int itemsName , String name) {
        super(FormAdapter.INPUT_VWH_SPINNER_TYPE);
        this.itemsName = itemsName;
        this.name = name;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
        super.setTostring(string);
    }

    public int getItemsName() {
        return itemsName;
    }

    public String getName() {
        return name;
    }


}
