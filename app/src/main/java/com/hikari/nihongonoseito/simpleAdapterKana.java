package com.hikari.nihongonoseito;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class simpleAdapterKana extends RecyclerView.Adapter<simpleAdapterKana.SimpleViewHolder> {

private ItemClickListener mClickListener;
private static final int column_type5 = 1;
private static final int column_type3 = 2;
private final Context mContext;
private boolean todosKana = false;
private final ArrayList<MyItem> arrayItens;

public simpleAdapterKana(Context context,ArrayList<MyItem> arrayItens) {
        mContext = context;
        this.arrayItens = arrayItens;
        }

public simpleAdapterKana(Context context,ArrayList<MyItem> arrayItens, float sizeImg,boolean todosKana) {
        mContext = context;
        this.arrayItens = arrayItens;
        this.todosKana=todosKana;
        }

public SimpleViewHolder onCreateViewHolder(ViewGroup parent) {
final View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle, parent, false);
        return new SimpleViewHolder(view);
        }

    @Override
public void onBindViewHolder(SimpleViewHolder holder, final int position) {

        holder.title.setText(arrayItens.get(position).texto);
        holder.imgKana.setImageDrawable(arrayItens.get(position).image);
        if(holder.title.getText().equals("z")){
        holder.title.setText("");
        holder.guardar.setVisibility(View.INVISIBLE);
        }else{
        holder.guardar.setVisibility(View.VISIBLE);
        }
        }

public boolean getTodosKana(){
        return todosKana;
        }

@Override
public int getItemCount() {
        return arrayItens.size();
        }

public void setmClickListener(ItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
        }

    @NonNull
    @Override
    public simpleAdapterKana.SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
public int getItemViewType(int position){
        return arrayItens.get(position).getjunção()? column_type3 : column_type5;
        }

public interface ItemClickListener {
    void onItemClick(View view, int position);
}

public class SimpleViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
    public final TextView title;
    public final ImageView imgKana;
    private View guardar;

    public SimpleViewHolder(View view) {
        super(view);
        title = view.findViewById(R.id.txtKana);
        imgKana= view.findViewById(R.id.imgKana);
        guardar=view;

        view.setOnClickListener(this);
    }

    public String getText(){
        return title.getText().toString();
    }

    @Override
    public void onClick(View view) {
        if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
    }


}
}