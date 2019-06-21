package com.hikari.nihongonoseito

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView
import com.hikari.nihongonoseito.Database.DBHelper
import kotlinx.android.synthetic.main.fragment_quiz.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class FragmentQuiz : Fragment(), View.OnClickListener {


    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    fun setOnHeadlineSelectedListener(callback: OnFragmentInteractionListener) {
        this.listener = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        val cKana: CardView = view.quizKana
        val cKanji: CardView = view.quizKanji
        val cVocab: CardView = view.quizVocab

        cKana.setOnClickListener(this)
        cKanji.setOnClickListener(this)
        cVocab.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        val isKana:Int = 0
        val isKanji:Int = 1
        val isVocab:Int = 2
        when (v?.id) {
            R.id.quizKana ->
                listener?.onFragmentInteraction(isKana)
            R.id.quizKanji ->
                listener?.onFragmentInteraction(isKanji)
            R.id.quizVocab ->
                listener?.onFragmentInteraction(isVocab)
        }
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
        fun onFragmentInteraction(tipoQuiz: Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentQuiz.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentQuiz().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
