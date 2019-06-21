package com.hikari.nihongonoseito.dataclass

import com.hikari.nihongonoseito.R

class Vocab( val id:Int
        ,val vocabNome:String, val vocabTrad:String, val vocabImg:String, val vocabCategory:String, val vocabAcerto:Int,val vocabErros:Int, val isInQuiz:Int ) {

    constructor(vocabNome:String, vocabTrad:String, vocabImg:String, vocabCategory:String, vocabAcerto:Int,vocabErros:Int, isInQuiz:Int ): this(-1,vocabNome,vocabTrad,vocabImg,vocabCategory,vocabAcerto,vocabErros,isInQuiz)

}