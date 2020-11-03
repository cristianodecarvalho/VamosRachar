package com.example.vamosrachar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{


    EditText et_total, et_qtd;
    TextView tv_result;
    FloatingActionButton fab_listen;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_total = findViewById(R.id.et_total);
        et_qtd = findViewById(R.id.ed_qtd);
        tv_result = findViewById(R.id.tv_result);
        fab_listen = findViewById(R.id.fab_listen);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.US);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result== TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(getBaseContext(), "Indioma não suportado", Toast.LENGTH_SHORT);
                    }else{
                        toSpeak("Bem vindo!");
                    }
                }
            }
        });


        et_total.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                float total = Float.parseFloat(et_total.getText().toString());
                float qtd = Integer.parseInt(et_qtd.getText().toString());
                float result = total/qtd;
                tv_result.setText(Float.toString(result));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_qtd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                float total = Float.parseFloat(et_total.getText().toString());
                float qtd = Integer.parseInt(et_qtd.getText().toString());
                float result = total/qtd;
                tv_result.setText(Float.toString(result));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fab_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSpeak(tv_result.getText().toString());
            }
        });


    }

    public void toSpeak(String toString){
        tts.speak(toString, TextToSpeech.QUEUE_FLUSH, null);
    }

    
}