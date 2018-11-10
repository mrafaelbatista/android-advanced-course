package br.com.mrafaelbatista.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.teste);
        inicializarSeekBar();

    }

    private void inicializarSeekBar(){

        seekVolume = findViewById(R.id.seekVolume);

        //configuração de volume (audio manager)
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Recuperar o volume máximo
        int volumeMax = audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        //Recuperar o volume atual
        int volumeAtual = audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);

        //configurar os valores do seekbar
        seekVolume.setMax(volumeMax);
        seekVolume.setProgress(volumeAtual);

        seekVolume.setOnSeekBarChangeListener(new SeekBar
                .OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager
                        .STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void playSom(View v) {
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    public void pauseSom(View v) {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void stopSom(View v) {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getApplicationContext(),
                    R.raw.teste);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
