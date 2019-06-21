package com.hikari.nihongonoseito.vocab;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.hikari.nihongonoseito.R;
import com.hikari.nihongonoseito.SeccondActivity;
import com.hikari.nihongonoseito.myRecyclerAd;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements myRecyclerAd.ItemClickListener{

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    RecyclerView recyclerView;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_list, container, false);

        return root;
    }

    @Override
    public void onStart(){
        super.onStart();

    }
    @Override
    public void onResume(){
        super.onResume();
        View root = getView();
        if (root instanceof RecyclerView) {
            Context context = root.getContext();
            recyclerView = (RecyclerView) root;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new myRecyclerAd(getContext(),getResources().getStringArray(R.array.listaVocab),getResources().getStringArray(R.array.vocabImgs),getResources().getStringArray(R.array.listaVocabTraduz)));
            recyclerView.setRecycledViewPool(((SeccondActivity)getActivity()).getSharedRecycledViewPool());
            ((myRecyclerAd)recyclerView.getAdapter()).setClickListener(this);
            recyclerView.getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        recyclerView = null;
    }

    @Override
    public void onItemClick(View view, int position, Boolean tipo) {
        String teste = "";
        if(tipo == true) {
            if(((myRecyclerAd.ViewHolder)recyclerView.getChildViewHolder(
                    recyclerView.getChildAt(position)
            )).controleCor==true) {
                ((ImageView) view).setImageDrawable(getContext().getDrawable(R.drawable.ver_img));
            }else{
                ((ImageView) view).setImageDrawable(getContext().getDrawable(R.drawable.naover_img));
            }
        }
        else teste = "vocab na posicao "+ position;
        Log.v("TAGClicked","meu numero foi"+ position);
        Toast.makeText(getContext(),teste,Toast.LENGTH_SHORT).show();
    }
}