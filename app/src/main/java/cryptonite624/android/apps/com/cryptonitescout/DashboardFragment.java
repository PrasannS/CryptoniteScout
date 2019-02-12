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

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;

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

        /*

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
        });*/

        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
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
        seriesData.add(new CustomDataEntry("1", 3.6, 2.3, 2.8));
        seriesData.add(new CustomDataEntry("2", 7.1, 4.0, 4.1));
        seriesData.add(new CustomDataEntry("3", 8.5, 6.2, 5.1));
        seriesData.add(new CustomDataEntry("4", 9.2, 11.8, 6.5));
        seriesData.add(new CustomDataEntry("5", 10.1, 13.0, 12.5));
        seriesData.add(new CustomDataEntry("6", 11.6, 13.9, 18.0));
        seriesData.add(new CustomDataEntry("7", 16.4, 18.0, 21.0));
        seriesData.add(new CustomDataEntry("8", 18.0, 23.3, 20.3));
        seriesData.add(new CustomDataEntry("9", 13.2, 24.7, 19.2));
        seriesData.add(new CustomDataEntry("10", 12.0, 18.0, 14.4));

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

        anyChartView.setChart(cartesian);

        
        

        return view;
    }
    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
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
