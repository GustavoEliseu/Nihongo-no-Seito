package com.hikari.nihongonoseito.dataclass

class Kana(
        val id:Int,
        val nome:String,
        val romaji:String,
        val img:String,
        val Bas_Var_Jun:Int,
        val erro:Int,
        val acerto:Int
){
    constructor(nome:String,romaji:String,img:String,Bas_Var_Jun: Int, erro:Int, acerto: Int):this(-1,nome,romaji,img,Bas_Var_Jun,erro,acerto)

    constructor(nome:String,romaji:String,img:String,Bas_Var_Jun: Int):this(-1,nome,romaji,img,Bas_Var_Jun,0,0)

}