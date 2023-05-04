package com.example.wallet.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wallet.R;
import com.example.wallet.model.Item;
import com.example.wallet.view.fragments.IncomFragment;
import com.example.wallet.viewmodel.SharedViewModel;

public class EditActivity extends AppCompatActivity {


    EditText title;
    EditText amount;
    EditText description;

    Button confirm;
    Button giveup;


    Item item;
    SharedViewModel sharedViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        initall();
    }

    private void initall() {
        initView();
        initListeners();
        parseIntent();
    }


    private void initListeners() {
        giveup.setOnClickListener(v-> finish());
         confirm.setOnClickListener(v->{

              Intent returnIntent = new Intent(this , IncomFragment.class);
              item.setTitle(title.getText().toString());
              item.setAmount(amount.getText().toString());
              item.setDescription(description.getText().toString());

              returnIntent.putExtra("message" , item);
              setResult(Activity.RESULT_OK , returnIntent);
              finish();
         });
    }


    private void parseIntent() {
        Intent intent = getIntent();
        if(intent.getExtras() != null){
           item =(Item)intent.getExtras().getSerializable(IncomFragment.EditKey);
            title.setText(item.getTitle());
            amount.setText(item.getAmount());
            description.setText(item.getDescription());
        }
    }

    private void initView() {
        title = findViewById(R.id.editTextNaslov);
        amount = findViewById(R.id.editTextKolicina);
        description= findViewById(R.id.editTextOpis);


        confirm = findViewById(R.id.buttonConfirm);
        giveup = findViewById(R.id.buttonGiveUp);
    }




}