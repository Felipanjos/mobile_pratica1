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

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private EditText new_login = (EditText) findViewById(R.id.new_username), new_senha = (EditText) findViewById(R.id.new_password),
            confirmar_senha = (EditText) findViewById(R.id.new_password_confirmar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Button botaoconfirmar = (Button) findViewById(R.id.button_confirmar);
        Button botaocancelar = (Button) findViewById(R.id.button_voltar);
        sharedPrefs = getSharedPreferences("Login",Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {

        Intent view_menu = new Intent(this, MenuActivity.class);

        switch (view.getId()) {
            case R.id.button_confirmar:

                if (new_senha == confirmar_senha) {
                    sharedPrefsEditor.putString("username", new_login.getText().toString());
                    sharedPrefsEditor.putString("password", new_senha.getText().toString());
                    sharedPrefsEditor.commit();
                    Toast.makeText(this, getResources().getString(R.string.msg_dados_alterados), Toast.LENGTH_SHORT).show();
                    startActivity(view_menu);
                }
                else {
                    Toast.makeText(this, getResources().getString(R.string.msg_senha_diferente), Toast.LENGTH_SHORT).show();
                    new_login.getText().clear();
                    new_senha.getText().clear();
                    confirmar_senha.getText().clear();
                }
                break;
            case R.id.button_voltar:
                new_login.getText().clear();
                new_senha.getText().clear();
                confirmar_senha.getText().clear();
                startActivity(view_menu);
                break;
        }
    }
}