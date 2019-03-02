package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubmissionReviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubmissionReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmissionReviewFragment extends Fragment {


    //private OnFragmentInteractionListener mListener;

    OnSubmissionListener submissionReadListener;

    private TextView totalHatches;
    private TextView totalCargos;
    private TextView climbLevel;
    private Button submissionButton;
    private ActionMap map;
    private Button replayButton;
    private TextView winMatch;
    private boolean matchWon;


    public SubmissionReviewFragment() {
        // Required empty public constructor
    }

    public interface OnSubmissionListener{
        public void OnSubmissionRead(String message);
    }
    public void setArguments(ActionMap actionMap, boolean MatchWon) {
        map = actionMap;
        matchWon = MatchWon;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submission_review, container, false);

        //replayButton = view.findViewById(R.id.openreplay);
        totalHatches = view.findViewById(R.id.submission_hatchnum);
        totalCargos = view.findViewById(R.id.submission_cargonum);
        climbLevel = view.findViewById(R.id.submission_hablevel);
        submissionButton = view.findViewById(R.id.submission_submit);
        winMatch = view.findViewById(R.id.submission_winloss);


        totalHatches.setText("" + ActionMapUtils.totalhatches(false, map.getActionsList()));
        totalCargos.setText("" + ActionMapUtils.totalhatches(true, map.getActionsList()));
        climbLevel.setText("" + map.getEndclimb());
        winMatch.setText("" + matchWon);

        submissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Congrats on submitting!",
                        Toast.LENGTH_LONG).show();

                submissionReadListener.OnSubmissionRead("submit");
            }
        });

//        replayButton.setOnClickListener(new View.OnClickListener() {
  //          @Override
  //          public void onClick(View v) {
  //              submissionReadListener.OnSubmissionRead("replay");
  //          }
  //      });





        // Inflate the layout for this fragment
        return view;

    }

    /*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            submissionReadListener = (cryptonite624.android.apps.com.cryptonitescout.SubmissionReviewFragment.OnSubmissionListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        submissionReadListener= null;
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
