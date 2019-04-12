package com.hikari.nihongonoseito;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Vocab_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Vocab_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Vocab_Fragment extends Fragment implements myRecyclerAd.ItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    myRecyclerAd mRecyclerAd;
    RecyclerView mRecycler;

    // TODO: Rename and change types of parameters


    private OnFragmentInteractionListener mListener;

    public Vocab_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Vocab_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Vocab_Fragment newInstance(String param1, String param2) {
        Vocab_Fragment fragment = new Vocab_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_vocab_, container, false);


        //Definindo o recyclerview
        mRecycler= v.findViewById(R.id.myRecycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerAd = new myRecyclerAd(getContext(),getResources().getStringArray(R.array.listaVocab),getResources().getStringArray(R.array.vocabImgs),getResources().getStringArray(R.array.listaVocabTraduz));
        mRecyclerAd.setClickListener(this);
        mRecycler.setAdapter(mRecyclerAd);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction("teste3",uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onItemClick(View view, int position, Boolean tipo) {
        String teste = "";
        if(tipo == true) teste = "botao pequeno "+ position;
        else teste = "vocab na posicao "+ position;
        Log.v("TAGClicked","meu numero foi"+ position);
        Toast.makeText(getContext(),teste,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener<T> {
        // TODO: Update argument type and name
        void onFragmentInteraction(String tag, T data);
    }
}
