package com.hikari.nihongonoseito;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//TODO- IMPLEMENTAR DRAWER COMO FRAGMENT!
public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, Quiz_Fragment.OnFragmentInteractionListener,
        Vocab_Fragment.OnFragmentInteractionListener,Kanji_Fragment.OnFragmentInteractionListener{

    NavigationView navigationView;
    Fragment mFrag;
    int controleBtn=0;
    private  DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        //definindo o Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Configuração do menu lateral e inicialização deste
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();


    }

    @Override
    public void onStart(){
        super.onStart();
        int idChecked = R.id.nav_vocab;



        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(idChecked);
        mFrag= new Vocab_Fragment();

        changeFragment(idChecked);

    }

    public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations( R.animator.enter_from_left, R.animator.exit_to_right);

        transaction.replace(R.id.frag_placeholder, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void changeFragment(int checkedId){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left);
        ft.replace(R.id.frag_placeholder, mFrag);
        ft.addToBackStack("test");
        switch(checkedId){
            case R.id.nav_vocab:
                mFrag= new Vocab_Fragment();
                ft.replace(R.id.frag_placeholder, mFrag);
                break;
            case R.id.nav_kanji:
                mFrag= new Kanji_Fragment();
                ft.replace(R.id.frag_placeholder, mFrag);
                break;
            case R.id.nav_quiz:
                mFrag= new Quiz_Fragment();
                ft.replace(R.id.frag_placeholder, mFrag);
                break;
        }

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
    public void onStop(){
        int idChecked = R.id.nav_vocab;
        navigationView.setCheckedItem(idChecked);
        super.onStop();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id =menuItem.getItemId();

        switch(id){
            case R.id.nav_vocab:
                if(!(controleBtn==0)) {
                    changeFragment(id);
                    controleBtn=0;
                }
                break;
            case R.id.nav_kanji:
                if(!(controleBtn==1)){
                changeFragment(id);
                    controleBtn=1;
                }
                break;
            case R.id.nav_quiz:

                if(!(controleBtn==2)){
                changeFragment(id);
                    controleBtn=2;
                }
                break;
            default:
                changeFragment(R.id.nav_vocab);
                controleBtn=0;
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(String tag, Object data) {

    }
}
