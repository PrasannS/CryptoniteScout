package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Activity;
import android.content.Context;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
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
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Button toMatch;
    public Button toDashboard;
    private String message;

    private TextView teamName;
    private TextView teamNum;
    private TextView teamRank;
    private TextView teamRP;
    private TextView cargoTotal;
    private TextView hatchTotal;
    private TextView cargoAverage;
    private TextView hatchAverage;
    private TextView climbLevel;

    OnTeamReadListener teamReadListener;

    private AutonFragment.OnFragmentInteractionListener mListener;

    public TeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public interface OnTeamReadListener{
        public void OnTeamRead(String message);
    }


    public void loadData(List<ActionMap> maps){
        cargoTotal.setText("" + ActionMapUtils.tournamentTotalCargos(maps));
        hatchTotal.setText("" + ActionMapUtils.tournamentTotalHatches(maps));
        cargoAverage.setText("" + ActionMapUtils.tournamentAverageCargo(maps));
        hatchAverage.setText("" + ActionMapUtils.tournamentAverageHatch(maps));
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_team, container, false);

        teamName = view.findViewById(R.id.team_teamname);
        teamNum = view.findViewById(R.id.team_teamnum);
        teamRank = view.findViewById(R.id.team_teamrank);
        teamRP = view.findViewById(R.id.team_teamrp);
        cargoTotal = view.findViewById(R.id.team_totalcargo);
        hatchTotal = view.findViewById(R.id.team_totalhatch);
        cargoAverage = view.findViewById(R.id.team_averagecargo);
        hatchAverage = view.findViewById(R.id.team_averagehatch);
    /*

        AnyChartView lineGraphView = view.findViewById(R.id.team_line_graph);

        Cartesian cartesian = AnyChart.line();

        lineGraphView.setChart(cartesian);



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
        seriesData.add(new TeamFragment.CustomDataEntry("1", 3.6, 2.3, 2.8));
        seriesData.add(new TeamFragment.CustomDataEntry("2", 7.1, 4.0, 4.1));
        seriesData.add(new TeamFragment.CustomDataEntry("3", 8.5, 6.2, 5.1));
        seriesData.add(new TeamFragment.CustomDataEntry("4", 9.2, 11.8, 6.5));
        seriesData.add(new TeamFragment.CustomDataEntry("5", 10.1, 13.0, 12.5));
        seriesData.add(new TeamFragment.CustomDataEntry("6", 11.6, 13.9, 18.0));
        seriesData.add(new TeamFragment.CustomDataEntry("7", 16.4, 18.0, 21.0));
        seriesData.add(new TeamFragment.CustomDataEntry("8", 18.0, 23.3, 20.3));
        seriesData.add(new TeamFragment.CustomDataEntry("9", 13.2, 24.7, 19.2));
        seriesData.add(new TeamFragment.CustomDataEntry("10", 12.0, 18.0, 14.4));

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



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void setArguments(List<ActionMap>r){
        loadData(r);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            teamReadListener = (OnTeamReadListener) activity;
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
