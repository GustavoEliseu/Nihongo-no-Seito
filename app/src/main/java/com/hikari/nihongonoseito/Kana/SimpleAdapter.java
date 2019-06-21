package com.hikari.nihongonoseito.Kana;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hikari.nihongonoseito.R;
import com.hikari.nihongonoseito.dataclass.Kana;
import com.hikari.nihongonoseito.dataclass.KanaExib;

import java.util.ArrayList;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {
    private ItemClickListener mClickListener;
    private static final int column_type5 = 1;
    private static final int column_type3 = 2;
    private final Context mContext;
    private boolean todosKana = false;
    private final ArrayList<KanaExib> arrayItens;

    public SimpleAdapter(Context context,ArrayList<KanaExib> arrayItens) {
        mContext = context;
        this.arrayItens = arrayItens;
    }

    public SimpleAdapter(Context context, ArrayList<KanaExib> arrayItens, float sizeImg, boolean todosKana) {
        mContext = context;
        this.arrayItens = arrayItens;
        this.todosKana=todosKana;
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view;
        switch(viewType) {
            case 1:
                view = LayoutInflater.from(mContext).inflate(R.layout.kana_recycle, parent, false);
                return new SimpleViewHolder(view);
            case 0:
                view = LayoutInflater.from(mContext).inflate(R.layout.kana_recycle2, parent, false);

                return new SimpleViewHolder(view);
                default:
                    view = LayoutInflater.from(mContext).inflate(R.layout.kana_recycle, parent, false);
                    return new SimpleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {

        holder.title.setText(arrayItens.get(position).getRomaji());
        Drawable id = arrayItens.get(position).getImg();
        holder.imgKana.setImageDrawable(id);
        if(holder.title.getText().equals("*")){
            holder.title.setText(" ");
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

    @Override
    public int getItemViewType(int position){
        return arrayItens.get(position).getBas_Var_Jun()==2? column_type3 : column_type5;
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