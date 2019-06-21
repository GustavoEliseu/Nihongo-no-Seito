package com.hikari.nihongonoseito.Database


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.hikari.nihongonoseito.R
import com.hikari.nihongonoseito.dataclass.Kana
import com.hikari.nihongonoseito.dataclass.Kanji
import com.hikari.nihongonoseito.dataclass.Vocab


//TODO - CORRIGIR UPDATE PARA FUNCIONAR CORRETMENTE
class DBHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context,
        DATABASE_NAME, null,
        DATABASE_VERSION
) {
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val DATABASE_NAME = "Nihongo.db"
        private const val DATABASE_VERSION = 1
        //coluna ID para todas as tabelas
        const val COLUNA_ID = "ID" //necessário para pegar a posição correta do kana
        //valores tabela conquista
        const val TABLE_CONQUISTA = "CONQUISTA"
        const val COLUNA_NOME = "NOME"
        const val COLUNA_DESC_CONQ = "DESCRICAO_CONQUISTA"
        const val COLUNA_MOSTRA = "MOSTRA_CONQUISTA"
        //Valores tabela user
        const val TABLE_USUARIO = "USER"
        const val COLUNA_RANK = "RANK_USER"
        const val COLUNA_DIASQUIZ = "DIAS_QUIZ"

        //Valores tabela Kana
        const val TABLE_HIRAGANA = "HIRAGANA"
        const val TABLE_KATAKANA = "KATAKANA"
        const val COLUNA_TXTPT = "TEXT_PORTUGUES"
        const val COLUNA_TXTJP = "TEXT_JAPONES"
        const val COLUNA_NOMEIMG = "NOME_IMAGEM"
        const val COLUNA_NOMETRC = "NOME_IMAGEM_TRACOS"
        const val COLUNA_QUIZ = "QUIZ"
        const val COLUNA_ACERTOLEIT = "NUMERO_ACERTOS_LEITURA"
        const val COLUNA_ERROLEIT = "NUMERO_ERROS_LEITURA"
        const val COLUNA_BAS_JUN = "IS_JUN"

        //Valores tabela kanji
        const val TABLE_KANJI = "KANJI"
        const val COLUNA_TXTKUN = "LEITURA_KUN"
        const val COLUNA_TXTON = "LEITURA_ON"
        //val COLUNA_NOMEICON = "NOME_ICON"
        const val COLUNA_NUM_TRC = "NUM_TRACOS"
        const val COLUNA_CAPITULO = "CAPITULO"
        const val COLUNA_CATEGORIA = "CATEGORIA"
        const val COLUNA_ACERTOSIG = "ACERTOS_SIGNIFICADO"
        const val COLUNA_ERROSIG = "ERROS_SIGNIFICADO"
        const val COLUNA_EX1 = "EXEMPLO1"
        const val COLUNA_EX2 = "EXEMPLO2"
        const val COLUNA_EX1TRAD = "EXEMPLO1_TRAD"
        const val COLUNA_EX2TRAD = "EXEMPLO2_TRAD"

        //valores vocab
        const val TABLE_VOCAB = "VOCAB"
        const val COLUNA_TRAD = "TRADUCAO"
        const val COLUNA_ACERTO = "ACERTOS"
        const val COLUNA_ERRO = "ERROS"


        val COLUNA_TEXT_KANJI = "KANJI_TEXT"
        val COLUNA_IMAGE_NAME = "KANJI_NOME_IMG"


        val TABLE_NOTAS = "NOTAS"

    }

    private val CREATE_TABLE_ACHIEVEMENTS = "CREATE TABLE IF NOT EXISTS $TABLE_CONQUISTA (\n" +
            "$COLUNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "$COLUNA_NOME TEXT NOT NULL,\n" +
            "$COLUNA_DESC_CONQ TEXT NOT NULL,\n" +
            "$COLUNA_MOSTRA INT DEFAULT 0 CHECK ($COLUNA_MOSTRA >= 0 AND $COLUNA_MOSTRA <= 1)\n" +
            ");\n"

    private val CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS $TABLE_USUARIO (" +
            "    $COLUNA_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    $COLUNA_RANK TEXT NOT NULL," +
            "    $COLUNA_DIASQUIZ INT DEFAULT 0" +
            "    );"

    private val CREATE_TABLE_HIRAGANA = "CREATE TABLE IF NOT EXISTS $TABLE_HIRAGANA ( " +
            "$COLUNA_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COLUNA_TXTPT TEXT NOT NULL," +
            "$COLUNA_TXTJP TEXT NOT NULL," +
            "$COLUNA_NOMEIMG TEXT NOT NULL," +
            "$COLUNA_BAS_JUN INT DEFAULT 0 CHECK ($COLUNA_BAS_JUN >= 0 AND $COLUNA_BAS_JUN <= 2)," +
            "$COLUNA_ACERTOLEIT INT NOT NULL," +
            "$COLUNA_ERROLEIT INT NOT NULL);"

    private val CREATE_TABLE_KATAKANA = "CREATE TABLE IF NOT EXISTS $TABLE_KATAKANA ( " +
            "$COLUNA_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COLUNA_TXTPT TEXT NOT NULL," +
            "$COLUNA_TXTJP TEXT NOT NULL," +
            "$COLUNA_NOMEIMG TEXT NOT NULL," +
            "$COLUNA_BAS_JUN INT DEFAULT 0 CHECK ($COLUNA_BAS_JUN >= 0 AND $COLUNA_BAS_JUN <= 2)," +
            "$COLUNA_ACERTOLEIT INT NOT NULL," +
            "$COLUNA_ERROLEIT INT NOT NULL);"

    private val CREATE_TABLE_KANJI = "CREATE TABLE IF NOT EXISTS $TABLE_KANJI (" +
            "    $COLUNA_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    $COLUNA_TXTKUN TEXT NOT NULL," +
            "    $COLUNA_TXTON TEXT NOT NULL," +
            "    $COLUNA_TXTPT TEXT NOT NULL," +
            "    $COLUNA_TXTJP TEXT NOT NULL," +
            //"    $COLUNA_NOMEIMG TEXT NOT NULL," +
            "    $COLUNA_NOMETRC TEXT NOT NULL," +
            //"    $COLUNA_NOMEICON TEXT NOT NULL," +
            "    $COLUNA_CATEGORIA TEXT NOT NULL," +
            "    $COLUNA_CAPITULO INT NOT NULL," +
            "    $COLUNA_EX1 TEXT," +
            "    $COLUNA_EX1TRAD TEXT," +
            "    $COLUNA_EX2 TEXT," +
            "    $COLUNA_EX2TRAD TEXT," +
            "    $COLUNA_QUIZ INT DEFAULT 0 CHECK ($COLUNA_QUIZ >= 0 AND $COLUNA_QUIZ <= 1)," +
            "    $COLUNA_ACERTOSIG INT NOT NULL," +
            "    $COLUNA_ERROSIG INT NOT NULL," +
            "    $COLUNA_ACERTOLEIT INT NOT NULL," +
            "    $COLUNA_ERROLEIT INT NOT NULL);"


    private val CREATE_TABLE_VOCAB = "CREATE TABLE IF NOT EXISTS $TABLE_VOCAB (\n" +
            "$COLUNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "$COLUNA_NOME TEXT NOT NULL,\n" +
            "$COLUNA_TRAD TEXT NOT NULL,\n" +
            "$COLUNA_NOMEIMG TEXT NOT NULL,\n" +
            "$COLUNA_ACERTO INT NOT NULL\n" +
            "$COLUNA_ERRO INT NOT NULL\n" +
            ");\n"

    //onCreate com auto-inicialização para testes
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_ACHIEVEMENTS)
        db.execSQL(CREATE_TABLE_USER)
        db.execSQL(CREATE_TABLE_KATAKANA)
        db.execSQL(CREATE_TABLE_HIRAGANA)
        db.execSQL(CREATE_TABLE_KANJI)
        db.execSQL(CREATE_TABLE_VOCAB)
