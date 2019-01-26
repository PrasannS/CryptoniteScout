package cryptonite624.android.apps.com.cryptonitescout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cryptonite624.android.apps.com.cryptonitescout.Fragments.AutonFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeftMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeftMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftMapFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button cargobutton1;
    Button cargobutton2;
    Button cargobutton3;
    Button cargobutton4;
    Button cargobutton5;
    Button cargobutton6;
    Button cargobutton7;
    Button cargobutton8;

    private OnFragmentInteractionListener mListener;

    OnLeftMapReadListener leftMapReadListener;

    public LeftMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftMapFragment newInstance(String param1, String param2) {
        LeftMapFragment fragment = new LeftMapFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left_map, container, false);

        cargobutton1 = (Button)view.findViewById(R.id.cargobutton1_left);
        cargobutton2 = (Button)view.findViewById(R.id.cargobutton2_left);
        cargobutton3 = (Button)view.findViewById(R.id.cargobutton3_left);
        cargobutton4 = (Button)view.findViewById(R.id.cargobutton4_left);
        cargobutton5 = (Button)view.findViewById(R.id.cargobutton5_left);
        cargobutton6 = (Button)view.findViewById(R.id.cargobutton6_left);
        cargobutton7 = (Button)view.findViewById(R.id.cargobutton7_left);
        cargobutton8 = (Button)view.findViewById(R.id.cargobutton8_left);

        cargobutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            leftMapReadListener = (OnLeftMapReadListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnLeftMapReadListener{
        public void OnLeftMapRead(int x, int y);
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
