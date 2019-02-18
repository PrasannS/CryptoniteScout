package cryptonite624.android.apps.com.cryptonitescout.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cryptonite624.android.apps.com.cryptonitescout.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EndgameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EndgameFragment} factory method to
 * create an instance of this fragment.
 */
public class EndgameFragment extends Fragment {
    public int temp;

    private OnFragmentInteractionListener mListener;
    public Button toTeleop;
    public Button submit;
    public String message;



    OnEndgameReadListener endgameReadListener;

    public EndgameFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface OnEndgameReadListener{
        public void OnEndgameRead(String message);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_endgame, container, false);

        toTeleop = (Button)view.findViewById(R.id.endgame_teleop);
        toTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toTeleop";
                endgameReadListener.OnEndgameRead(message);
            }
        });


        submit = (Button)view.findViewById(R.id.endgame_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "toReview";
                endgameReadListener.OnEndgameRead(message);
                System.out.println("Sent Submission");
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            endgameReadListener = (EndgameFragment.OnEndgameReadListener)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        endgameReadListener = null;
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
