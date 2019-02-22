package cryptonite624.android.apps.com.cryptonitescout;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.drm.DrmStore;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.orm.SugarRecord;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.Config;
import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;
import cryptonite624.android.apps.com.cryptonitescout.Utils.CommentUtils;

public class BluetoothHandler {

    BluetoothListener bluetoothListener = new BluetoothListener() {
        @Override
        public void OnBluetoothRead(String message) {

        }

        @Override
        public void start(Intent intent) {

        }
    };

    public Map<String,String> lastmessages  = new HashMap<>();
    public static String regex = "0624";
    public Timer timer = new Timer();
    public Context curcontext;
    public Config configuration;
    public Map<Character,Boolean> updated;

    private BluetoothAdapter bluetoothAdapter = null;

    public BluetoothHandler(Context context){
        if(Config.listAll(Config.class).size()==0)
            configuration = new Config();
        else
            configuration = Config.findById(Config.class,Long.valueOf(1));
        configuration.save();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        startDiscovery();
        bluetoothAdapter.setName("Cryptonite");
        curcontext = context;
        openreceiver();
        updated.put('m',true);
        updated.put('p',true);
        updated.put('f',true);
        updated.put('c',true);
        updated.put('n',true);
    }

    public void openreceiver(){
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_NAME_CHANGED);
        curcontext.registerReceiver(mReceiver, filter);
    }

    private void ensureDiscoverable() {
        if (bluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(
                    BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
            bluetoothListener.start(discoverableIntent);
        }
    }

    public void handleMessage(String s){
        char i = s.charAt(0);
        switch (i){
            case 'm':
                ActionMapUtils.parseActionMap(s.substring(1)).save();
                bluetoothListener.OnBluetoothRead("datasaved");
                break;
            case 'p':
                ActionMapUtils.parseActionMap(s.substring(1)).save();
                bluetoothListener.OnBluetoothRead("pitnotesaved");
                break;
            case 'f':
                CommentUtils.parseComment(s.substring(1)).save();
                bluetoothListener.OnBluetoothRead("commentsaved");
                break;
            case 'c':
                bluetoothListener.OnBluetoothRead("chatter"+s.substring(1));
                break;
            case 'n':
                bluetoothListener.OnBluetoothRead("currentnum");
                if(Integer.parseInt(s.substring(1))> configuration.getCurrentmatch()){
                    updatePending('n');
                    if(Integer.parseInt(s.substring(1))+1==configuration.getCurrentmatch()){
                        sendMessage('w',s.substring(1));
                    }
                }
                else if(Integer.parseInt(s.substring(1))< configuration.getCurrentmatch()){
                    sendMessage('n', Schedule.findById(Schedule.class,Long.valueOf(Integer.parseInt(s.substring(1))+1))+"");
                }
                break;
            /*case 'w':
                bluetoothListener.OnBluetoothRead("requestmade");
                String temp = "";
                List<ActionMap> maplist = ActionMap.find(ActionMap.class,"Teamnum = ?",Integer.parseInt(s.substring(1))+"");
                for(ActionMap a :maplist){
                    temp+=a.toString()+',';
                }
                sendMessage('u',temp);
                break;
            case 'u':
                if()*/
            default:
                break;
        }
    }

    public interface BluetoothListener{
        public void OnBluetoothRead(String message);
        //TODO get this setup too
        public void start(Intent intent);
    }

    public void startlooking(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Your database code here
                startDiscovery();
                ensureDiscoverable();
            }
        }, 3*1000, 3*1000);
    }

    public void stoplooking(){
        timer.cancel();
    }


    public void sendMessage(char type, String message) {
        final String sNewName = regex+type+message;
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
                            handleMessage(name.substring(regex.length()));
                        }
                        else if(!temp.equals(name)) {
                            handleMessage(name.substring(regex.length()));
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
                            handleMessage(name.substring(regex.length()));

                        }
                        else if(!temp.equals(name)){
                            handleMessage(name.substring(regex.length()));
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
            }
        }
    };

    public void updatePending(char type){
        //TODO Place Pending Logic over here
        sendMessage(type,configuration.getCurrentmatch()+"");
        updated.put(type,false);
    }

    public void endstuff(){
        //TODO set up stuff for this
        bluetoothListener.OnBluetoothRead("endthings");
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


}
