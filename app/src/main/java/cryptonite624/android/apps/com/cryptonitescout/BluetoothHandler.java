package cryptonite624.android.apps.com.cryptonitescout;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils;
import cryptonite624.android.apps.com.cryptonitescout.Utils.CommentUtils;

public class BluetoothHandler {

    BluetoothListener bluetoothListener;
    public Map<String,String> lastmessages  = new HashMap<>();
    public static String regex = "0624";

    private BluetoothAdapter bluetoothAdapter = null;

    public BluetoothHandler(){

    }

    public void handleMessage(String s){
        char i = s.charAt(0);
        switch (i){
            case 'm':
                ActionMapUtils.parseActionMap(s.substring(1)).save();
                bluetoothListener.OnBluetoothRead("datasaved");
                break;
            case 'f':
                CommentUtils.parseComment(s.substring(1)).save();
                bluetoothListener.OnBluetoothRead("commentsaved");
                break;
            case 'c':
                bluetoothListener.OnBluetoothRead("chatter"+s.substring(1));
                break;
            default:
                break;
        }
    }

    public interface BluetoothListener{
        public void OnBluetoothRead(String message);
    }


    private void sendMessage(char type, String message) {
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
                            if (name.charAt(regex.length()) == 'm') {
                                handleMessage(name.substring(regex.length()));
                            }
                            else
                        }
                        else if(!temp.equals(name)) {
                            if (name.charAt(regex.length()) == 'm') {
                                ActionMapUtils.parseActionMap(name.substring(regex.length())).save();
                                bluetoothListener.OnBluetoothRead("newdata");
                            }
                            else
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
                            if (name.charAt(regex.length()) == 'm') {
                                saveEntry(name.substring(regex.length()));
                                recordeddevices++;
                            }
                            else

                        }
                        else if(!temp.equals(name)){
                            if (name.charAt(regex.length()) == 'm') {
                                saveEntry(name.substring(regex.length()));
                                recordeddevices++;
                            }
                            else
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


}
