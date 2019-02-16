package cryptonite624.android.apps.com.cryptonitescout;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public Map<String,String> lastmessages  = new HashMap<>();

    public static String regex = "0624";
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    private ListView lvMainChat;
    private EditText etMain;
    private ImageView btnSend;
    private BluetoothAdapter bluetoothAdapter = null;
    private ChatArrayAdapter chatArrayAdapter;
    String writeMessage;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        startDiscovery();
        bluetoothAdapter.setName("Cryptonite");

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Your database code here
                startDiscovery();
                ensureDiscoverable();
            }
        }, 3*1000, 3*1000);
        /*getWidgetReferences*/
        lvMainChat = (ListView) findViewById(R.id.lvMainChat);
        etMain = (EditText) findViewById(R.id.etMain);
        btnSend = (ImageView) findViewById(R.id.btnSend);


        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.message);
        lvMainChat.setAdapter(chatArrayAdapter);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                writeMessage = etMain.getText().toString();
                sendMessage(writeMessage);
                ChatMessage m = new ChatMessage(false,writeMessage);
                chatArrayAdapter.add(m);
                etMain.setText("");
            }
        });

        lvMainChat.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        lvMainChat.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                lvMainChat.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

        // Register for broadcasts when a device is discovered.
        //IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        //registerReceiver(mReceiver, filter);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_NAME_CHANGED);
        this.registerReceiver(mReceiver, filter);

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent serverIntent = null;
        switch (item.getItemId()) {
            case R.id.secure_connect_scan:
                serverIntent = new Intent(this, DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
                return true;
            case R.id.insecure_connect_scan:
                serverIntent = new Intent(this, DeviceListActivity.class);
                startActivityForResult(serverIntent,
                        REQUEST_CONNECT_DEVICE_INSECURE);
                return true;
            case R.id.discoverable:
                ensureDiscoverable();
                return true;
        }
        return false;
    }

    private void ensureDiscoverable() {
        if (bluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(
                    BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
            startActivity(discoverableIntent);
        }
    }

    private void sendMessage(String message) {
        final String sNewName = "0624"+message;
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

    @Override
    public void onStart() {
        super.onStart();

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
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
                        ChatMessage message = new ChatMessage(true,name.substring(regex.length()));
                        chatArrayAdapter.add(message);
                    }
                    else if(!temp.equals(name)){
                        ChatMessage message = new ChatMessage(true,name.substring(regex.length()));
                        chatArrayAdapter.add(message);
                    }
                }
        }
    };

    public List<BluetoothDevice> mNewDevicesArrayAdapter = new ArrayList<BluetoothDevice>();

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            String temp;
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                startFetch(device);
                String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                if(name!=null)
                    if(name.contains(regex)){
                        temp = lastmessages.put(device.getAddress(),name);
                        if(temp==null){
                            ChatMessage message = new ChatMessage(true,name.substring(regex.length()));
                            chatArrayAdapter.add(message);
                        }
                        else if(!temp.equals(name)){
                            ChatMessage message = new ChatMessage(true,name.substring(regex.length()));
                            chatArrayAdapter.add(message);
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
                        if(temp==null){
                            ChatMessage message = new ChatMessage(true,name.substring(regex.length()));
                            chatArrayAdapter.add(message);
                        }
                        else if(!temp.equals(name)){
                            ChatMessage message = new ChatMessage(true,name.substring(regex.length()));
                            chatArrayAdapter.add(message);
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
    public synchronized void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(mBroadcastReceiver2);
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
