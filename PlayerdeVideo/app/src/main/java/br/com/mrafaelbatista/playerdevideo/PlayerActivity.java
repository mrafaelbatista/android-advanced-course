package br.com.mrafaelbatista.playerdevideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayerActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        videoView = findViewById(R.id.videoView);

        //Esconde a statusbar
        View decorView = getWindow().getDecorView();
        int uiOpcoes = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOpcoes);

        //Esconder anction bar
        getSupportActionBar().hide();

        //Configurar controles
        videoView.setMediaController(new MediaController(this));
        //Configurando o path
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        //Inicializa o vídeo
        videoView.start();
    }
}
