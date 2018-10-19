package br.com.mrafaelbatista.androidroom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.mrafaelbatista.androidroom.adapters.PessoaListAdapter;
import br.com.mrafaelbatista.androidroom.bd.AppDatabase;
import br.com.mrafaelbatista.androidroom.bd.entities.Pessoa;
import br.com.mrafaelbatista.androidroom.bd.views.PessoaViewModel;

import static br.com.mrafaelbatista.androidroom.bd.AppDatabase.*;

public class MainActivity extends AppCompatActivity {

    private AppDatabase mDb;

    private PessoaViewModel mPessoaViewModel;

    public static final int NEW_PESSOA_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDb = getDatabase(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PessoaListAdapter adapter = new PessoaListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPessoaViewModel = ViewModelProviders.of(this).get(PessoaViewModel.class);

        mPessoaViewModel.getmAllPessoas().observe(this, new Observer<List<Pessoa>>() {
            @Override
            public void onChanged(@Nullable final List<Pessoa> pessoas) {
                adapter.setPessoas(pessoas);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NovaPessoaActivity.class);
                startActivityForResult(intent, NEW_PESSOA_ACTIVITY_REQUEST_CODE);
            }
        });


    }

    @Override
    protected void onDestroy() {
        destroyInstance();
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PESSOA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Pessoa pessoa = new Pessoa(data.getStringExtra(NovaPessoaActivity.EXTRA_REPLY));
            mPessoaViewModel.insert(pessoa);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
