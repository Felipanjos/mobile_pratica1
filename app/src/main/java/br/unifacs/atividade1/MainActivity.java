package br.unifacs.atividade1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private int chances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPrefs = getSharedPreferences("Login", Context.MODE_PRIVATE);
        chances = sharedPrefs.getInt("Tentativa", 3);

        if (chances == 0)
            blockApp();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botaoentrar = (Button) findViewById(R.id.button_entrar);
        Button botaocancelar = (Button) findViewById(R.id.button_cancelar);
        botaoentrar.setOnClickListener(this);
        botaocancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_entrar:
                autentica();
                break;
            case R.id.button_cancelar:
                finish();
                break;
        }
    }

    private void autentica() {

        EditText login = (EditText) findViewById(R.id.username),
                senha = (EditText) findViewById(R.id.password);
        String loginInput = login.getText().toString(),
                senhaInput = senha.getText().toString(),
                loginPadrao = sharedPrefs.getString("Username", "admin"),
                senhaPadrao = sharedPrefs.getString("Password", "admin");

        if (loginPadrao.equals(loginInput) && senhaInput.equals(senhaInput)) {

            chances = 0;
            updateTable();

            Intent view_menu = new Intent(this, MenuActivity.class);
            startActivity(view_menu);
            Toast.makeText(this, getResources().getString(R.string.msg_login), Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            chances--;
            updateTable();

            if (chances == 0)
                blockApp();
            else {
                login.getText().clear();
                senha.getText().clear();
                Toast.makeText (this, getResources().getString(R.string.msg_falha) + chances + getResources().getString(R.string.msg_falha2), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateTable() {

        sharedPrefsEditor = sharedPrefs.edit();

        if (sharedPrefsEditor != null) {
            sharedPrefsEditor.putInt("Tentativas", chances);
            sharedPrefsEditor.commit();
        }
    }

    private void blockApp() {
        Toast.makeText(this, getResources().getString(R.string.msg_over), Toast.LENGTH_LONG).show();
        finish();
    }
}