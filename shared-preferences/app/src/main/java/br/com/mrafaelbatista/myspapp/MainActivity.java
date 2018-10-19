package br.com.mrafaelbatista.myspapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBox;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.checkBox);

        //Capturamos a instância do SharedPreferences
        prefs = getSharedPreferences("configuracoes", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Boolean bol = prefs.getBoolean("check", true);

        if (checkBox.isChecked() != bol) {
            checkBox.setChecked(bol);
        }
    }

    protected void savePrefs(View v) {

        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putBoolean("check", checkBox.isChecked());
        prefsEditor.commit();

        Toast.makeText(this, "Preferências salvas",
                Toast.LENGTH_SHORT).show();

    }

}
