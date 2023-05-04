package com.example.wallet.view.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wallet.R;
import com.example.wallet.viewmodel.SharedViewModel;
import com.example.wallet.viewmodel.ShowViewModel;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class InputFragment extends Fragment {

    SharedViewModel sharedViewModel;
    ShowViewModel showViewModel;


    Spinner spinner;
    EditText enterTitle;
    EditText enterAmount;
    EditText enterDescription;
    CheckBox audiocheck;
    ImageButton mic;
    ImageButton micRecording;
    Button buttonAdd;

    private final  int PERMISSION_ALL = 1;
    private final String[] permission= {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private MediaRecorder mediaRecorder;
    private File file;

    public InputFragment(){
         super(R.layout.input_fragment_layout);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showViewModel = new ViewModelProvider(this).get(ShowViewModel.class);
        // sa requireActivity() u ViewModelProvider viewModel vezujemo za activity
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // moramo da prosledjujemo view kroz metode jer nismo u activity, pa po default-u nemamo pristup metodama kao sto su findViewById
        init(view);
    }

    private void init(View view) {
        initView(view);
        initListeners();
        initObservers(view);
        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext() , R.array.option_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initObservers(View view) {
    }

    private void init(){
        enterDescription.setVisibility(View.GONE);
        mic.setVisibility(View.VISIBLE);
    }

    private void  initFolder() {
     File folder = new File(getContext().getFilesDir() , "sounds");
     if(!folder.exists()) folder.mkdir();
     file = new File(folder , "record.3gp");
    }


    private void initMediaRecorder(File file) {
        mediaRecorder = new MediaRecorder();
        // Postavljanje parametara za mediaRecorder
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(file);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_ALL){
           if(grantResults.length > 0){
               StringBuilder denied=  new StringBuilder();
               for(int i=0 ; i<grantResults.length ;i++){
               if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                   denied.append("\n").append(permissions[i]);
               }
               }
               if (denied.toString().length() == 0) {
                   // Ukoliko nema odbijenih dozvola, nastavljamo dalje
                   init();
               }else {
                   Toast.makeText(getContext(), "Missing permissions! " + denied.toString(), Toast.LENGTH_LONG).show();
                   // Ukoliko ima odbijenih dozvola ispisujemo poruku i zatvaramo activity

               }
           }
        }
    }

    private void initListeners() {




        audiocheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    if(hasPermission(getContext() , permission)) {
                       init();
                    }
                    else{
                      requestPermissions(permission,  PERMISSION_ALL);
                    }

                } else {
                    enterDescription.setVisibility(View.VISIBLE);
                    mic.setVisibility(View.GONE);
                }
            }
        });

        buttonAdd.setOnClickListener(v-> {
            if(validate()) {

                String spinnertext = spinner.getSelectedItem().toString();
                String title = enterTitle.getText().toString();
                String amount = enterAmount.getText().toString();
                String description = enterDescription.getText().toString();

                if (spinnertext.equals("Prihod")) {
                    sharedViewModel.addItemIncome(title, amount , description , file);
                } else {
                    sharedViewModel.addItemExpense(title, amount,  description , file);
                }
                clearForm();
            }
        });

        mic.setOnClickListener(v->{
            try {
                initFolder();
                mic.setVisibility(View.GONE);
                micRecording.setVisibility(View.VISIBLE);
                initMediaRecorder(file);
                mediaRecorder.prepare();
                mediaRecorder.start();
            }catch(IOException e){
                e.printStackTrace();
            }
        });

        micRecording.setOnClickListener(v->{
            mic.setVisibility(View.VISIBLE);
            micRecording.setVisibility(View.GONE);
            mediaRecorder.release();
            mediaRecorder = null;
        });
    }



    private boolean hasPermission(Context context, String... permission) {
        for(String perm : permission){
         if(ActivityCompat.checkSelfPermission(context , perm) != PackageManager.PERMISSION_GRANTED)
             return false;
        }
        return true;
    }

    private void clearForm() {

        Context context = getContext().getApplicationContext();
        CharSequence text = getString(R.string.savedData);
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        enterTitle.setText("");
        enterAmount.setText("");
        enterDescription.setText("");
        audiocheck.setSelected(false);
    }


    private boolean validate() {

        if(enterTitle.length() == 0){
            enterTitle.setError(getString(R.string.required));
            return false ;
        }

        if(enterAmount.length() == 0){
            enterAmount.setError(getString(R.string.required));
            return false;
        }


        if(!audiocheck.isChecked()) {
            if (enterDescription.length() == 0) {
                enterDescription.setError(getString(R.string.required));
                return false;
            }
        }

        return true;
    }

    private void initView(View view) {
     spinner = view.findViewById(R.id.spinner);
     enterTitle = view.findViewById(R.id.enterTitle);
     enterAmount = view.findViewById(R.id.enterAmount);
     audiocheck = view.findViewById(R.id.AudioCheck);
     enterDescription = view.findViewById(R.id.enterDescription);
     buttonAdd = view.findViewById(R.id.addButton);
     mic  = view.findViewById(R.id.mic);
     micRecording = view.findViewById(R.id.micRecording);
    }
}
