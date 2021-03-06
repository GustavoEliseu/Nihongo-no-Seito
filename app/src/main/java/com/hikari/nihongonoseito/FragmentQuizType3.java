package com.hikari.nihongonoseito;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentQuizType3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentQuizType3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentQuizType3 extends Fragment implements View.OnClickListener{

    private CardView c1,c2,c3,c4;

    private OnFragmentInteractionListener mListener;

    public FragmentQuizType3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentQuiz.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentQuizType3 newInstance(String param1, String param2) {
        FragmentQuizType3 fragment = new FragmentQuizType3();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_quiz, container, false);
        c1= v.findViewById(R.id.quizBtn1);
        c2= v.findViewById(R.id.quizBtn2);
        c3= v.findViewById(R.id.quizBtn3);
        c4= v.findViewById(R.id.quizBtn4);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction("teste",uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.quizBtn1:
                Toast.makeText(getContext(),"option1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.quizBtn2:
                Toast.makeText(getContext(),"option2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.quizBtn3:
                Toast.makeText(getContext(),"option3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.quizBtn4:
                Toast.makeText(getContext(),"option4",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getContext(),"Nenhuma opção detecetada!",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener<T> {
        // TODO: Update argument type and name
        void onFragmentInteraction(String tag, T data);

    }
}
