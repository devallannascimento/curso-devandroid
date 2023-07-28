package com.dev.allannascimento.frasesdodia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void gerarNovaFrase(View view) {

        String[] frases = {
                "Frase 0",
                "Frase 1",
                "Frase 2",
                "Frase 3",
        };

        int numero = new Random().nextInt(4);

        TextView texto = findViewById(R.id.txtFrase);
        texto.setText(frases[numero]);

    }
}