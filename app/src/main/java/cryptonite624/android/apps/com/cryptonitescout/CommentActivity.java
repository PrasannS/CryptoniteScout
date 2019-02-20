package cryptonite624.android.apps.com.cryptonitescout;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;

import cryptonite624.android.apps.com.cryptonitescout.Models.Comment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;
import cryptonite624.android.apps.com.cryptonitescout.Utils.CommentUtils;

public class CommentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener,BluetoothHandler.BluetoothListener{
    private Button submit;
    private EditText commentget;
    private String comment;
    private EditText teamnameget;
    private String teamname;

    private Spinner Rating;
    private String RatingLevel;
    private String [] Levels = {"1","2","3","4","5","6"};

    private Spinner Driver;
    private String DriverRatings;
    private String [] DriverRatingLevels = {"1","2","3","4","5","6"};

    //private Spinner Defense;
    //private String DefenseRatings;
    private String [] DefenseRatingLevels = {"1","2","3","4","5","6"};

    private int HatchEfficiency;
    private int CargoEfficiency;
    private boolean ExcessiveFoul;
    private String BrokenComment;
    private EditText WhyBroken;
    private Switch brokenswitch;

    private RangeSeekBar hatchefficiencySeekbar;
    private RangeSeekBar cargoefficiencySeekbar;
    private RangeSeekBar defenseratingSeekbar;

    public Comment comm;



    public int recordeddevices = 0;




    public Map<String,String> lastmessages  = new HashMap<>();
    public static String regex = "0624";

    private BluetoothAdapter bluetoothAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        comm = new Comment();

