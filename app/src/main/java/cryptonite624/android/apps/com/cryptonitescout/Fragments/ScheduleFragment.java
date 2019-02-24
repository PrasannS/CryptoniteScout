package cryptonite624.android.apps.com.cryptonitescout.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;
import cryptonite624.android.apps.com.cryptonitescout.R;
import cryptonite624.android.apps.com.cryptonitescout.RankingFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String INPUTS = "inputs";





    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int[] inputs;

    private OnFragmentInteractionListener mListener;

    private TextView matchnum;
    private Button red1;
    private Button red2;
    private Button red3;
    private Button blue1;
    private Button blue2;
    private Button blue3;


    public String message;

    OnScheduleRead onScheduleRead;



    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(int[] inputs) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putIntArray(INPUTS,inputs);
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnScheduleRead{
        public void OnScheduleRead(String message);
    }

    public FragmentManager fragmentManager;
    public View view2;


    public void setArgument(Schedule data ){
        matchnum.setText(data.getMatchnum());
        red1.setText(data.getR1());
        red2.setText(data.getR2());
        red3.setText(data.getR3());
        blue1.setText(data.getB1());
        blue2.setText(data.getB2());
        blue3.setText(data.getB3());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);


        /*
        matchnum = (TextView)(view.findViewById(R.id.Match_num_schedule));

        red1 = (Button)(view.findViewById(R.id.Red1_schedule));
        red1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toTeamProfile";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        red2 = (Button)(view.findViewById(R.id.Red2_schedule));
        red2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toTeamProfile";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        red3 = (Button)(view.findViewById(R.id.Red3_schedule));
        red3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toTeamProfile";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        blue1 = (Button)(view.findViewById(R.id.Blue1_schedule));
        blue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toTeamProfile";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        blue2 = (Button)(view.findViewById(R.id.Blue2_schedule));
        blue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toTeamProfile";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        blue3 = (Button)(view.findViewById(R.id.Blue3_schedule));
        blue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toTeamProfile";
                onScheduleRead.OnScheduleRead(message);
            }
        });*/

        view2 = view;
        return view;


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
        Activity activity = (Activity)context;
        try{
            onScheduleRead = (ScheduleFragment.OnScheduleRead) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    }
}