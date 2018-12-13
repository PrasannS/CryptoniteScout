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
import android.widget.EditText;
import android.widget.TextView;

import cryptonite624.android.apps.com.cryptonitescout.Models.PregameEntry;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PregameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PregameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

//USE THIS CLASS AS REFERENCE FOR ANY FRAGMENT CODE YOU NEED TO DO. I CLEANED OUT A LOT OF UNECESSARY DEFAULT CODE THAT WOULD CONFUSE YOU
public class PregameFragment extends Fragment {
    //setup all of your buttons and class variables here
    public Button toAuton;
    public EditText matchNum;
    public String message;
    public EditText teamNum;
    public PregameEntry pregameEntry = new PregameEntry();

    OnPregameReadListener pregameReadListener;

    public PregameFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public interface OnPregameReadListener{
        public void OnPregameRead(String message);

        public void LoadPregameData(PregameEntry p);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //The variable view is not default
        View view = inflater.inflate(R.layout.fragment_pregame, container, false);

        toAuton = (Button)view.findViewById(R.id.prematch_auton);
        toAuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toAuton";
                pregameReadListener.OnPregameRead(message);

                pregameEntry = new PregameEntry();

            }
        });



        matchNum = (EditText) view.findViewById(R.id.matchnum);
        teamNum = (EditText) view.findViewById(R.id.teamnum);


        return view;
    }

    //This is important onAttatch represents essentially the first linkup between this fragment and the activity, interface instance created here

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            pregameReadListener = (PregameFragment.OnPregameReadListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        pregameReadListener = null;
    }



}
