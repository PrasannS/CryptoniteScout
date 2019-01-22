package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashboardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 * configuration database
 * admin tab - usernames, position
 * user data
 * cycle times
 */
public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Button toMatches;
    public Button toPitnotes;
    public Button toRankings;
    public Button toMapview;
    private String message;

    OnDashboardReadListener dashboardReadListener;

    private AutonFragment.OnFragmentInteractionListener mListener;

    public DashboardFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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

    public interface OnDashboardReadListener{
        public void OnDashboardRead(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        toPitnotes = (Button)view.findViewById(R.id.pitnotebutton);
        toPitnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toPitnotes";

                dashboardReadListener.OnDashboardRead(message);
            }
        });

        toMatches = (Button)view.findViewById(R.id.matchesbutton);
        toMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toMatch";
                dashboardReadListener.OnDashboardRead(message);
            }
        });

        toRankings = (Button)view.findViewById(R.id.rankingbutton);
        toRankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toRankings";
                dashboardReadListener.OnDashboardRead(message);
            }
        });

        toMapview = (Button)view.findViewById(R.id.newentrybutton);
        toMapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toMapview";
                dashboardReadListener.OnDashboardRead(message);
            }
        });

        toPitnotes = (Button)view.findViewById(R.id.pitnotebutton);
        toPitnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "toPitnote";
                dashboardReadListener.OnDashboardRead(message);
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
        Activity activity = (Activity)context;
        try{
            dashboardReadListener = (DashboardFragment.OnDashboardReadListener) activity;
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
