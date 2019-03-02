package cryptonite624.android.apps.com.cryptonitescout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapDrawFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapDrawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapDrawFragment extends Fragment implements BluetoothHandler.BluetoothListener{


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public void OnBluetoothRead(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
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
