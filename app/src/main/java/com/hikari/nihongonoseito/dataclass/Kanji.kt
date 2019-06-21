package com.hikari.nihongonoseito.dataclass

class Kanji(
        val id:Int,
        val text_jap:String,
        val text_pt:String,
        val text_kun:String,
        val text_on:String,
        //val text_img:String,
        val text_num_trac:String,
        //val text_icon:String,
        val text_categoria:String,
        val text_capitulo:String,
        val text_ex1:String,
        val text_ex2:String,
        val text_ex1_trad:String,
        val text_ex2_trad:String,
        val isInQuiz:Boolean,
        val acertos_sig:Int,
        val erros_sig:Int,
        val acerto_leit:Int,
        val erro_leit:Int
){
    constructor(text_jap:String,
                text_pt:String,
                 text_kun:String,
                 text_on:String,
            //val text_img:String,
                 text_num_trac:String,
            //val text_icon:String,
                 text_categoria:String,
                 text_capitulo:String,
                 text_ex1:String,
                 text_ex2:String,
                 text_ex1_trad:String,
                 text_ex2_trad:String,
                 isInQuiz:Boolean,
                 acertos_sig:Int,
                 erros_sig:Int,
                 acerto_leit:Int,
                 erro_leit:Int): this(-1,text_jap,text_pt,text_kun,text_on,text_num_trac,text_categoria,text_capitulo,text_ex1,text_ex2,text_ex1_trad,text_ex2_trad,isInQuiz,acertos_sig,erros_sig,acerto_leit,erro_leit)

}

