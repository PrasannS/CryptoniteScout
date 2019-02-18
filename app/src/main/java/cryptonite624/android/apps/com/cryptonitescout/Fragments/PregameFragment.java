package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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
    public Button toMapView;
    public EditText matchNum;
    public String message;
    public EditText teamNum;

    public TextView hatchDisplay;

    OnPregameReadListener pregameReadListener;

    public PregameFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //The variable view is not default
        View view = inflater.inflate(R.layout.fragment_pregame, container, false);

        /*toAuton = (Button)view.findViewById(R.id.pregame_auton);
        toAuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toAuton";
                pregameReadListener.OnPregameRead(message);
                pregameEntry = new PregameEntry();

            }
        });*/

        //hatchDisplay = (TextView)view.findViewById(R.id.pregame_hatchdisplay);



        /*toMapView = (Button)view.findViewById(R.id.prematch_mapview);
        toMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MapView.class);
                startActivity(i);
            }
        });*/

        //matchNum = (EditText) view.findViewById(R.id.matchnum);


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

    /*public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.settings_menu, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