        submit= (Button)findViewById(R.id.Submitcomments);
        commentget= (EditText)findViewById(R.id.commentsave);
        comment = commentget.getText().toString();
        brokenswitch = findViewById(R.id.brokenswitch);
        //teamnameget = (EditText) findViewById(R.id.TeamName);
        //teamname = teamnameget.getText().toString();
        cargoefficiencySeekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                comm.cargoefficiency = (int)rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //start tracking touch
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //stop tracking touch
            }
        });
        hatchefficiencySeekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                comm.hatchefficiency = (int)rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //start tracking touch
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //stop tracking touch
            }
        });
        defenseratingSeekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                comm.defense = (int)rightValue;
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //start tracking touch
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view,  boolean isLeft) {
                //stop tracking touch
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDiscovery();
                sendMessage();
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        // Your database code here
                        startDiscovery();
                        ensureDiscoverable();
                    }
                }, 3*1000, 3*1000);

                comm.comment = commentget.getText().toString();
                comm.whybroken = WhyBroken.getText().toString();
                comm.broken = brokenswitch.isChecked();

                comm.save();
            }
        });
        /*Rating = findViewById(R.id.Rating);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Levels);
        Rating.setAdapter(adapter);

        Driver = findViewById(R.id.DriverRating);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,DriverRatingLevels);
        Driver.setAdapter(adapter1);
*/
        //Defense = findViewById(R.id.DefenseRating);
        //ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,DefenseRatingLevels);
        //Defense.setAdapter(adapter2);

        hatchefficiencySeekbar = findViewById(R.id.hatchefficiency_seekbar);
        hatchefficiencySeekbar.setRange(1f, 10f);
        hatchefficiencySeekbar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_NUMBER);

        cargoefficiencySeekbar = findViewById(R.id.cargoefficiency_seekbar);
        hatchefficiencySeekbar.setRange(1f, 10f);
        hatchefficiencySeekbar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_NUMBER);

        defenseratingSeekbar = findViewById(R.id.defenseeffeciency_seekbar);
        hatchefficiencySeekbar.setRange(1f, 10f);
        hatchefficiencySeekbar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_NUMBER);
    }
    /*public void goToDashboard()
    {
        Intent intent1 = new Intent(this, MapView.class);
        startActivity(intent1);
    }*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //setting the dropdown elements and displaying the texts for the programming languages
        RatingLevel = Levels[position];
        Toast.makeText(parent.getContext(), RatingLevel, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the hatch intakes
        DriverRatings = DriverRatingLevels[position];
        Toast.makeText(parent.getContext(), DriverRatings, Toast.LENGTH_SHORT).show();

        //setting the dropdown elements and displaying the texts for the cargo intakes
        //DefenseRatings = DefenseRatingLevels[position];
        //Toast.makeText(parent.getContext(), DefenseRatings, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }


    /******************************************************************************************************************/

    private void sendMessage() {
        final String sNewName = "0624"+'f'+comment.toString();
        final BluetoothAdapter myBTAdapter = BluetoothAdapter.getDefaultAdapter();
        final long lTimeToGiveUp_ms = System.currentTimeMillis() + 10000;
        if (myBTAdapter != null)
        {
            String sOldName = myBTAdapter.getName();
            if (sOldName.equalsIgnoreCase(sNewName) == false)
            {
                final Handler myTimerHandler = new Handler();
                myBTAdapter.enable();
                myTimerHandler.postDelayed(
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                if (myBTAdapter.isEnabled())
                                {
                                    myBTAdapter.setName(sNewName);
                                    if (sNewName.equalsIgnoreCase(myBTAdapter.getName()))
                                    {
                                        Log.i("hi", "Updated BT Name to " + myBTAdapter.getName());
                                    }
                                }
                                if ((sNewName.equalsIgnoreCase(myBTAdapter.getName()) == false) && (System.currentTimeMillis() < lTimeToGiveUp_ms))
                                {
                                    myTimerHandler.postDelayed(this, 500);
                                    if (myBTAdapter.isEnabled())
                                        Log.i("hi", "Update BT Name: waiting on BT Enable");
                                    else
                                        Log.i("hi", "Update BT Name: waiting for Name (" + sNewName + ") to set in");
                                }
                            }
                        } , 500);
            }
        }
    }

    private void startDiscovery() {
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }

        bluetoothAdapter.startDiscovery();
    }

    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            startFetch(bluetoothDevice);
            String temp;
            String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
            if(name!=null)
                if(name.contains(regex)){
                    temp = lastmessages.put(bluetoothDevice.getAddress(),name);
                    if(temp==null){
                        if (name.charAt(regex.length()) == 'f') {
                            //tempmap.parseString(name.substring(regex.length()));
                            Comment comment3 = CommentUtils.parseComment(name.substring(regex.length()));
                            comment3.save();
                            recordeddevices++;
                        }
                    }
                    else if(!temp.equals(name)){
                        if (name.charAt(regex.length()) == 'f') {
                            ActionMapUtils.parseActionMap(name.substring(regex.length()));
                            recordeddevices++;
                        }
                    }
                }
        }
    };

    private void ensureDiscoverable() {
        if (bluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(
                    BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
            startActivity(discoverableIntent);
        }
    }

    public List<BluetoothDevice> mNewDevicesArrayAdapter = new ArrayList<BluetoothDevice>();

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            String temp;
            ActionMap tempmap = new ActionMap();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                startFetch(device);
                String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                if(name!=null)
                    if(name.contains(regex)){
                        temp = lastmessages.put(device.getAddress(),name);
                        if(temp==null){
                            if (name.charAt(regex.length()) == 'f') {
                                saveEntry(name.substring(regex.length()));
                                recordeddevices++;
                            }
                        }
                        else if(!temp.equals(name)) {
                            if (name.charAt(regex.length()) == 'f') {
                                saveEntry(name.substring(regex.length()));
                                recordeddevices++;
                            }
                        }
                    }
                Log.d("mReceiver","ACTION_FOUND:"+device.getAddress()+" :"+device.getName());
                // If it's already paired, skip it, because it's been listed already

                if(device.getName()==null)
                { //when name is null, skip
                    //But if you want to make lists asap, comment out this block.
                }
                else
                {
                    if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                        //mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                        boolean listed = false;
                        for (BluetoothDevice bd:mNewDevicesArrayAdapter) {
                            String address = bd.getAddress();
                            if (device.getAddress().equals(address)) {
                                Log.d("mReceiver", "ACTION_FOUND: replace the item of lists");
                                mNewDevicesArrayAdapter.remove(bd);
                                mNewDevicesArrayAdapter.add(device);
                                listed = true;
                                break;
                            }
                        }
                        if(listed==false)
                        {//if it is new device( not in lists), add it.
                            mNewDevicesArrayAdapter.add(device);
                        }
                    }
                }

            }else if(BluetoothDevice.ACTION_NAME_CHANGED.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                startFetch(device);
                String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                if(name!=null)
                    if(name.contains(regex)){
                        temp = lastmessages.put(device.getAddress(),name);
                        /**TODO check this out after finishing bluetooth output system*/
                        if(temp==null){
                            if (name.charAt(regex.length()) == 'f') {
                                saveEntry(name.substring(regex.length()));
                                recordeddevices++;
                            }

                        }
                        else if(!temp.equals(name)){
                            if (name.charAt(regex.length()) == 'f') {
                                saveEntry(name.substring(regex.length()));
                                recordeddevices++;
                            }
                        }
                    }
                Log.d("mReceiver", "NAME_CHANGED:" + device.getAddress() + " :" + device.getName());
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    boolean listed = false;
                    for (BluetoothDevice bd:mNewDevicesArrayAdapter) {
                        String address = bd.getAddress();
                        if (device.getAddress().equals(address)) {
                            Log.d("mReceiver", "NAME_CHANGED: replace the item of lists");
                            mNewDevicesArrayAdapter.remove(bd);
                            mNewDevicesArrayAdapter.add(device);
                            listed = true;
                            break;
                        }
                    }
                    if(listed==false)
                    {//if it is new device( not in lists), add it.
                        mNewDevicesArrayAdapter.add(device);
                    }
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);
                if (mNewDevicesArrayAdapter.size() == 0) {
                    mNewDevicesArrayAdapter.add(null);
                }
            }
        }
    };

    public void updatePending(){
        //TODO Place Pending Logic over here
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(mBroadcastReceiver2);
        bluetoothAdapter.cancelDiscovery();
    }

    public static void startFetch( BluetoothDevice device ) {
        // Need to use reflection prior to API 15
        Class cl = null;
        try {
            cl = Class.forName("android.bluetooth.BluetoothDevice");
        } catch( ClassNotFoundException exc ) {
            Log.e("hiya", "android.bluetooth.BluetoothDevice not found." );
        }
        if (null != cl) {
            Class[] param = {};
            Method method = null;
            try {
                method = cl.getMethod("fetchUuidsWithSdp", param);
            } catch( NoSuchMethodException exc ) {
                Log.e("hiya", "fetchUuidsWithSdp not found." );
            }
            if (null != method) {
                Object[] args = {};
                try {
                    method.invoke(device, args);
                } catch (Exception exc) {
                    Log.e("hiya", "Failed to invoke fetchUuidsWithSdp method." );
                }
            }
        }
    }

    /******************************************************************************************************************/

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //TODO do this method done, updates to current match based on matches db
    public void getCurrentMatch(boolean useless){

    }

    public void saveEntry(String entrymessage){
        ActionMap a = ActionMapUtils.parseActionMap(entrymessage);
        a.save();
    }

    @Override
    public void OnBluetoothRead(String message) {

    }
}