//        val cursor:Cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_ALUNOS",null)
//        cursor.moveToFirst()
        //if (cursor.getInt(0)<=0){
        try {
            if (isDBStarted(db).not()) {
                startDB(db)
            }
        } catch (e: Exception) {

        }

        //cursor.close()
    }

    fun isDBStarted(db: SQLiteDatabase): Boolean {
        val db = this.getWritableDatabase()
        val count = "SELECT count(*) FROM $TABLE_HIRAGANA"
        val mcursor = db.rawQuery(count, null)
        mcursor.moveToFirst()
        val icount = mcursor.getInt(0)
        mcursor.close()
        return icount > 0
    }

    fun startDB(db: SQLiteDatabase) {
        //inicializa Kana
        val mArrayHira = context.resources.getStringArray(R.array.hira_text)
        val mArrayKata = context.resources.getStringArray(R.array.kata_text)
        val mArrayKanaRomaji = context.resources.getStringArray(R.array.kana_roma)
        val mArrayHiraImg = context.resources.getStringArray(R.array.hira_img)
        val mArrayKataImg = context.resources.getStringArray(R.array.kata_img)

        //inicializa Kanji
        val mArrayKanji = context.resources.getStringArray(R.array.Kanji)
        val mArrayKanjiTrad = context.resources.getStringArray(R.array.kanji_trad)
        val mArrayKanjiKun = context.resources.getStringArray(R.array.leitura_kun)
        val mArrayKanjiOn = context.resources.getStringArray(R.array.leitura_on)
        val mArrayKanjiNumTracos = context.resources.getStringArray(R.array.kanji_num_tracos)
        val mArrayKanjiEx1 = context.resources.getStringArray(R.array.kanji_ex1)
        val mArrayKanjiEx1Trad = context.resources.getStringArray(R.array.kanji_ex1_trad)
        val mArrayKanjiEx2 = context.resources.getStringArray(R.array.kanji_ex2)
        val mArrayKanjiEx2Trad = context.resources.getStringArray(R.array.kanji_ex2_trad)
        val mArrayKanjiCapitulo = context.resources.getStringArray(R.array.kanji_capitulo)
        val mArrayKanjiCategoria = context.resources.getStringArray(R.array.kanji_categoria)

        //inicializa Vocab
        val mArrayVocabNome = context.resources.getStringArray(R.array.listaVocab)
        val mArrayVocabTrad = context.resources.getStringArray(R.array.listaVocabTraduz)
        val mArrayVocabimg = context.resources.getStringArray(R.array.vocabImgs)
        val mArrayVocabCategory = context.resources.getStringArray(R.array.vocabCateg)

        for (x in 0 until mArrayHira.size) {
            val isJun: Int = if (x > 79) 2 else if (x > 55) 1 else 0

            val hira = Kana(mArrayHira[x], mArrayKanaRomaji[x], mArrayHiraImg[x], isJun,0,0)
            val kata = Kana(mArrayKata[x], mArrayKanaRomaji[x], mArrayKataImg[x], isJun,0,0)

            addHiraKata(db, hira, TABLE_HIRAGANA)
            addHiraKata(db, kata, TABLE_KATAKANA)
        }

        for (x in 0 until mArrayKanji.size) {

            val myKanji = Kanji(mArrayKanji[x], mArrayKanjiTrad[x], mArrayKanjiKun[x], mArrayKanjiOn[x], mArrayKanjiNumTracos[x], mArrayKanjiCategoria[x], mArrayKanjiCapitulo[x], mArrayKanjiEx1[x], mArrayKanjiEx2[x], mArrayKanjiEx1Trad[x], mArrayKanjiEx2Trad[x], true, 0, 0, 0, 0)
            addKanji(db, myKanji)
        }

        for (x in 0 until mArrayVocabNome.size) {
            val myVocab = Vocab(mArrayVocabNome[x], mArrayVocabTrad[x], mArrayVocabimg[x], mArrayVocabCategory[x], 0, 0,0)
            addVocab(db, myVocab)
        }

    }


    private fun addKanji(db: SQLiteDatabase, myKanji: Kanji): Long {
        val values = ContentValues()
        values.put(COLUNA_TXTJP, myKanji.text_jap)
        values.put(COLUNA_TXTPT, myKanji.text_pt)
        values.put(COLUNA_TXTKUN, myKanji.text_kun)
        values.put(COLUNA_TXTON, myKanji.text_on)
        values.put(COLUNA_CAPITULO, myKanji.text_capitulo)
        values.put(COLUNA_CATEGORIA, myKanji.text_categoria)
        values.put(COLUNA_EX1, myKanji.text_ex1)
        values.put(COLUNA_EX1TRAD, myKanji.text_ex1_trad)
        values.put(COLUNA_EX2, myKanji.text_ex2)
        values.put(COLUNA_EX2TRAD, myKanji.text_ex2_trad)
        values.put(COLUNA_ACERTOSIG, myKanji.acertos_sig)
        values.put(COLUNA_ERROSIG, myKanji.erros_sig)
        values.put(COLUNA_ACERTOLEIT, myKanji.acerto_leit)
        values.put(COLUNA_ERROLEIT, myKanji.erro_leit)
        values.put(COLUNA_QUIZ, myKanji.isInQuiz)
        values.put(COLUNA_NUM_TRC, myKanji.text_num_trac)

        values.put(COLUNA_TXTPT, myKanji.text_pt)
        values.put(COLUNA_TXTPT, myKanji.text_pt)
        values.put(COLUNA_TXTPT, myKanji.text_pt)
        values.put(COLUNA_TXTPT, myKanji.text_pt)
        values.put(COLUNA_TXTPT, myKanji.text_pt)
        var myLong: Long
        try {
            myLong = db.insertOrThrow(TABLE_KANJI, null, values)
            //precaução para caso de algum erro
        } catch (t: Throwable) {
            t.printStackTrace()
            myLong = -1
        }
        return myLong
    }

    fun addHiraKata(db: SQLiteDatabase, kana: Kana, table: String): Long {
        val values = ContentValues()
        values.put(COLUNA_TXTPT, kana.romaji)
        values.put(COLUNA_TXTJP, kana.nome)
        values.put(COLUNA_BAS_JUN, kana.Bas_Var_Jun)
        values.put(COLUNA_ACERTOLEIT, 0)
        values.put(COLUNA_ERROLEIT, 0)
        values.put(COLUNA_NOMEIMG, kana.img)
        var myLong: Long
        try {
            myLong = db.insertOrThrow(table, null, values)
            //precaução para caso de algum erro
        } catch (t: Throwable) {
            t.printStackTrace()
            myLong = -1
        }
        return myLong
    }

    fun addVocab(db: SQLiteDatabase, vocab: Vocab): Long {
        val values = ContentValues()
        values.put(COLUNA_NOME, vocab.vocabNome)
        values.put(COLUNA_TRAD, vocab.vocabTrad)
        values.put(COLUNA_ACERTO, vocab.vocabAcerto)
        values.put(COLUNA_ERRO, vocab.vocabErros)
        values.put(COLUNA_NOMEIMG, vocab.vocabImg)
        var myLong: Long
        try {
            myLong = db.insertOrThrow(TABLE_VOCAB, null, values)
            //precaução para caso de algum erro
        } catch (t: Throwable) {
            t.printStackTrace()
            myLong = -1
        }
        return myLong
    }

    fun updateVocab(vocab: Vocab): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COLUNA_ERRO, vocab.vocabErros)
        values.put(COLUNA_NOMEIMG, vocab.vocabImg)
        values.put(COLUNA_QUIZ,vocab.isInQuiz)
        var myInt: Int
        try {
            myInt = db.update(TABLE_VOCAB, values, "#COLUNA_ID=" + vocab.id, null)
        } catch (t: Throwable) {
            t.printStackTrace()
            myInt = -1
        }
        return myInt
    }


    fun updateKanji(kanji: Kanji):Int {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COLUNA_ERROLEIT, kanji.erro_leit)
        values.put(COLUNA_ERROSIG, kanji.erros_sig)
        values.put(COLUNA_ACERTOLEIT, kanji.acerto_leit)
        values.put(COLUNA_ACERTOSIG, kanji.acertos_sig)
        values.put(COLUNA_QUIZ, kanji.isInQuiz)
        var myInt: Int
        try {
            myInt = db.update(TABLE_KANJI, values, "#COLUNA_ID=" + kanji.id, null)
        } catch (t: Throwable) {
            t.printStackTrace()
            myInt = -1
        }
        return myInt
    }

    //true = TABLE_HIRAGANA OU false = TABLE_KATAKANA
    fun updateKatakana(kana: Kana, isHiragana: Boolean):Int {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COLUNA_ACERTO, kana.acerto)
        values.put(COLUNA_ERRO, kana.erro)

        val table = if(isHiragana) TABLE_HIRAGANA else TABLE_KATAKANA

        var myInt: Int
        try {
            myInt = db.update(table, values, "#COLUNA_ID=" + kana.id, null)
        } catch (t: Throwable) {
            t.printStackTrace()
            myInt = -1
        }
        return myInt
    }


