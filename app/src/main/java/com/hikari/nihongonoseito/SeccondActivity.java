package com.hikari.nihongonoseito;

import android.net.Uri;
import android.os.Bundle;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.hikari.nihongonoseito.Kana.KanaFragment;
import com.hikari.nihongonoseito.dataclass.Kana;

import io.fabric.sdk.android.Fabric;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SeccondActivity extends AbstractActivity implements KanaFragment.OnListFragmentInteractionListener,
        FragmentKanji.OnFragmentInteractionListener,  FragmentSobre.OnFragmentInteractionListener,
FragmentPerfil.OnFragmentInteractionListener, FragmentVocab.OnListFragmentInteractionListener,FragmentQuiz.OnFragmentInteractionListener {
    private TextView mTextMessage;
    final Fragment fragmentSobre = new FragmentSobre();
    final Fragment fragmentKana = new KanaFragment();
    final Fragment fragmentKanji = new FragmentKanji();
    final Fragment fragmentPerfil = new FragmentPerfil();
    final Fragment fragmentVocab = new FragmentVocab();
    Fragment mFrag;
    BottomNavigationView navView;
    int telaAtual;
    RecyclerView.RecycledViewPool recycledViewPool;


    //Objetivo será controlar o OnBackPressed para retornar ou para tela anterior ou para um andar anterior do fragment
    private int degraisDeTela;
    public RecyclerView.RecycledViewPool getSharedRecycledViewPool(){
        return recycledViewPool;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        setContentView(R.layout.activity_seccond);
        navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        int opt =getIntent().getIntExtra("sentOpt",0);
        recycledViewPool = new RecyclerView.RecycledViewPool();


        switch(opt){
            case 0:
                navView.setSelectedItemId(R.id.nav_kana);
                telaAtual=1;
                break;
            case 1:
                navView.setSelectedItemId(R.id.nav_kanji);
                telaAtual=2;
                break;
            case 2:
                navView.setSelectedItemId(R.id.nav_vocab);
                telaAtual=3;
                break;
            case 3:
                navView.setSelectedItemId(R.id.nav_perfil);
                telaAtual=4;
                break;
            case 4:
                changeFragment(4);
                telaAtual=0;
                break;
        }

        degraisDeTela=0;



    }
    @Override
    public void onStart(){
        super.onStart();
    }

    public void firstInit(){

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id =item.getItemId();
            switch (id) {
                case R.id.nav_home:
                    changeFragment(4);
                    telaAtual=0;
                    degraisDeTela=0;
                    return true;
                case R.id.nav_kana:
                    changeFragment(id);
                    telaAtual=1;
                    degraisDeTela=0;
                    return true;
                case R.id.nav_kanji:
                    changeFragment(id);
                    telaAtual=2;
                    degraisDeTela=0;
                    return true;
                case R.id.nav_vocab:
                    changeFragment(id);
                    telaAtual=3;
                    degraisDeTela=0;
                    return true;
                case R.id.nav_perfil:
                    changeFragment(id);
                    telaAtual=4;
                    degraisDeTela=0;
                    return true;
            }
            return false;
        }
    };
    @Override
    public void onBackPressed(){
        if(getSupportFragmentManager().getFragments().size()<1){
            finish();
        }else{
            degraisDeTela--;
            this.removeFragment();
        }
    }

    public void changeFragment(int checkedId){
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        String tag ="";
        switch(checkedId){
            case 4:
                mFrag= fragmentSobre;
                tag= "Sobre";
                break;
            case R.id.nav_vocab:
                mFrag= fragmentVocab;
                tag= "Vocab";
                break;
            case R.id.nav_kanji:
                mFrag= fragmentKanji;
                tag= "Kanji";
                break;
            case R.id.nav_kana:
                mFrag= fragmentKana;
                tag= "Kana";
                break;
            case R.id.nav_perfil:
                mFrag= fragmentPerfil;
                tag= "Perfil";
                break;
        }
        mFrag.setRetainInstance(true);

        ft.replace(R.id.fragment_sec_screen, mFrag,tag);

        ft.commit();
        getSupportFragmentManager().executePendingTransactions();
    }
    public void removeFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mFrag != null) {
            ft.remove(mFrag);
            ft.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String tag, Object data) {

    }

    @Override
    public void onListFragmentInteraction() {

    }

    @Override
    public void onListFragmentInteraction(@Nullable Kana item) {

    }

    @Override
    public void onFragmentInteraction(@NotNull int tipoQuiz) {
        Fragment meuQuiz = new FragmentQuizController();
        Bundle arguments = new Bundle();
        arguments.putInt("controleQuiz",tipoQuiz);
        meuQuiz.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().add(meuQuiz,"quizFrag").addToBackStack("quiz");
        getSupportFragmentManager().executePendingTransactions();
    }
}
