package br.unifacs.atividade1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity implements View.OnClickListener {

    Intent view_menu = new Intent(this, MenuActivity.class);
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPrefs = getSharedPreferences("Login",Context.MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button botaoconfirmar = (Button) findViewById(R.id.button_confirmar);
        Button botaocancelar = (Button) findViewById(R.id.button_voltar);
        botaoconfirmar.setOnClickListener(this);
        botaocancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_confirmar:
                persistChanges();
                break;
            case R.id.button_voltar:
                finish();
                break;
        }
    }

    private void persistChanges() {

        EditText new_login = (EditText) findViewById(R.id.new_username),
                new_senha = (EditText) findViewById(R.id.new_password),
                confirmar_senha = (EditText) findViewById(R.id.new_password_confirmar);

        String new_login_input = new_login.getText().toString(),
                new_senha_input = new_senha.getText().toString(),
                confirmar_senha_input = confirmar_senha.getText().toString();

        if (new_senha_input.equals(confirmar_senha_input)) {

            sharedPrefsEditor = sharedPrefs.edit();

            if (sharedPrefsEditor != null) {
                sharedPrefsEditor.putString("Username", new_login_input);
                sharedPrefsEditor.putString("Password", new_senha_input);
                sharedPrefsEditor.commit();
            }
            Toast.makeText(this, getResources().getString(R.string.msg_dados_alterados), Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            new_login.getText().clear();
            new_senha.getText().clear();
            confirmar_senha.getText().clear();
            Toast.makeText(this, getResources().getString(R.string.msg_senha_diferente), Toast.LENGTH_SHORT).show();
        }
    }
}