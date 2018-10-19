package br.com.mrafaelbatista.androidroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NovaPessoaActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "br.com.mrafaelbatista.wordlistsql.REPLY";

    private EditText mEditPessoaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_pessoa);
        mEditPessoaView = findViewById(R.id.edit_pessoa);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditPessoaView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String nome = mEditPessoaView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, nome);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
