package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RocketFragment} interface
 * to handle interaction events.
 * Use the {@link RocketFragment#} factory method to
 * create an instance of this fragment.
 */
public class RocketFragment extends Fragment {
    //setup all of your buttons and class variables here
    //public Button rocketHatch;
    //public Button rocketCargo;
    public String message;
    public Button br;
    public Button bl;
    public Button mr;
    public Button ml;
    public Button tr;
    public Button tl;
    
    public boolean top;

    public boolean brcf;
    public boolean blcf;
    public boolean mrcf;
    public boolean mlcf;
    public boolean trcf;
    public boolean tlcf;
    public boolean brhf;
    public boolean blhf;
    public boolean mrhf;
    public boolean mlhf;
    public boolean trhf;
    public boolean tlhf;
    ActionMap map;


    OnrocketReadListener rocketReadListener;

    public RocketFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void setArguments(ActionMap actionMap, boolean b) {
        map = actionMap;
        top = b;
        if(actionMap.getActionsList().size()!=0)
        if(top){
            for(int i = 0; i < actionMap.getActionsList().size(); i++){
                if(actionMap.getActionsList().get(i).getActionCode().equals("A1") && actionMap.getActionsList().get(i).isHatch()){
                    brhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A2")&& actionMap.getActionsList().get(i).isHatch()){
                    blhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A3")&& actionMap.getActionsList().get(i).isHatch()){
                    mrhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A4")&& actionMap.getActionsList().get(i).isHatch()){
                    mlhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A5")&& actionMap.getActionsList().get(i).isHatch()){
                    trhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A6")&& actionMap.getActionsList().get(i).isHatch()){
                    tlhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A1")&& !actionMap.getActionsList().get(i).isHatch()){
                    brcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A2")&& !actionMap.getActionsList().get(i).isHatch()){
                    blcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A3")&& !actionMap.getActionsList().get(i).isHatch()){
                    mrcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A4")&& !actionMap.getActionsList().get(i).isHatch()){
                    mlcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A5")&& !actionMap.getActionsList().get(i).isHatch()){
                    trcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("A6")&& !actionMap.getActionsList().get(i).isHatch()){
                    tlcf = true;
                }
            }
        }
        else{
            for(int i = 0; i < actionMap.getActionsList().size(); i++){
                if(actionMap.getActionsList().get(i).getActionCode().equals("B1") && actionMap.getActionsList().get(i).isHatch()){
                    brhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B2")&& actionMap.getActionsList().get(i).isHatch()){
                    blhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B3")&& actionMap.getActionsList().get(i).isHatch()){
                    mrhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B4")&& actionMap.getActionsList().get(i).isHatch()){
                    mlhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B5")&& actionMap.getActionsList().get(i).isHatch()){
                    trhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B6")&& actionMap.getActionsList().get(i).isHatch()){
                    tlhf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B1")&& !actionMap.getActionsList().get(i).isHatch()){
                    brcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B2")&& !actionMap.getActionsList().get(i).isHatch()){
                    blcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B3")&& !actionMap.getActionsList().get(i).isHatch()){
                    mrcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B4")&& !actionMap.getActionsList().get(i).isHatch()){
                    mlcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B5")&& !actionMap.getActionsList().get(i).isHatch()){
                    trcf = true;
                }
                else if(actionMap.getActionsList().get(i).getActionCode().equals("B6")&& !actionMap.getActionsList().get(i).isHatch()){
                    tlcf = true;
                }
            }
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

    /**
     *
     *
     * Scouting meeting debrief
     * TODO
     */

    public interface OnrocketReadListener{
        public void OnrocketRead(String message);
    }

    public int rocketCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //The variable view is not default
        View view = inflater.inflate(R.layout.fragment_rocket, container, false);

       /* toMapView = (Button)view.findViewById(R.id.prematch_mapview);
        toMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MapView.class);
                startActivity(i);
            }
        });*/

        /*rocketCargo = (Button)view.findViewById(R.id.rocketcargo);
        rocketCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "return";
                rocketReadListener.OnrocketRead(message);
            }
        });

        rocketHatch = (Button)view.findViewById(R.id.rocketisHatch());
        rocketHatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "return";
                rocketReadListener.OnrocketRead(message);
            }
        });*/

        br = (Button)view.findViewById(R.id.br);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "1";
                rocketCode = 1;
                rocketReadListener.OnrocketRead(message);
            }
        });

        bl = (Button)view.findViewById(R.id.bl);
        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "2";
                rocketCode = 2;
                rocketReadListener.OnrocketRead(message);
            }
        });

        mr = (Button)view.findViewById(R.id.mr);
        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "3";
                rocketCode = 3;
                rocketReadListener.OnrocketRead(message);
            }
        });

        ml = (Button)view.findViewById(R.id.ml);
        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "4";
                rocketCode = 4;
                rocketReadListener.OnrocketRead(message);
            }
        });

        tr = (Button)view.findViewById(R.id.tr);
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "5";
                rocketCode = 5;
                rocketReadListener.OnrocketRead(message);
            }
        });

        tl = (Button)view.findViewById(R.id.tl);
        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "6";
                rocketCode = 6;
                rocketReadListener.OnrocketRead(message);
            }
        });

        //if the isHatch() is in, it's jankredleft. if the cargo is in, it's yellow. if both, it's green

        if(brhf){
            br.setBackgroundColor(Color.RED);
        }
        if(blhf){
            bl.setBackgroundColor(Color.RED);
        }
        if(mrhf){
            mr.setBackgroundColor(Color.RED);
        }
        if(mlhf){
            ml.setBackgroundColor(Color.RED);
        }
        if(trhf){
            tr.setBackgroundColor(Color.RED);
        }
        if(tlhf){
            tl.setBackgroundColor(Color.RED);
        }
        if(brcf){
            br.setBackgroundColor(Color.YELLOW);
        }
        if(blcf){
            bl.setBackgroundColor(Color.YELLOW);
        }
        if(mrcf){
            mr.setBackgroundColor(Color.YELLOW);
        }
        if(mlcf){
            ml.setBackgroundColor(Color.YELLOW);
        }
        if(trcf){
            tr.setBackgroundColor(Color.YELLOW);
        }
        if(tlcf){
            tl.setBackgroundColor(Color.YELLOW);
        }

        if(brcf && brhf){
            br.setBackgroundColor(Color.GREEN);
        }
        if(blcf && brhf){
            bl.setBackgroundColor(Color.GREEN);
        }
        if(mrcf && mrhf){
            mr.setBackgroundColor(Color.GREEN);
        }
        if(mlcf && mlhf){
            ml.setBackgroundColor(Color.GREEN);
        }
        if(trcf && trhf){
            tr.setBackgroundColor(Color.GREEN);
        }
        if(tlcf && tlhf){
            tl.setBackgroundColor(Color.GREEN);
        }

        return view;
    }

    //This is important onAttatch represents essentially the first linkup between this fragment and the activity, interface instance created here

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            rocketReadListener = (RocketFragment.OnrocketReadListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        rocketReadListener = null;
    }

    /*public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.settings_menu, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
