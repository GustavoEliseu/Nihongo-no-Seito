package com.hikari.nihongonoseito;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

//TODO- IMPLEMENTAR DRAWER COMO FRAGMENT!
public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    CardView kana,kanji,vocab,perfil,sobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        kana=findViewById(R.id.kana_card);
        kanji=findViewById(R.id.kanji_card);
        vocab=findViewById(R.id.vocab_card);
        perfil=findViewById(R.id.profile_card);
        sobre=findViewById(R.id.sobre_btn);
        kana.setOnClickListener(this);
        kanji.setOnClickListener(this);
        vocab.setOnClickListener(this);
        perfil.setOnClickListener(this);
        sobre.setOnClickListener(this);


    }

    @Override
    public void onStart(){
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SeccondActivity.class);
        switch(v.getId()){
            case R.id.kana_card:
                intent.putExtra("sentOpt",0);
                startActivity(intent);
                break;
            case R.id.kanji_card:
                intent.putExtra("sentOpt",1);
                startActivity(intent);
                break;
            case R.id.vocab_card:
                intent.putExtra("sentOpt",2);
                startActivity(intent);
                break;
            case R.id.profile_card:
                intent.putExtra("sentOpt",3);
                startActivity(intent);
                break;
            case R.id.sobre_btn:
                intent.putExtra("sentOpt",4);
                startActivity(intent);
                break;
        }
    }
}
