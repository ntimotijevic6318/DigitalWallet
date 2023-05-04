package com.example.wallet.view.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wallet.LoginActivity;
import com.example.wallet.R;
import com.example.wallet.model.User;
import com.example.wallet.view.activities.EditProfile;
import com.google.gson.Gson;

public class ProfileFragment extends Fragment {


    private static final String PROFILE_FRAGMENT_TAG = "PROFILE_FRAGMENT";
    private static final String EDIT_PROFILE_FRAGMENT_TAG = "EDIT_PROFILE_FRAGMENT" ;

    public ProfileFragment(){
        super(R.layout.profile_fragment_layout);
    }


    TextView textViewName , getTextViewsurname , bank;
    Button logout;
    Button edit;


    SharedPreferences sharedPreferences;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       sharedPreferences = this.getActivity().getSharedPreferences(this.getActivity().getPackageName() , Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString(LoginActivity.sharedPreferencesKey, "");
        User user = gson.fromJson(json, User.class);
        init(view);

        textViewName.setText(user.getName());
        getTextViewsurname.setText(user.getSurname());
        bank.setText(user.getBank());
    }

    private void init(View view) {
        initView(view);
        initListener(view);
    }

    private void initView(View view) {
        textViewName = view.findViewById(R.id.textViewIme);
        getTextViewsurname = view.findViewById(R.id.textViewPrezime);
        bank = view.findViewById(R.id.textViewBanka);
        logout = view.findViewById(R.id.logoutBtn);
        edit = view.findViewById(R.id.editBtn);
    }

    @SuppressLint("ResourceAsColor")
    private void initListener(View view) {
        logout.setOnClickListener(v->{
            Intent intent = new Intent(getContext() , LoginActivity.class);
            Context context = getContext().getApplicationContext();
            CharSequence text = getString(R.string.logout);
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            ViewGroup group = (ViewGroup) toast.getView();
            group.getBackground().setColorFilter(R.color.black , PorterDuff.Mode.DARKEN);
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(25);
            messageTextView.setTextColor(R.color.white);
            toast.show();
            if(!toast.getView().isShown())
            {
                startActivity(intent);
                sharedPreferences.edit().clear().apply();
            }
        });


        edit.setOnClickListener(v->{
        Intent intent = new Intent(getContext() , EditProfile.class);
        startActivityForResult(intent , 222);
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 222:
                if(resultCode == Activity.RESULT_OK){

                    Gson gson = new Gson();

                    String json = sharedPreferences.getString(LoginActivity.sharedPreferencesKey, "");
                    User user = gson.fromJson(json, User.class);

                    textViewName.setText(user.getName());
                    getTextViewsurname.setText(user.getSurname());
                    bank.setText(user.getBank());

                }
        }
    }


}
