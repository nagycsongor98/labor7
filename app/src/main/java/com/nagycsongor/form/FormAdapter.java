package com.nagycsongor.form;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int INPUT_VWH_TEXT_TYPE =0;
    public static final int INPUT_VWH_SPINNER_TYPE =1;
    public static final int INPUT_VWH_BUTTON_TYPE =2;
    public static final int INPUT_VWH_RADIO_BUTTON_TYPE =3;
    public static final int INPUT_VWH_CHECK_BOX_TYPE =4;
    private ArrayList<FormModel> data;
    private Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference firebaseDb = database.getReference("students");

    public FormAdapter(ArrayList<FormModel> data, Context context) {
        this.data = data;
        this.context = context;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType){
            case INPUT_VWH_TEXT_TYPE: return new FormAdapter.MyViewHolderText(inflater.inflate(R.layout.text_item,parent,false));
            case INPUT_VWH_SPINNER_TYPE : return new FormAdapter.MyViewHolderSpinner(inflater.inflate(R.layout.spinner_item,parent,false));
            case INPUT_VWH_BUTTON_TYPE : return new FormAdapter.MyViewHolderButton(inflater.inflate(R.layout.button_item,parent,false));
            case INPUT_VWH_RADIO_BUTTON_TYPE : return new FormAdapter.MyViewHolderRadioButton(inflater.inflate(R.layout.radio_button_item,parent,false));
            case INPUT_VWH_CHECK_BOX_TYPE : return new FormAdapter.MyViewHolderCheckBox(inflater.inflate(R.layout.check_box_item,parent,false));
        }
    return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (data.get(position).getTYPE()){
            case INPUT_VWH_TEXT_TYPE: {
                final TextFormModel model = (TextFormModel)data.get(position);
                final MyViewHolderText myViewHolderText = (MyViewHolderText)holder;
                myViewHolderText.editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        model.setString(myViewHolderText.editText.getText().toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            }
            case INPUT_VWH_SPINNER_TYPE: {
                final SpinnerFormModel model = (SpinnerFormModel)data.get(position);
                final MyViewHolderSpinner myViewHolderSpinner = (MyViewHolderSpinner)holder;
                int itemName = model.getItemsName();
                myViewHolderSpinner.titleTextView.setText(model.getName());
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                        itemName, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myViewHolderSpinner.spinner.setAdapter(adapter);
                myViewHolderSpinner.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selected = parent.getItemAtPosition(position).toString();
                        model.setString(selected);
                    }
                    @Override
                    public void onNothingSelected(AdapterView <?> parent) {
                    }
                });
                break;
            }
            case INPUT_VWH_RADIO_BUTTON_TYPE:{
                final RadioButtonFormModel model = (RadioButtonFormModel)data.get(position);
                final MyViewHolderRadioButton myViewHolderRadioButton = (MyViewHolderRadioButton)holder;
                myViewHolderRadioButton.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == myViewHolderRadioButton.maleRadioButton.getId()) {
                            //Toast.makeText(context, "male", Toast.LENGTH_SHORT).show();
                            model.setString("Male");
                        }else {
                            //Toast.makeText(context, "female", Toast.LENGTH_SHORT).show();
                            model.setString("Female");
                        }
                    }
                });
                break;
            }
            case INPUT_VWH_CHECK_BOX_TYPE:{
                final CheckBoxFormModel model = (CheckBoxFormModel)data.get(position);
                final MyViewHolderCheckBox myViewHolderCheckBox = (MyViewHolderCheckBox)holder;
                myViewHolderCheckBox.checkBox.setText(model.getName());
                model.setString("No");
                myViewHolderCheckBox.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            model.setString("Yes");
                        }else {
                            model.setString("No");
                        }
                    }
                });
                break;
            }
            case INPUT_VWH_BUTTON_TYPE:{
                final MyViewHolderButton myViewHolderButton = (MyViewHolderButton)holder;
                myViewHolderButton.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        String string = "";
//                        for (int i = 0 ;i<data.size()-1;i++){
//                            string = string + " " +data.get(i).getTostring();
//                        }
//                        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();

                        String id = firebaseDb.push().getKey();
                        FormClass formClass = new FormClass(id);
                        formClass.setName(data.get(0).getTostring());
                        formClass.setLocation(data.get(1).getTostring());
                        formClass.setGender(data.get(2).getTostring());
                        formClass.setHobby(data.get(3).getTostring());
                        formClass.setDepartment(data.get(4).getTostring());
                        firebaseDb.child(id).setValue(formClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context,"Form added!",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getTYPE();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolderText extends RecyclerView.ViewHolder {
        EditText editText;
        public MyViewHolderText(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.editText);
        }
    }

    public class MyViewHolderSpinner extends RecyclerView.ViewHolder {
        Spinner spinner;
        TextView titleTextView;
        public MyViewHolderSpinner(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinner);
            titleTextView = itemView.findViewById(R.id.titleTextView);
        }
    }
    public class MyViewHolderButton extends RecyclerView.ViewHolder {
        Button button;
        public MyViewHolderButton(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }
    }

    public class MyViewHolderRadioButton extends RecyclerView.ViewHolder {
        RadioGroup radioGroup;
        RadioButton maleRadioButton;
        RadioButton femaleRadioButton;
        public MyViewHolderRadioButton(@NonNull View itemView) {
            super(itemView);
            radioGroup = itemView.findViewById(R.id.radioButton);
            maleRadioButton = itemView.findViewById(R.id.radioMale);
            femaleRadioButton = itemView.findViewById(R.id.radioFemale);
        }
    }
    public class MyViewHolderCheckBox extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public MyViewHolderCheckBox(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
