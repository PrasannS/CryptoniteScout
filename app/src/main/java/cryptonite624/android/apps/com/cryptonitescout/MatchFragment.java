package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;


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
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<String> comms = new ArrayList<String>();
    
    public TextView redtotalpoints1;
    public TextView redtotalhatch1;
    public TextView redtotalcargo1;
    public TextView redtotalpoints2;
    public TextView redtotalhatch2;
    public TextView redtotalcargo2;
    public TextView redtotalpoints3;
    public TextView redtotalhatch3;
    public TextView redtotalcargo3;

    public TextView bluetotalpoints1;
    public TextView bluetotalhatch1;
    public TextView bluetotalcargo1;
    public TextView bluetotalpoints2;
    public TextView bluetotalhatch2;
    public TextView bluetotalcargo2;
    public TextView bluetotalpoints3;
    public TextView bluetotalhatch3;
    public TextView bluetotalcargo3;
    


    OnMatchReadListener matchReadListener;
    FragmentManager fragmentManager;

    private AutonFragment.OnFragmentInteractionListener mListener;

    public MatchFragment() {
        // Required empty public constructor

    }

    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance(FieldClassification.Match m) {
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

    private void initRecyclerView(){
        comms.add("This is a test commentsss");
        comms.add("Test comment 2");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), comms);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_match, container, false);
        /*
        if (view.findViewById(R.id.match_access_layout) != null) {
            MatchAccessFragment matchAccessFragment= new MatchAccessFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.match_access_layout, matchAccessFragment , null);
            fragmentTransaction.commit();
        }*/

        //mRecyclerView = view.findViewById(R.id.recyclerview_comments);
        initRecyclerView();


        //pie chart

        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("624", 10000));
        data.add(new ValueDataEntry("118", 12000));
        data.add(new ValueDataEntry("3310", 18000));
        pie.data(data);
        //AnyChartView anyChartView = (AnyChartView) view.findViewById(R.id.match_piechart);

        pie.data(data);

        pie.title("Team Contribution in Alliance");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Teams")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        //anyChartView.setChart(pie);



        //line graph

        /*


        lineGraphView.setChart(cartesian); AnyChartView lineGraphView = view.findViewById(R.id.any_chart_view_match);
        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Scores per Match");

        cartesian.yAxis(0).title("Score");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new MatchFragment.CustomDataEntry("1", 3.6, 2.3, 2.8));
        seriesData.add(new MatchFragment.CustomDataEntry("2", 7.1, 4.0, 4.1));
        seriesData.add(new MatchFragment.CustomDataEntry("3", 8.5, 6.2, 5.1));
        seriesData.add(new MatchFragment.CustomDataEntry("4", 9.2, 11.8, 6.5));
        seriesData.add(new MatchFragment.CustomDataEntry("5", 10.1, 13.0, 12.5));
        seriesData.add(new MatchFragment.CustomDataEntry("6", 11.6, 13.9, 18.0));
        seriesData.add(new MatchFragment.CustomDataEntry("7", 16.4, 18.0, 21.0));
        seriesData.add(new MatchFragment.CustomDataEntry("8", 18.0, 23.3, 20.3));
        seriesData.add(new MatchFragment.CustomDataEntry("9", 13.2, 24.7, 19.2));
        seriesData.add(new MatchFragment.CustomDataEntry("10", 12.0, 18.0, 14.4));

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("624");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("118");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("3310");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

*/

        return view;

    }
    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }

        /*
        fragmentManager = getChildFragmentManager();
        if(view.findViewById(R.id.mapdisplaycontainer)!=null){
            MatchAccessFragment leftMapFragment= new MatchAccessFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.mapdisplaycontainer,leftMapFragment,null);
            fragmentTransaction.commit();
        }*/


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    
    public void loadData(ArrayList<ActionMap> mapList){
        redtotalpoints1.setText(ActionMapUtils.totalPoints(mapList.get(0).actions));
        redtotalhatch1.setText(ActionMapUtils.totalhatches(true, mapList.get(0).actions));
        redtotalcargo1.setText(ActionMapUtils.totalhatches(false, mapList.get(0).actions));

        redtotalpoints2.setText(ActionMapUtils.totalPoints(mapList.get(1).actions));
        redtotalhatch2.setText(ActionMapUtils.totalhatches(true, mapList.get(1).actions));
        redtotalcargo2.setText(ActionMapUtils.totalhatches(false, mapList.get(1).actions));

        redtotalpoints3.setText(ActionMapUtils.totalPoints(mapList.get(2).actions));
        redtotalhatch3.setText(ActionMapUtils.totalhatches(true, mapList.get(2).actions));
        redtotalcargo3.setText(ActionMapUtils.totalhatches(false, mapList.get(2).actions));

        bluetotalpoints1.setText(ActionMapUtils.totalPoints(mapList.get(3).actions));
        bluetotalhatch1.setText(ActionMapUtils.totalhatches(true, mapList.get(3).actions));
        bluetotalcargo1.setText(ActionMapUtils.totalhatches(false, mapList.get(3).actions));

        bluetotalpoints2.setText(ActionMapUtils.totalPoints(mapList.get(4).actions));
        bluetotalhatch2.setText(ActionMapUtils.totalhatches(true, mapList.get(4).actions));
        bluetotalcargo2.setText(ActionMapUtils.totalhatches(false, mapList.get(4).actions));

        bluetotalpoints3.setText(ActionMapUtils.totalPoints(mapList.get(5).actions));
        bluetotalhatch3.setText(ActionMapUtils.totalhatches(true, mapList.get(5).actions));
        bluetotalcargo3.setText(ActionMapUtils.totalhatches(false, mapList.get(5).actions));
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
