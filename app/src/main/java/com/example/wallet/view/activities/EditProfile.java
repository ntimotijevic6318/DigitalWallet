package com.example.wallet.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.wallet.LoginActivity;
import com.example.wallet.R;
import com.example.wallet.model.User;
import com.example.wallet.view.fragments.ProfileFragment;
import com.google.gson.Gson;

public class EditProfile extends AppCompatActivity {


    User user;
    EditText name , surname , bank;
    Button confirm , giveup;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sharedPreferences = getSharedPreferences(getPackageName() , MODE_PRIVATE);
        initall();
    }

    private void initall() {
        initView();
        initListeners();
    }

    private void initListeners() {
        giveup.setOnClickListener(v->finish());
        confirm.setOnClickListener(v->{

            user.setName(name.getText().toString());
            user.setSurname(surname.getText().toString());
            user.setBank(bank.getText().toString());

            Gson gson = new Gson();
            String json = gson.toJson(user);
            sharedPreferences.edit().putString(LoginActivity.sharedPreferencesKey , json).apply();
            setResult(Activity.RESULT_OK);



            finish();
        });
    }

    private void initView() {
        name = findViewById(R.id.textViewImeProfile);
        surname  = findViewById(R.id.textViewPrezimeProfile);
        bank = findViewById(R.id.textViewBankaProfile);

        confirm = findViewById(R.id.editBtnProfile);
        giveup  = findViewById(R.id.buttonGiveUpProfile);

        Gson gson = new Gson();
        String json = sharedPreferences.getString(LoginActivity.sharedPreferencesKey, "");
        user = gson.fromJson(json, User.class);

        name.setText(user.getName());
        surname.setText(user.getSurname());
        bank.setText(user.getBank());

    }
}