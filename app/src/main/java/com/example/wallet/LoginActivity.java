package com.example.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.wallet.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private Button submit;
    private EditText name, surname, bank, password;
    private static final String correctPassword = "RMA2021";
    private boolean validForm;
    public static String sharedPreferencesKey="User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intit();
    }

    private void intit() {
        initView();
        intiListeners();
    }

    private void intiListeners() {

        submit.setOnClickListener(v -> {
            validForm=true;
            validate();

            if(validForm)
            {   Intent intent = new Intent(this , BottomNavigationActivity.class);
                User user = new User(name.getText().toString() , surname.getText().toString() , bank.getText().toString() , password.toString());
                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() , MODE_PRIVATE);
                Gson gson = new Gson();
                String json = gson.toJson(user);
                sharedPreferences.edit().putString(sharedPreferencesKey , json).apply();
                startActivity(intent);
            }
        });
    }

    private void initView() {

        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        bank = findViewById(R.id.bank);
        password = findViewById(R.id.password);

    }

    private void validate() {

        String requireErr = getString(R.string.required);
        String lengthErr = getString(R.string.lentherr);
        String wrongpass = getString(R.string.wrongpass);

        List<EditText> editTexts = new ArrayList<>();
        editTexts.add(name);
        editTexts.add(surname);
        editTexts.add(bank);
        editTexts.add(password);

        for (EditText element : editTexts) {
            if (element.length() == 0) {
                element.setError(requireErr);
                validForm = false;
            } else if (element.getResources().getResourceEntryName(element.getId()).equals("password")) {
                if (element.length() < 5) {
                    element.setError(lengthErr);
                    validForm = false;
                } else if(!element.getText().toString().equals(correctPassword)) {
                    element.setError(wrongpass);
                    validForm = false;
                }
            }
        }



        /*
        if(name.length() == 0){
            name.setError(requireErr);
        }
        if(surname.length() == 0)
        {
            surname.setError(requireErr);
        }
        if(bank.length() == 0){
            bank.setError(requireErr);
        }
        if(password.length() == 0)
        {
            password.setError(requireErr);
        }else if(password.length() < 5){
            password.setError(lengthErr);
        }
        else if(!password.getText().toString().equals(correctPassword)) {
            password.setError(wrongpass);
        }

    }
    */

    }
}