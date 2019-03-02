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
import android.widget.ImageView;

import java.util.ArrayList;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RobotAction;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RightMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RightMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RightMapFragment extends Fragment{


    private ImageView cargoimage1;
    private ImageView cargoimage2;
    private ImageView cargoimage3;
    private ImageView cargoimage4;
    private ImageView cargoimage5;
    private ImageView cargoimage6;
    private ImageView cargoimage7;
    private ImageView cargoimage8;

    private OnFragmentInteractionListener mListener;

    OnRightMapReadListener leftMapReadListener;

    private ActionMap actionMap;

    public RightMapFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right_map, container, false);


        cargoimage1 = (ImageView)view.findViewById(R.id.cargoimage1_right);
        cargoimage2 = (ImageView)view.findViewById(R.id.cargoimage2_right);
        cargoimage3 = (ImageView)view.findViewById(R.id.cargoimage3_right);
        cargoimage4 = (ImageView)view.findViewById(R.id.cargoimage4_right);
        cargoimage5 = (ImageView)view.findViewById(R.id.cargoimage5_right);
        cargoimage6 = (ImageView)view.findViewById(R.id.cargoimage6_right);
        cargoimage7 = (ImageView)view.findViewById(R.id.cargoimage7_right);
        cargoimage8 = (ImageView)view.findViewById(R.id.cargoimage8_right);


        /*cargoimage1.setImageResource(R.drawable.cargoandhatchimage);
        cargoimage2.setImageResource(R.drawable.cargoandhatchimage);
        cargoimage3.setImageResource(R.drawable.cargoandhatchimage);
        cargoimage4.setImageResource(R.drawable.cargoandhatchimage);
        cargoimage5.setImageResource(R.drawable.cargoandhatchimage);
        cargoimage6.setImageResource(R.drawable.cargoandhatchimage);
        cargoimage7.setImageResource(R.drawable.cargoandhatchimage);
        cargoimage8.setImageResource(R.drawable.cargoandhatchimage);*/

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            leftMapReadListener = (OnRightMapReadListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    public void lightUpButtons(ArrayList<RobotAction> actions){
        int C1 = 0;
        int C2 = 0;
        int C3 = 0;
        int C4 = 0;
        int C5 = 0;
        int C6 = 0;
        int C7 = 0;
        int C8 = 0;

        for(RobotAction action : actions){
            if (action.getActionCode().equals("C1")) {
                if(action.isHatch()){
                    C1 += 1;
                }
                else if(!action.isHatch()){
                    C1 += 2;
                }

            } else if (action.getActionCode().equals("C2")) {
                if(action.isHatch()){
                    C2 += 1;
                }
                else if(!action.isHatch()){
                    C2 += 2;
                }
            } else if (action.getActionCode().equals("C3")) {
                if(action.isHatch()){
                    C3 += 1;
                }
                else if(!action.isHatch()){
                    C3 += 2;
                }
            } else if (action.getActionCode().equals("C4")) {
                if(action.isHatch()){
                    C4 += 1;
                }
                else if(!action.isHatch()){
                    C4 += 2;
                }
            } else if (action.getActionCode().equals("C5")) {
                if(action.isHatch()){
                    C5 += 1;
                }
                else if(!action.isHatch()){
                    C5 += 2;
                }
            } else if (action.getActionCode().equals("C6")) {
                if(action.isHatch()){
                    C6 += 1;
                }
                else if(!action.isHatch()){
                    C6 += 2;
                }
            } else if (action.getActionCode().equals("C7")) {
                if(action.isHatch()){
                    C7 += 1;
                }
                else if(!action.isHatch()){
                    C7 += 2;
                }
            } else if (action.getActionCode().equals("C8")) {
                if(action.isHatch()){
                    C8 += 1;
                }
                else if(!action.isHatch()){
                    C8 += 2;
                }
            }
        }
        //reset
        if(C1 == 0){

            //cargoimage8.setImageResource(0);
            cargoimage8.setVisibility(View.INVISIBLE);
        }
        if(C2 == 0){
            //cargoimage7.setImageResource(0);
            cargoimage7.setVisibility(View.INVISIBLE);
        }
        if(C3 == 0){
            //cargoimage6.setImageResource(0);
            cargoimage6.setVisibility(View.INVISIBLE);
        }
        if(C4 == 0){
            //cargoimage5.setImageResource(0);
            cargoimage5.setVisibility(View.INVISIBLE);
        }
        if(C5 == 0){
            //cargoimage4.setImageResource(0);
            cargoimage4.setVisibility(View.INVISIBLE);
        }
        if(C6 == 0){
            //cargoimage3.setImageResource(0);
            cargoimage3.setVisibility(View.INVISIBLE);
        }
        if(C7 == 0){
            //cargoimage2.setImageResource(0);
            cargoimage2.setVisibility(View.INVISIBLE);
        }
        if(C8 == 0){
            //cargoimage1.setImageResource(0);
            cargoimage1.setVisibility(View.INVISIBLE);
        }
        
        
        //hatches
        
        if(C1 == 1){
            cargoimage8.setImageResource(R.drawable.hatchimage);
            cargoimage8.setVisibility(View.VISIBLE);

        }
        if(C2 == 1){
            cargoimage7.setImageResource(R.drawable.hatchimage);
            cargoimage7.setVisibility(View.VISIBLE);

        }
        if(C3 == 1){
            cargoimage6.setImageResource(R.drawable.hatchimage);
            cargoimage6.setVisibility(View.VISIBLE);
        }
        if(C4 == 1){
            cargoimage5.setImageResource(R.drawable.hatchimage);
            cargoimage5.setVisibility(View.VISIBLE);
        }
        if(C5 == 1){
            cargoimage4.setImageResource(R.drawable.hatchimage);
            cargoimage4.setVisibility(View.VISIBLE);
        }
        if(C6 == 1){
            cargoimage3.setImageResource(R.drawable.hatchimage);
            cargoimage3.setVisibility(View.VISIBLE);
        }
        if(C7 == 1){
            cargoimage2.setImageResource(R.drawable.hatchimage);
            cargoimage2.setVisibility(View.VISIBLE);
        }
        if(C8 == 1){
            cargoimage1.setImageResource(R.drawable.hatchimage);
            cargoimage1.setVisibility(View.VISIBLE);
        }


        //cargos
        if(C1 == 2){
            cargoimage8.setVisibility(View.VISIBLE);
            cargoimage8.setImageResource(R.drawable.cargoimage);
        }
        if(C2 == 2){
            cargoimage7.setVisibility(View.VISIBLE);
            cargoimage7.setImageResource(R.drawable.cargoimage);
        }
        if(C3 == 2){
            cargoimage6.setVisibility(View.VISIBLE);
            cargoimage6.setImageResource(R.drawable.cargoimage);
        }
        if(C4 == 2){
            cargoimage5.setVisibility(View.VISIBLE);
            cargoimage5.setImageResource(R.drawable.cargoimage);
        }
        if(C5 == 2){
            cargoimage4.setVisibility(View.VISIBLE);
            cargoimage4.setImageResource(R.drawable.cargoimage);
        }
        if(C6 == 2){
            cargoimage3.setVisibility(View.VISIBLE);
            cargoimage3.setImageResource(R.drawable.cargoimage);
        }
        if(C7 == 2){
            cargoimage2.setVisibility(View.VISIBLE);
            cargoimage2.setImageResource(R.drawable.cargoimage);
        }
        if(C8 == 2){
            cargoimage1.setVisibility(View.VISIBLE);
            cargoimage1.setImageResource(R.drawable.cargoimage);
        }

        //both

        if(C1 == 3){
            cargoimage8.setVisibility(View.VISIBLE);
            cargoimage8.setImageResource(R.drawable.cargoandhatchimage);
        }
        if(C2 == 3){
            cargoimage7.setVisibility(View.VISIBLE);
            cargoimage7.setImageResource(R.drawable.cargoandhatchimage);
        }
        if(C3 == 3){
            cargoimage6.setVisibility(View.VISIBLE);
            cargoimage6.setImageResource(R.drawable.cargoandhatchimage);
        }
        if(C4 == 3){
            cargoimage5.setVisibility(View.VISIBLE);
            cargoimage5.setImageResource(R.drawable.cargoandhatchimage);
        }
        if(C5 == 3){
            cargoimage4.setVisibility(View.VISIBLE);
            cargoimage4.setImageResource(R.drawable.cargoandhatchimage);
        }
        if(C6 == 3){
            cargoimage3.setVisibility(View.VISIBLE);
            cargoimage3.setImageResource(R.drawable.cargoandhatchimage);
        }
        if(C7 == 3){
            cargoimage2.setVisibility(View.VISIBLE);
            cargoimage2.setImageResource(R.drawable.cargoandhatchimage);
        }
        if(C8 == 3){
            cargoimage1.setVisibility(View.VISIBLE);
            cargoimage1.setImageResource(R.drawable.cargoandhatchimage);
        }
    }

    public void individualButton(RobotAction action){
        if (action.getActionCode().equals("C1")) {
            if(action.isHatch()){
                cargoimage1.setImageResource(R.drawable.cargoimage);
            }
            else if(!action.isHatch()){
                cargoimage1.setImageResource(R.drawable.hatchimage);
            }

        } else if (action.getActionCode().equals("C2")) {
            if(action.isHatch()){
                cargoimage2.setImageResource(R.drawable.cargoimage);
            }
            else if(!action.isHatch()){
                cargoimage2.setImageResource(R.drawable.hatchimage);
            }
        } else if (action.getActionCode().equals("C3")) {
            if(action.isHatch()){
                cargoimage3.setImageResource(R.drawable.cargoimage);
            }
            else if(!action.isHatch()){
                cargoimage3.setImageResource(R.drawable.hatchimage);
            }
        } else if (action.getActionCode().equals("C4")) {
            if(action.isHatch()){
                cargoimage4.setImageResource(R.drawable.cargoimage);
            }
            else if(!action.isHatch()){
                cargoimage4.setImageResource(R.drawable.hatchimage);
            }
        } else if (action.getActionCode().equals("C5")) {
            if(action.isHatch()){
                cargoimage5.setImageResource(R.drawable.cargoimage);
            }
            else if(!action.isHatch()){
                cargoimage5.setImageResource(R.drawable.hatchimage);
            }
        } else if (action.getActionCode().equals("C6")) {
            if(action.isHatch()){
                cargoimage6.setImageResource(R.drawable.cargoimage);
            }
            else if(!action.isHatch()){
                cargoimage6.setImageResource(R.drawable.hatchimage);
            }
        } else if (action.getActionCode().equals("C7")) {
            if(action.isHatch()){
                cargoimage7.setImageResource(R.drawable.cargoimage);
            }
            else if(!action.isHatch()){
                cargoimage7.setImageResource(R.drawable.hatchimage);
            }
        } else if (action.getActionCode().equals("C8")) {
            if(action.isHatch()){
                cargoimage8.setImageResource(R.drawable.cargoimage);
            }
            else if(!action.isHatch()){
                cargoimage8.setImageResource(R.drawable.hatchimage);
            }
        }
    }


}
