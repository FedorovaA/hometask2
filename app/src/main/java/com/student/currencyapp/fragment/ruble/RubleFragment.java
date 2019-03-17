package com.student.currencyapp.fragment.ruble;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.student.currencyapp.R;
import com.student.currencyapp.fragment.tenge.TengeFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RubleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RubleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RubleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private EditText rubleKurs;
    private Button convert;
    private double resShekel;
    private double resEuro;
    private double resTenge;
    private int params=0;

    public RubleFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RubleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RubleFragment newInstance(String param1, String param2) {
        RubleFragment fragment = new RubleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void getParams(int values){
        this.params = values;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ruble, container, false);
        rubleKurs = v.findViewById(R.id.ed_rub);
        convert = v.findViewById(R.id.btn_convert1);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rubleKurs.getText().length() != 0) {
                    double res = Double.valueOf(rubleKurs.getText().toString());
                    resEuro = res * 0.014;
                    resShekel = res * 0.055;
                    resTenge = res * 5.8;
                    switch (params){
                        case 1:
                            mListener.onInputRubleSent(input(String.valueOf(resShekel)));
                            break;
                        case 2:
                            mListener.onInputRubleSent(input(String.valueOf(resTenge)));
                            break;
                        case 3:
                            mListener.onInputRubleSent(input(String.valueOf(resEuro)));
                            break;
                    }

                }
            }
        });
        return v;
    }
    private CharSequence input(String values){
        CharSequence in = values;
        return in;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
    public void updateRubleText(CharSequence newText){
        rubleKurs.setText(newText);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void onInputRubleSent(CharSequence input);

    }
}
