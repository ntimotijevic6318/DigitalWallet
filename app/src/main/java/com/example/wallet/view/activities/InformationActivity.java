package com.example.wallet.view.activities;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wallet.R;
import com.example.wallet.model.Item;
import com.example.wallet.view.fragments.IncomFragment;

public class InformationActivity  extends AppCompatActivity {

    TextView title;
    TextView amount;
    TextView description;

    ImageButton play;
    ImageButton pause;


    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private AudioFocusRequest audioFocusRequest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        initall(); 
    }

    private void initall() {
        initView();
        parseIntent();
    }

    private void parseIntent() {

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            Item item  =(Item)intent.getExtras().getSerializable(IncomFragment.ClcikedKey);
            title.setText(item.getTitle());
            amount.setText(item.getAmount());

            if(item.getFile()!=null && item.getDescription().isEmpty())
            {
             description.setVisibility(View.GONE);
             play.setVisibility(View.VISIBLE);
             mediaPlayer = MediaPlayer.create(this , Uri.fromFile(item.getFile()));
             audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
             initListeners();
            }
            else{
                description.setText(item.getDescription());
            }
        }
    }

    private void initListeners() {

        play.setOnClickListener(v-> play());
        pause.setOnClickListener(v->pause());

        // We have to handle focus changes
        onAudioFocusChangeListener = focusChange -> {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS: {
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a short amount of time.
                    // The AUDIOFOCUS_LOSS case means we've lost audio focus

                    pause();
                } break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: {
                    // The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume.
                } break;
                case AudioManager.AUDIOFOCUS_GAIN: {
                    // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                    play();
                } break;
            }
        };


        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAcceptsDelayedFocusGain(true)
                .setWillPauseWhenDucked(true)
                .setOnAudioFocusChangeListener(onAudioFocusChangeListener)
                .build();


    }

    private void pause() {
        // Hide pause button
        pause.setVisibility(View.GONE);
        // Show play button
        play.setVisibility(View.VISIBLE);
        // Pause media player
        mediaPlayer.pause();
    }

    private void play() {
        int result = audioManager.requestAudioFocus(audioFocusRequest);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // Hide play button
            play.setVisibility(View.GONE);
            // Show pause button
            pause.setVisibility(View.VISIBLE);
            // Start media player
            mediaPlayer.start();
        }
    }
    

    private void initView() {
        title = findViewById(R.id.textViewNaslov);
        amount = findViewById(R.id.textViewKolicina);
        description= findViewById(R.id.textViewDescription);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
    }


}
