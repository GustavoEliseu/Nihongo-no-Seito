package com.hikari.nihongonoseito

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.hikari.nihongonoseito.Database.DBHelper
import com.hikari.nihongonoseito.dataclass.vocabQuiz
import kotlin.random.Random
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import kotlinx.android.synthetic.main.fragment_quiz_controller.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentQuizController : Fragment(),FragmentQuizType2.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }

    private var listener: OnFragmentInteractionListener? = null
    private var tipoQuiz: Int = 0
    internal val fragmentQuiz: Fragment = FragmentQuizType2()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            tipoQuiz = arguments!!.getInt("controleQuiz", 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz_controller, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()
        // Get FragmentManager and FragmentTransaction object.

        val fragmentTransaction = childFragmentManager.beginTransaction()
        val vocabs: ArrayList<vocabQuiz> = ArrayList()
        if (context != null) {
            val db: DBHelper? = DBHelper(context!!, null)
            for (i in 0..9) {
                val randomico: Int = Random.nextInt(0, resources.getStringArray(R.array.listaVocab).size)
                val isCerto: Boolean = Random.nextBoolean()
                if (isCerto) {
                    vocabs.add(vocabQuiz(
                            resources.getStringArray(R.array.listaVocab)[randomico],
                            resources.getStringArray(R.array.vocabImgs)[randomico]
                    ))
                } else {
                    var img: String
                    var txt: String
                    var rand2: Int = randomico
                    while (resources.getStringArray(R.array.listaVocab)[rand2].equals(resources.getStringArray(R.array.listaVocab)[randomico])) {
                        rand2 = Random.nextInt(0, resources.getStringArray(R.array.listaVocab).size)
                    }
                    vocabs.add(vocabQuiz(
                            resources.getStringArray(R.array.listaVocab)[rand2],
                            resources.getStringArray(R.array.vocabImgs)[randomico]
                    ))
                    }
                }


        }
        fragmentTransaction.replace(R.id.myQuizFrag, fragmentQuiz, "VocabQuiz").commit()
        childFragmentManager.executePendingTransactions()

    }


    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentQuizController.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentQuizController().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
