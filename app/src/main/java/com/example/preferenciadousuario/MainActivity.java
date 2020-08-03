package com.example.preferenciadousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editNome;
    private Button btnSalvarNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.editText);
        btnSalvarNome = findViewById(R.id.button);
        textResultado = findViewById(R.id.textView2);

        btnSalvarNome.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                // Classe para salvar os dados nas preferencias do usuário, assim salvando dados no aparelho do usuário.
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
                SharedPreferences.Editor editor = preferences.edit();


                // Validar  o campo nome.
                if (editNome.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Campo nome em branco",Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    // Criando um objeto do tipo String e recuperando o que foi digitado no campo EditText e convertendo para String.
                    String nome = editNome.getText().toString();
                    // Passando uma chave e um valor, para que sejá possível acessar os dados.
                    editor.putString("nome",nome);
                    // Salvando os dados.
                    editor.apply();
                    textResultado.setText("Hello, " + nome);
                }
            }
        });

        // Recuperando os dados que foram salvor.
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
        // Verificando se temos um arquivo XML com essa chave, para assim ser possível a recuperação dos dados.
        if (preferences.contains("nome")){

            // Recuperando a chave.
            String nome = preferences.getString("nome","user was not definied");
            textResultado.setText("Hello," + nome);

        } else {
            textResultado.setText("Sorry, but the user was not definied.");
        }
    }
}
