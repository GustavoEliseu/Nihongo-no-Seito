package com.hikari.nihongonoseito;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements myRecyclerAd.ItemClickListener {
    myRecyclerAd mRecyclerAd;
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler= findViewById(R.id.myRecycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAd = new myRecyclerAd(this,getResources().getStringArray(R.array.listaVocab),getResources().getStringArray(R.array.vocabImgs),getResources().getStringArray(R.array.listaVocabTraduz));
        mRecyclerAd.setClickListener(this);
        mRecycler.setAdapter(mRecyclerAd);
        mRecyclerAd.notifyItemChanged(0);

    }

    @Override
    public void onItemClick(View view, int position, Boolean tipo) {
        String teste = "";
        if(tipo == true) teste = "botao pequeno "+ position;
        else teste = "vocab na posicao "+ position;
        Log.v("TAGClicked","meu numero foi"+ position);
        Toast.makeText(this,teste,Toast.LENGTH_SHORT).show();
    }
}
