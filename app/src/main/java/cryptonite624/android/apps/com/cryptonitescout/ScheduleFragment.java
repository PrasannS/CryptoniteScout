package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;
import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;
import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

import static cryptonite624.android.apps.com.cryptonitescout.ScheduleActivity.fragmentManager;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String INPUTS = "inputs";

    de.codecrafters.tableview.TableView<String []> TableView;

    public static String [] Schedule_headers = {"Match #","teams","placeholder","test"};

    public static String [][] Schedule_datas = {{"Team#","test","data","placeholder"},
            {"Another","testdata","goes","here"}};



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int[] inputs;

    private OnFragmentInteractionListener mListener;

    private Button matchnum;
    private Button red1;
    private Button red2;
    private Button red3;
    private Button blue1;
    private Button blue2;
    private Button blue3;

    public String message;

    OnScheduleRead onScheduleRead;



    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(int[] inputs) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putIntArray(INPUTS,inputs);
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnScheduleRead{
        public void OnScheduleRead(String message);
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        TableView = (TableView<String[]>)view.findViewById(R.id.tableView_schedule);
        TableView.setDataAdapter(new SimpleTableDataAdapter(getContext(), Schedule_datas));
        TableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), Schedule_headers));
        TableView.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Toast.makeText(getContext(), clickedData[0], Toast.LENGTH_SHORT).show();
            }
        });


        matchnum = (Button)(view.findViewById(R.id.matchnum));
        matchnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toAuton";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        red1 = (Button)(view.findViewById(R.id.red1));
        red1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toAuton";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        red2 = (Button)(view.findViewById(R.id.red2));
        red2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toAuton";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        red3 = (Button)(view.findViewById(R.id.red3));
        red3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toAuton";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        blue1 = (Button)(view.findViewById(R.id.blue1));
        blue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toAuton";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        blue2 = (Button)(view.findViewById(R.id.blue2));
        blue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toAuton";
                onScheduleRead.OnScheduleRead(message);
            }
        });

        blue3 = (Button)(view.findViewById(R.id.blue3));
        blue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toAuton";
                onScheduleRead.OnScheduleRead(message);
            }
        });


        return view;


    }
    public View view;

    public void addAllRows(ArrayList<Schedule> data) {
        ScheduleFragment temp;
        for(Schedule r:data){
            temp = new ScheduleFragment();
            temp.setArguments(r);
            if (view.findViewById(R.id.infoframe) != null) {
                cryptonite624.android.apps.com.cryptonitescout.PregameFragment pregameFragment = new cryptonite624.android.apps.com.cryptonitescout.PregameFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.infoframe, pregameFragment, null);
                fragmentTransaction.commit();
            }
        }
    }

    public void putDatasinTable() {
        String [] data = new String[]
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
            onScheduleRead = (ScheduleFragment.OnScheduleRead) activity;
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