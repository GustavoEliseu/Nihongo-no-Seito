package com.hikari.nihongonoseito;

import android.graphics.drawable.Drawable;

class MyItem {
    String texto;
    Drawable image;
    boolean junção = false;

    MyItem(String texto, Drawable image){
        this.texto=texto;
        this.image=image;
    }
    MyItem(String texto, Drawable image, boolean jun){
        this.texto=texto;
        this.image=image;
        this.junção=jun;
    }

    MyItem(String texto){
        this.texto=texto;
    }

    public boolean getjunção(){
        return junção;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }



}
