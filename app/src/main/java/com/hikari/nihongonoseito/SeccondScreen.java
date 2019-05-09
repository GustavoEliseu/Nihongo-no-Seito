package com.hikari.nihongonoseito;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.hikari.nihongonoseito.dummy.DummyContent;

public class SeccondScreen extends AppCompatActivity implements KanaFragment.OnFragmentInteractionListener,
        KanjiFragment.OnFragmentInteractionListener, Vocab_Fragment_BAK.OnFragmentInteractionListener, SobreFragment.OnFragmentInteractionListener,
PerfilFragment.OnFragmentInteractionListener, FragmentVocab.OnListFragmentInteractionListener{
    private TextView mTextMessage;

    Fragment mFrag;
    BottomNavigationView navView;

    //Objetivo será controlar o OnBackPressed para retornar ou para tela anterior ou para um andar anterior do fragment
    private int degraisDeTela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccond_screen);
        navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        int opt =getIntent().getIntExtra("sentOpt",0);

        switch(opt){
            case 0:
                navView.setSelectedItemId(R.id.nav_kana);
                changeFragment(R.id.nav_kana);
                break;
            case 1:
                navView.setSelectedItemId(R.id.nav_kanji);
                changeFragment(R.id.nav_kanji);
                break;
            case 2:
                navView.setSelectedItemId(R.id.nav_vocab);
                changeFragment(R.id.nav_vocab);
                break;
            case 3:
                navView.setSelectedItemId(R.id.nav_perfil);
                changeFragment(R.id.nav_perfil);
                break;
            case 4:
                changeFragment(4);
                break;
        }
        degraisDeTela=0;



    }
    @Override
    public void onStart(){
        super.onStart();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id =item.getItemId();
            switch (id) {
                case R.id.nav_home:
                    finish();
                    return true;
                case R.id.nav_kana:
                    changeFragment(id);
                    degraisDeTela=0;
                    return true;
                case R.id.nav_kanji:
                    changeFragment(id);
                    degraisDeTela=0;
                    return true;
                case R.id.nav_vocab:
                    changeFragment(id);
                    degraisDeTela=0;
                    return true;
                case R.id.nav_perfil:
                    changeFragment(id);
                    degraisDeTela=0;
                    return true;
            }
            return false;
        }
    };
    @Override
    public void onBackPressed(){
        if(degraisDeTela==0){
            finish();
        }else{
            degraisDeTela--;
            super.onBackPressed();
        }
    }

    public void changeFragment(int checkedId){
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();;
        switch(checkedId){
            case 4:
                mFrag= new SobreFragment();
                break;
            case R.id.nav_vocab:
                mFrag= new FragmentVocab();
                break;
            case R.id.nav_kanji:
                mFrag= new KanjiFragment();
                break;
            case R.id.nav_kana:
                mFrag= new KanaFragment();
                break;
            case R.id.nav_perfil:
                mFrag= new PerfilFragment();
                break;
        }
        try{
        ft.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left);
        ft.replace(R.id.fragment_sec_screen, mFrag);
        ft.addToBackStack("test");
        }
        catch (Exception E){
            ft.add(R.id.fragment_sec_screen,mFrag);
        }


        ft.replace(R.id.fragment_sec_screen, mFrag);

        ft.commit();
    }
    public void removeFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mFrag != null) {
            ft.remove(mFrag);
            ft.commit();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            mFrag = null;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String tag, Object data) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
