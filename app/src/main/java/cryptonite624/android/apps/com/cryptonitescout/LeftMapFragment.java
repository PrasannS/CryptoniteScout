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
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeftMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeftMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftMapFragment extends Fragment {
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

    OnLeftMapReadListener leftMapReadListener;

    private ActionMap actionMap;

    public LeftMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftMapFragment newInstance(String param1, String param2) {
        LeftMapFragment fragment = new LeftMapFragment();
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
        View view = inflater.inflate(R.layout.fragment_left_map, container, false);

/*
        cargobutton1 = (Button)view.findViewById(R.id.cargobutton1_left);
        cargobutton2 = (Button)view.findViewById(R.id.cargobutton2_left);
        cargobutton3 = (Button)view.findViewById(R.id.cargobutton3_left);
        cargobutton4 = (Button)view.findViewById(R.id.cargobutton4_left);
        cargobutton5 = (Button)view.findViewById(R.id.cargobutton5_left);
        cargobutton6 = (Button)view.findViewById(R.id.cargobutton6_left);
        cargobutton7 = (Button)view.findViewById(R.id.cargobutton7_left);
        cargobutton8 = (Button)view.findViewById(R.id.cargobutton8_left);
*/
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            leftMapReadListener = (OnLeftMapReadListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnLeftMapReadListener{
        public void OnLeftMapRead(int x, int y);
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

    public void individualButton(RobotAction action){
        if (action.actionCode.equals("C1")) {
            if(action.hatch){
                cargobutton1.setBackgroundColor(Color.YELLOW);
            }
            else{
                cargobutton1.setBackgroundColor(Color.RED);
            }

        } else if (action.actionCode.equals("C2")) {
            if(action.hatch){
                cargobutton2.setBackgroundColor(Color.YELLOW);
            }
            else{
                cargobutton2.setBackgroundColor(Color.RED);
            }
        } else if (action.actionCode.equals("C3")) {
            if(action.hatch){
                cargobutton3.setBackgroundColor(Color.YELLOW);
            }
            else{
                cargobutton3.setBackgroundColor(Color.RED);
            }
        } else if (action.actionCode.equals("C4")) {
            if(action.hatch){
                cargobutton4.setBackgroundColor(Color.YELLOW);
            }
            else{
                cargobutton4.setBackgroundColor(Color.RED);
            }
        } else if (action.actionCode.equals("C5")) {
            if(action.hatch){
                cargobutton5.setBackgroundColor(Color.YELLOW);
            }
            else{
                cargobutton5.setBackgroundColor(Color.RED);
            }
        } else if (action.actionCode.equals("C6")) {
            if(action.hatch){
                cargobutton6.setBackgroundColor(Color.YELLOW);
            }
            else{
                cargobutton6.setBackgroundColor(Color.RED);
            }
        } else if (action.actionCode.equals("C7")) {
            if(action.hatch){
                cargobutton7.setBackgroundColor(Color.YELLOW);
            }
            else{
                cargobutton7.setBackgroundColor(Color.RED);
            }
        } else if (action.actionCode.equals("C8")) {
            if(action.hatch){
                cargobutton8.setBackgroundColor(Color.YELLOW);
            }
            else{
                cargobutton8.setBackgroundColor(Color.RED);
            }
        }
    }
/*
    public void updateButtons() {
        //Activity tempActivity = (MapView) getActivity();
        //((MapView) tempActivity).updateFilled();
        //actionMap = ((MapView)tempActivity).getActionMap();
        Activity tempActivity = (MapView) getActivity();
        actionMap = ((MapView)tempActivity).getActionMap();

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
    }*/
}
