package com.student.currencyapp.fragment.ruble;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.student.currencyapp.R;

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
    private static final String ARG_PARAM1 = "CURRENCY";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;
    private EditText rubleKurs;
    private Button convert;

    public RubleFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment RubleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RubleFragment newInstance(String param1) {
        RubleFragment fragment = new RubleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ruble, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rubleKurs = view.findViewById(R.id.ed_rub);
        convert = view.findViewById(R.id.btn_convert1);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rubleKurs.getText().length() != 0) {
                    double res = Double.valueOf(rubleKurs.getText().toString());
                    switch (mParam1){
                        case "SHEKEL":
                            res*=0.055;
                            break;
                        case "EURO":
                            res*=0.014;
                            break;
                        case "TENGE":
                            res*=5.8;
                            break;
                    }
                    mListener.onInputRubleSent(String.valueOf(Math.round(res*100)/100d),mParam1);
                }
                else {
                    mListener.onMessage("Поле курс пусто");
                }
            }
        });
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
    public void updateRubleText(String newText){
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
        void onInputRubleSent(String input, String param);
        void onMessage(String message);

    }
}
