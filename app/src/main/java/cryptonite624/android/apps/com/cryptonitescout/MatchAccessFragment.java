package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jaygoo.widget.RangeSeekBar;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.PregameFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchAccessFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchAccessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchAccessFragment extends Fragment implements LeftMapFragment.OnLeftMapReadListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    FragmentManager fragmentManager;
    public ActionMap map;
    public int totalactions = 0;
    public int currentaction = 0;
    public RangeSeekBar rangeSeekBar;
    public int timeinterval = 2;
    public boolean cancel = false;
    public Timer timer;
    public Button playbutton;
    public boolean play = false;
    public RangeSeekBar intervalbar;
    public LeftMapFragment leftMapFragment;

    public MatchAccessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchAccessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchAccessFragment newInstance(String param1, String param2) {
        MatchAccessFragment fragment = new MatchAccessFragment();
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

    public void setArguments(ActionMap actionMap) {
        map = actionMap;
        updateParams();
    }

    public void updateParams(){
        totalactions = map.getActionsList().size();
        rangeSeekBar.setRange(0,totalactions,1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_match_access, container, false);

        fragmentManager = getFragmentManager();
        if(view.findViewById(R.id.videomnapcontainer)!=null){
            leftMapFragment = new LeftMapFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.videomnapcontainer,leftMapFragment,null);
            fragmentTransaction.commit();
        }

        rangeSeekBar=view.findViewById(R.id.matchscrubber);
        playbutton=view.findViewById(R.id.playbutton);
        intervalbar = view.findViewById(R.id.shiftspeed);
        intervalbar.setRange(2,5,1,5);
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play){
                    runThroughActions();
                }
                else
                    timer.cancel();
            }
        });



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

    public void runThroughActions(){
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                currentaction++;
                rangeSeekBar.setValue(currentaction);
                //put all layout changing code here
                leftMapFragment.individualButton(map.getActionsList().get(currentaction));
            }
        };

        timer = new Timer("Timer");

        long delay  = timeinterval*1000;
        long period = (totalactions-currentaction)*timeinterval*1000;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    @Override
    public void OnLeftMapRead(int x, int y) {

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
