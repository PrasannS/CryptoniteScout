package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

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
    public String message;

    TableView<String[]> tableView;

    private OnFragmentInteractionListener mListener;

    OnRankingRead onRankingRead;

    public DaoSession daoSession;

    private static final String[] TABLE_HEADERS = { "This", "is", "a", "test" };


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rankings, container, false);
        daoSession = ((CRyptoniteApplication)getActivity().getApplication()).getDaoSession();
        List<RankingData> rankings = daoSession.getRankingDataDao().loadAll();
        tableView = (TableView<String[]>) view.findViewById(R.id.tableView);
        tableView.setDataAdapter(new SimpleTableDataAdapter(getContext(), getArrfromRanking(rankings)));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), TABLE_HEADERS));
        tableView.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Toast.makeText(getContext(), clickedData[0], Toast.LENGTH_SHORT).show();
            }
        });

        /*

        Rankings_ranking = (TextView) (view.findViewById(R.id.Rankings_rankings));

        Teamnum_rankings = (TextView) (view.findViewById(R.id.Teams_rankings));

        Winrate_rankings = (TextView) (view.findViewById(R.id.Winrate_rankings));

        Rankings_ranking = (TextView) (view.findViewById(R.id.Rankings_rankings));

        PlaceHolder_rankings = (TextView) (view.findViewById(R.id.PlaceHolder_rankings));

        Phasese_rankings = (TextView) (view.findViewById(R.id.Phases_rankings));*/



        view2 = view;
        return view;
    }
    public FragmentManager fragmentManager;
    public View view2;

    public String[][] getArrfromRanking(List<RankingData> datas) {
        String [][] data = new String[datas.size()][31];
        int cur = 0;
        for(RankingData s:datas){
            data[cur]= rankingtoString(s);
            cur++;
        }
        return data;
    }

    public String [] rankingtoString(RankingData data){
        String [] datas = {data.getRankpoint()+"",data.getTotalwins()+"",data.getTeamnum()+"",data.getMatchesplayed()+"",data.getTotalcargo()+"",
                            data.getTotalhatches()+"",data.getClimbone()+"",data.getClimbtwo()+"",data.getClimbthree()+"",data.getClimbfailed()+"",
                            data.getTeamkey(),data.getId()+""};
        return datas;
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
        /*
        Activity activity = (Activity)context;
        try{
            onRankingRead = (RankingFragment.OnRankingRead) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must override onkeyboardoneread");
        }*/
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