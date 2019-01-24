package cryptonite624.android.apps.com.cryptonitescout;

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
import android.widget.FrameLayout;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.Match;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Button toMatches;
    public Button redteam1;
    public Button toDashboard;
    private String message;

    OnMatchReadListener matchReadListener;
    FragmentManager fragmentManager;

    private AutonFragment.OnFragmentInteractionListener mListener;

    public MatchFragment() {
        // Required empty public constructor

    }

    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance(Match m) {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
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

    public interface OnMatchReadListener{
        public void OnMatchRead(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_match, container, false);

        toDashboard = (Button)view.findViewById(R.id.toDashboard_match);
        toDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toDashboard";
                matchReadListener.OnMatchRead(message);
            }
        });

        redteam1 = (Button)view.findViewById(R.id.redteam1);
        redteam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toTeam";
                matchReadListener.OnMatchRead(message);
            }
        });

        fragmentManager = getChildFragmentManager();
        if(view.findViewById(R.id.mapdisplaycontainer)!=null){
            MatchAccessFragment leftMapFragment= new MatchAccessFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.mapdisplaycontainer,leftMapFragment,null);
            fragmentTransaction.commit();

        }

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
            matchReadListener = (OnMatchReadListener) activity;
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
