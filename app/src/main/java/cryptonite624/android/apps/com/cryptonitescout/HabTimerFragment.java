package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HabTimerFragment.OnHabTimerReadListener} interface
 * to handle interaction events.
 * Use the {@link HabTimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HabTimerFragment extends Fragment {


    public TextView timeDisplay;
    public Button timerStop;
    public String message;
    public CountUpTimer timer;

    OnHabTimerReadListener onHabTimerReadListener;

    public interface OnHabTimerReadListener{
        public void OnHabTimerRead(String message);
    }

    public HabTimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hab_timer, container, false);

        timeDisplay = view.findViewById(R.id.habtimer_display);
        timerStop = view.findViewById(R.id.habtimer_stop);

        timer = new CountUpTimer(30000) {
            public void onTick(int second) {
                timeDisplay.setText(String.valueOf(second));
            }
        };

        timer.start();

        timerStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "" + timer.toString();
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            onHabTimerReadListener = (cryptonite624.android.apps.com.cryptonitescout.HabTimerFragment.OnHabTimerReadListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onHabTimerReadListener = null;
    }

    public abstract class CountUpTimer extends CountDownTimer {
        private static final long INTERVAL_MS = 1000;
        private final long duration;

        protected CountUpTimer(long durationMs) {
            super(durationMs, INTERVAL_MS);
            this.duration = durationMs;
        }

        public abstract void onTick(int second);

        @Override
        public void onTick(long msUntilFinished) {
            int second = (int) ((duration - msUntilFinished) / 1000);
            onTick(second);
        }

        @Override
        public void onFinish() {
            onTick(duration / 1000);
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
    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}}