//    fun startAlunoNota(db: SQLiteDatabase){
//        val mArrayNome= context.resources.getStringArray(R.array.alunos_nomes)
//        val mArrayData= context.resources.getStringArray(R.array.alunos_datas)
//        val mArrayMateria= context.resources.getStringArray(R.array.notas_materia)
//
//        for(x in 0 until mArrayNome.size){
//            val aluno: Aluno =
//                    Aluno(mArrayNome[x], mArrayData[x], x)
//            //checa se o usuário foi adicionado corretamente e adiciona as respectivas ntoas dele
//            if(addAluno(aluno,db)>-1L){
//                for (y in 0 until mArrayMateria.size){
//                    addNota(
//                            Nota(
//                                    aluno,
//                                    mArrayMateria[y],
//                                    (0..10).random().toInt()
//                            ),db)
//                }
//            }
//        }
//    }

/* private val CREATE_TABLE_KANJI =
         "CREATE TABLE IF NOT EXISTS $TABLE_ALUNOS ($COLUNA_MATRICULA INTEGER PRIMARY KEY AUTOINCREMENT, $COLUNA_NOME TEXT NOT NULL, $COLUNA_DATA TEXT , UNIQUE ($COLUNA_NOME , $COLUNA_DATA));"
 private val CREATE_TABLE_KANAS =
         "CREATE TABLE IF NOT EXISTS $TABLE_NOTAS ($COLUNA_MATRICULA INTEGER , $COLUNA_NOTA INTEGER CHECK ($COLUNA_NOTA >= 0 AND $COLUNA_NOTA <11), $COLUNA_MATERIA TEXT NOT NULL);"
 private val CREATE_TABLE_VOCAB =
         "CREATE TABLE IF NOT EXISTS $TABLE_NOTAS ($COLUNA_MATRICULA INTEGER , $COLUNA_NOTA INTEGER CHECK ($COLUNA_NOTA >= 0 AND $COLUNA_NOTA <11), $COLUNA_MATERIA TEXT NOT NULL);"



 override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

     onCreate(db)
 }

 //FUNÇÃO TESTE - para adicionar os alunos e as notas disponibilizadas nos string-arrays no string.xml
 fun startAlunoNota(db: SQLiteDatabase){
     val mArrayNome= context.resources.getStringArray(R.array.alunos_nomes)
     val mArrayData= context.resources.getStringArray(R.array.alunos_datas)
     val mArrayMateria= context.resources.getStringArray(R.array.notas_materia)

     for(x in 0 until mArrayNome.size){
         val aluno: Aluno =
                 Aluno(mArrayNome[x], mArrayData[x], x)
         //checa se o usuário foi adicionado corretamente e adiciona as respectivas ntoas dele
         if(addAluno(aluno,db)>-1L){
             for (y in 0 until mArrayMateria.size){
                 addNota(
                         Nota(
                                 aluno,
                                 mArrayMateria[y],
                                 (0..10).random().toInt()
                         ),db)
             }
         }
     }
 }

 //FUNÇÃO TESTE- para adicionar alunos do onCreateDatabase
 fun addAluno(aluno: Aluno, db: SQLiteDatabase):Long {
     val values = ContentValues()
     values.put(COLUNA_NOME, aluno.nome)
     values.put(COLUNA_DATA, aluno.data)
     var myLong: Long
     try {
         myLong= db.insertOrThrow(TABLE_ALUNOS, null, values)
         //precaução para caso de algum erro
     }catch(t:Throwable){
         t.printStackTrace()
         myLong=-1
     }
     return myLong
 }

 //Função para se adicionar um aluno ao banco de dados
 fun addAluno(aluno: Aluno):Long {
     val values = ContentValues()
     values.put(COLUNA_NOME, aluno.nome)
     values.put(COLUNA_DATA, aluno.data)
     var myLong: Long
     val db = this.writableDatabase
     try {
         myLong= db.insertOrThrow(TABLE_ALUNOS, null, values)
         //precaução para caso de algum erro
     }catch(t:Throwable){
         t.printStackTrace()
         myLong=-1
     }
     db.close()
     return myLong
 }

 fun updateAluno(nome:String,data:String, matricula:Int):Boolean{
     val db = this.writableDatabase
     val cv:ContentValues = ContentValues()
     cv.put(COLUNA_NOME,nome)
     cv.put(COLUNA_DATA,data)
     var result: Boolean
     try{
         result =db.update(TABLE_ALUNOS,cv, COLUNA_MATRICULA+" = ? ",arrayOf(matricula.toString()))>0
     }catch(e:Exception){
         result = false
     }
     return result
 }

 //Seleciona o ultimo AUTOINCREMENT adicionado na tabela Alunos
 fun getLastInsert():Cursor?{
     val db = this.readableDatabase
     return db.query(TABLE_ALUNOS,  arrayOf(COLUNA_MATRICULA), null, null, null, null, COLUNA_MATRICULA+" DESC");
     //return db.rawQuery("SELECT $COLUNA_MATRICULA FROM $TABLE_ALUNOS ORDER BY DESC LIMIT 1 ",null)
 }

 //FUNÇÃO TESTE, adiciona notas para os alunos gerados automaticamente
 fun addNota(nota: Nota, db: SQLiteDatabase) {
     val values = ContentValues()
     values.put(COLUNA_MATRICULA,nota.aluno.matricula)
     values.put(COLUNA_NOTA, nota.valor)
     values.put(COLUNA_MATERIA, nota.materia)
     db.insertOrThrow(TABLE_NOTAS, null, values)

 }

 //Função para adicionar a nota de um aluno
 fun addNota(nota: Nota) {
     val values = ContentValues()
     values.put(COLUNA_MATRICULA,nota.aluno.matricula)
     values.put(COLUNA_NOTA, nota.valor)
     values.put(COLUNA_MATERIA, nota.materia)
     val db = this.writableDatabase
     db.insertOrThrow(TABLE_NOTAS, null, values)
     db.close()

 }

 fun deleteNota(id:Long, matricula: Int):Boolean{
     val db= this.writableDatabase
     val args:Array<String> = arrayOf(id.toString(),matricula.toString())
     return db.delete(TABLE_NOTAS,"ROWID=? and $COLUNA_MATRICULA =? ",args )>0
 }

 fun deleteAllNotas(matricula:Int){
     val db= this.writableDatabase
     val args:Array<String> = arrayOf(matricula.toString())
     db.delete(TABLE_NOTAS,"$COLUNA_MATRICULA =? ",args )>0
 }

 fun deleteAluno(matricula:Int):Boolean{
     val db= this.writableDatabase
     val args:Array<String> = arrayOf(matricula.toString())
     val alunoDeletado= db.delete(TABLE_ALUNOS,"$COLUNA_MATRICULA =? ",args)>0
     if(alunoDeletado==true){
         deleteAllNotas(matricula)
     }
     return alunoDeletado
 }

 //função que seleciona todos os launos para preencher a lista
 fun getAllAlunos(): Cursor? {
     val db = this.readableDatabase
     return db.rawQuery("SELECT * FROM $TABLE_ALUNOS", null)
 }

 //criada para caso seja solicitado
 fun getAllNotas(): Cursor? {
     val db = this.readableDatabase
     return db.rawQuery("SELECT  * FROM $TABLE_NOTAS", null)
 }

 //função para receber todas as notas de um aluno
 fun getAllNotasAluno(matricula:Int): Cursor? {
     val db = this.readableDatabase
     return db.rawQuery("SELECT ROWID as rowid,* FROM $TABLE_NOTAS WHERE $COLUNA_MATRICULA  = "+matricula, null)
 }*/


}