package br.unifacs.atividade1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button botaoconfigurar = (Button) findViewById(R.id.button_configurar);
        Button botaosair = (Button) findViewById(R.id.button_sair);
        botaoconfigurar.setOnClickListener(this);
        botaosair.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_configurar:
                Intent view_configurar = new Intent(this, ConfigActivity.class);
                startActivity(view_configurar);
                break;
            case R.id.button_sair:
                finish();
                break;
        }
    }
}