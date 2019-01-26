package cryptonite624.android.apps.com.cryptonitescout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Fragments.InputFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RightMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RightMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RightMapFragment extends Fragment implements InputFragment.OnInputReadListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button cargobutton1;
    Button cargobutton2;
    Button cargobutton3;
    Button cargobutton4;
    Button cargobutton5;
    Button cargobutton6;
    Button cargobutton7;
    Button cargobutton8;

    private OnFragmentInteractionListener mListener;

    OnRightMapReadListener rightMapReadListener;
    
    private ActionMap actionMap;

    public RightMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RightMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RightMapFragment newInstance(String param1, String param2) {
        RightMapFragment fragment = new RightMapFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right_map, container, false);

        //updateButtons();

        cargobutton1 = (Button)view.findViewById(R.id.cargobutton1_right);
        cargobutton2 = (Button)view.findViewById(R.id.cargobutton2_right);
        cargobutton3 = (Button)view.findViewById(R.id.cargobutton3_right);
        cargobutton4 = (Button)view.findViewById(R.id.cargobutton4_right);
        cargobutton5 = (Button)view.findViewById(R.id.cargobutton5_right);
        cargobutton6 = (Button)view.findViewById(R.id.cargobutton6_right);
        cargobutton7 = (Button)view.findViewById(R.id.cargobutton7_right);
        cargobutton8 = (Button)view.findViewById(R.id.cargobutton8_right);


        cargobutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity tempActivity = (MapView) getActivity();
                actionMap = ((MapView)tempActivity).getActionMap();
                updateButtons();
            }
        });

        cargobutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargobutton2.setBackgroundColor(Color.RED);
                Activity tempActivity = (MapView) getActivity();
                actionMap = ((MapView)tempActivity).getActionMap();
                updateButtons();
            }
        });

        cargobutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargobutton3.setBackgroundColor(Color.RED);
                Activity tempActivity = (MapView) getActivity();
                actionMap = ((MapView)tempActivity).getActionMap();
                updateButtons();
            }
        });

        cargobutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargobutton4.setBackgroundColor(Color.RED);
                Activity tempActivity = (MapView) getActivity();
                actionMap = ((MapView)tempActivity).getActionMap();
                updateButtons();
            }
        });

        cargobutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargobutton5.setBackgroundColor(Color.RED);
                Activity tempActivity = (MapView) getActivity();
                actionMap = ((MapView)tempActivity).getActionMap();
                updateButtons();
            }
        });

        cargobutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargobutton6.setBackgroundColor(Color.RED);
                Activity tempActivity = (MapView) getActivity();
                actionMap = ((MapView)tempActivity).getActionMap();
                updateButtons();
            }
        });

        cargobutton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargobutton7.setBackgroundColor(Color.RED);
                Activity tempActivity = (MapView) getActivity();
                actionMap = ((MapView)tempActivity).getActionMap();
                updateButtons();
            }
        });

        cargobutton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargobutton8.setBackgroundColor(Color.RED);
                Activity tempActivity = (MapView) getActivity();
                actionMap = ((MapView)tempActivity).getActionMap();
                updateButtons();
            }
        });



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            rightMapReadListener = (OnRightMapReadListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void hatch(Boolean b) {
        System.out.println("IT REALLY WORKS!");
        updateButtons();
    }

    public interface OnRightMapReadListener{
        public void OnRightMapRead(int x, int y);
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

    public void updateButtons() {
        //Activity tempActivity = (MapView) getActivity();
        //((MapView) tempActivity).updateFilled();
        //actionMap = ((MapView)tempActivity).getActionMap();
        if (actionMap != null) {
            for (int i = 0; i < actionMap.actions.size(); i++) {
                System.out.println(actionMap.actions.get(i));
                if (actionMap.actions.get(i).actionCode.equals("C1")) {
                    if(actionMap.actions.get(i).hatch){
                        cargobutton1.setBackgroundColor(Color.YELLOW);
                    }
                    else{
                        cargobutton1.setBackgroundColor(Color.RED);
                    }

                } else if (actionMap.actions.get(i).actionCode.equals("C2")) {
                    if(actionMap.actions.get(i).hatch){
                        cargobutton2.setBackgroundColor(Color.YELLOW);
                    }
                    else{
                        cargobutton2.setBackgroundColor(Color.RED);
                    }
                } else if (actionMap.actions.get(i).actionCode.equals("C3")) {
                    if(actionMap.actions.get(i).hatch){
                        cargobutton3.setBackgroundColor(Color.YELLOW);
                    }
                    else{
                        cargobutton3.setBackgroundColor(Color.RED);
                    }
                } else if (actionMap.actions.get(i).actionCode.equals("C4")) {
                    if(actionMap.actions.get(i).hatch){
                        cargobutton4.setBackgroundColor(Color.YELLOW);
                    }
                    else{
                        cargobutton4.setBackgroundColor(Color.RED);
                    }
                } else if (actionMap.actions.get(i).actionCode.equals("C5")) {
                    if(actionMap.actions.get(i).hatch){
                        cargobutton5.setBackgroundColor(Color.YELLOW);
                    }
                    else{
                        cargobutton5.setBackgroundColor(Color.RED);
                    }
                } else if (actionMap.actions.get(i).actionCode.equals("C6")) {
                    if(actionMap.actions.get(i).hatch){
                        cargobutton6.setBackgroundColor(Color.YELLOW);
                    }
                    else{
                        cargobutton6.setBackgroundColor(Color.RED);
                    }
                } else if (actionMap.actions.get(i).actionCode.equals("C7")) {
                    if(actionMap.actions.get(i).hatch){
                        cargobutton7.setBackgroundColor(Color.YELLOW);
                    }
                    else{
                        cargobutton7.setBackgroundColor(Color.RED);
                    }
                } else if (actionMap.actions.get(i).actionCode.equals("C8")) {
                    if(actionMap.actions.get(i).hatch){
                        cargobutton8.setBackgroundColor(Color.YELLOW);
                    }
                    else{
                        cargobutton8.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
    }
}