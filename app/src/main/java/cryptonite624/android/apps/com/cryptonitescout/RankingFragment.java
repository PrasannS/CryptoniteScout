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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RankingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String INPUTS = "inputs";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int[] inputs;


    private TextView Rankings_ranking;
    private TextView Teamnum_rankings;
    private TextView Winrate_rankings;
    private TextView RankPoints_rankings;
    private TextView PlaceHolder_rankings;
    private TextView Phasese_rankings;

    private double Winrate;
    private int Rankpoints;
    private String Teamnum;
    private String Rankings;
    private String Placeholder;
    private String Phases;

    public String message;

    private OnFragmentInteractionListener mListener;

    OnRankingRead onRankingRead;

    public RankingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RankingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void setArguments(RankingData rankdata){
        Rankings_ranking.setText(rankdata.Ranking);
        Teamnum_rankings.setText(rankdata.Teamnum);
        Winrate_rankings.setText(rankdata.winRate);
        RankPoints_rankings.setText(rankdata.RankPoint);
        Phasese_rankings.setText(rankdata.phase);
        PlaceHolder_rankings.setText(rankdata.placeHolder);
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
        View view = inflater.inflate(R.layout.fragment_rankings, container, false);

        Rankings_ranking = (TextView) (view.findViewById(R.id.Rankings_rankings));

        Teamnum_rankings = (TextView) (view.findViewById(R.id.Teams_rankings));

        Winrate_rankings = (TextView) (view.findViewById(R.id.Winrate_rankings));

        Rankings_ranking = (TextView) (view.findViewById(R.id.Rankings_rankings));

        PlaceHolder_rankings = (TextView) (view.findViewById(R.id.PlaceHolder_rankings));

        Phasese_rankings = (TextView) (view.findViewById(R.id.Phases_rankings));



        view2 = view;
        return view;
    }
    public FragmentManager fragmentManager;
    public View view2;

    public void addAllRows(ArrayList<RankingData> data) {
        RankingFragment temp;
        for(RankingData r:data){
            temp = new RankingFragment();
            temp.setArguments(r);
            if (view2.findViewById(R.id.infoframe) != null) {
                cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment = new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.infoframe, pregameFragment, null);
                fragmentTransaction.commit();
            }
        }
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
            onRankingRead = (RankingFragment.OnRankingRead) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must override onkeyboardoneread");
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

    public interface OnRankingRead{
        public void OnRankingRead(String message);
    }
}