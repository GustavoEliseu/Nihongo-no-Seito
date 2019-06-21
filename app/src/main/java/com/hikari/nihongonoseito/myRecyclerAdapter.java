package com.hikari.nihongonoseito;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class myRecyclerAdapter extends RecyclerView.Adapter<myRecyclerAdapter.ViewHolder> {
    private ItemClickListener mClickListener;
    private String[] mData,mvVocabsImg, mDataTrad;
    private Context context;
    private LayoutInflater mInflater;

    public myRecyclerAdapter(Context context, String data[], String[] vocabsImg, String[] mDataTrad){
        this.mInflater = LayoutInflater.from(context);
        mData = data;
        this.mDataTrad=mDataTrad;
        mvVocabsImg=vocabsImg;
        this.context = context;
    }

    @NonNull
    @Override
    public myRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.recycler_view_item, viewGroup,false);
        return new ViewHolder(view, false);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        try{
        viewHolder.mTitleView.setText(mData[i]);
        viewHolder.vocabTraduz.setText(mDataTrad[i]);
        //viewHolder.controleCor =
        viewHolder.imageVocab.setImageDrawable( context.getResources().getDrawable(context.getResources().getIdentifier(mvVocabsImg[i], "drawable", context.getPackageName())));
        //viewHolder.imageVocab.setImageDrawable(context.getResources().getDrawable(R.drawable.carne_img));
        viewHolder.imageMarcarVocab.setImageDrawable(context.getResources().getDrawable(R.drawable.ver_img));}
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView mTitleView, vocabTraduz;
        public ImageView imageVocab,imageMarcarVocab;
        public boolean controleCor = true;

        public ViewHolder(View v, Boolean controleCor) {
            super(v);
            this.controleCor=controleCor;
            mTitleView = v.findViewById(R.id.vocabTitulo);
            vocabTraduz = v.findViewById(R.id.vocabTraduz);
            imageVocab = v.findViewById(R.id.imageVocab);
            imageMarcarVocab = v.findViewById(R.id.imageMarcarVocab);
            definirCor(controleCor);
            v.setOnClickListener(this);
            imageMarcarVocab.setOnClickListener(this);

        }

        public void inverteCor(){
            this.controleCor = !controleCor;
            if(controleCor){
                mTitleView.setTextColor(Color.BLACK);
            } else{
                mTitleView.setTextColor(Color.GRAY);
            }

        }

        public void definirCor(Boolean controleCor){
            if(controleCor){
                mTitleView.setTextColor(Color.BLACK);
            } else{
                mTitleView.setTextColor(Color.GRAY);
            }
        }


        @Override
        public void onClick(View v) {
            if (mClickListener != null )
                if(v.getId()==imageMarcarVocab.getId()){//click no botão cancelar
                    inverteCor();
                    /*
                    * Se necessário, pode utilizar a boolean controlecor, como algo para saber se o item está habilitado ou não.
                    * com if(controleCor==true).
                    * */
                    mClickListener.onItemClick(imageMarcarVocab, getAdapterPosition(),true);
                }
                else{//click no view em si

                    mClickListener.onItemClick(v, getAdapterPosition(),false);
                }
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position, Boolean tipoClasse);
    }
}




