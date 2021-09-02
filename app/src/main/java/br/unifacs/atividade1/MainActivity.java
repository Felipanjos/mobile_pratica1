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
    private int chances = sharedPrefs.getInt("Tentativa", 3);
    private EditText login = (EditText) findViewById(R.id.username), senha = (EditText) findViewById(R.id.password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botaoentrar = (Button) findViewById(R.id.button_entrar);
        Button botaocancelar = (Button) findViewById(R.id.button_cancelar);
        botaoentrar.setOnClickListener(this);
        botaocancelar.setOnClickListener(this);

        sharedPrefs = getSharedPreferences("Login", Context.MODE_PRIVATE);
        sharedPrefsEditor.putString("username", "admin");
        sharedPrefsEditor.putString("password", "admin");
        sharedPrefsEditor.commit();
    }

    @Override
    public void onClick(View view) {

        boolean find_login = (login.getText().toString() == sharedPrefs.getString("username", "test"));
        boolean find_senha = (senha.getText().toString() == sharedPrefs.getString("password", "test"));

        switch (view.getId()) {
            case R.id.button_entrar:

                if (chances > 0) {
                    if (find_login && find_senha) {
                        sharedPrefsEditor.putInt("Tentativa", 0);
                        sharedPrefsEditor.commit();
                        Intent view_menu = new Intent(this, MenuActivity.class);
                        startActivity(view_menu);
                        Toast.makeText(this, getResources().getString(R.string.msg_login), Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else {
                        chances--;
                        Toast.makeText(this, getResources().getString(R.string.msg_falha) + chances + getResources().getString(R.string.msg_falha2), Toast.LENGTH_SHORT).show();
                        senha.getText().clear();
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.msg_over), Toast.LENGTH_LONG).show();
                    finish();
                }

            case R.id.button_cancelar:
                finish();
                break;
        }
    }
}