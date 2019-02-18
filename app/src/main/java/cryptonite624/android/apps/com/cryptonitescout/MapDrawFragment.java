package cryptonite624.android.apps.com.cryptonitescout;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapDrawFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapDrawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapDrawFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button clearButton;
    Button colorSelector;
    Button saveButton;
    private PaintView paintView;
    private AlertDialog dialogColor;
    private AlertDialog.Builder currentAlertDialog;
    Button greenButton;
    Button redButton;
    Button blueButton;
    Button blackButton;
    Button whiteButton;



    public MapDrawFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapDrawFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapDrawFragment newInstance(String param1, String param2) {
        MapDrawFragment fragment = new MapDrawFragment();
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
        View view = inflater.inflate(R.layout.fragment_map_draw, container, false);

        paintView = (PaintView)view.findViewById(R.id.paint_view);

        colorSelector = (Button) view.findViewById(R.id.colorselector);

        saveButton = (Button)view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.saveToInternalStorage();
            }
        });

        clearButton = (Button)view.findViewById(R.id.drawing_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.clear();
            }
        });

        colorSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorSelectorDialog();
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
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    void showColorSelectorDialog(){
        currentAlertDialog = new AlertDialog.Builder(this.getContext());
        View view = getLayoutInflater().inflate(R.layout.color_dialog, null);

        greenButton = (Button)view.findViewById(R.id.greenButton);
        redButton = (Button)view.findViewById(R.id.redButton);
        blueButton = (Button)view.findViewById(R.id.blueButton);
        blackButton = (Button)view.findViewById(R.id.blackButton);
        whiteButton = (Button)view.findViewById(R.id.whiteButton);

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setDrawingColor(Color.GREEN);
                dialogColor.dismiss();
                currentAlertDialog = null;
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setDrawingColor(Color.RED);
                dialogColor.dismiss();
                currentAlertDialog = null;
            }
        });
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setDrawingColor(Color.BLUE);
                dialogColor.dismiss();
                currentAlertDialog = null;
            }
        });
        blackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setDrawingColor(Color.BLACK);
                dialogColor.dismiss();
                currentAlertDialog = null;
            }
        });
        whiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setDrawingColor(Color.WHITE);
                dialogColor.dismiss();
                currentAlertDialog = null;
            }
        });

        currentAlertDialog.setView(view);

        dialogColor = currentAlertDialog.create();
        dialogColor.show();


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
