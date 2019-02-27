package cryptonite624.android.apps.com.cryptonitescout;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DataSetObserver;
import android.location.OnNmeaMessageListener;
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

public class MainActivity extends AppCompatActivity implements BluetoothHandler.BluetoothListener{

    private ListView lvMainChat;
    private EditText etMain;
    private ImageView btnSend;
    private BluetoothAdapter bluetoothAdapter = null;
    private ChatArrayAdapter chatArrayAdapter;
    String writeMessage;
    public BluetoothHandler bluetoothHandler;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothHandler = new BluetoothHandler(getApplication(),this);
        bluetoothHandler.startlooking();

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
                try {
                    bluetoothHandler.sendMessage('c',writeMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        bluetoothHandler.endstuff();
    }

    @Override
    public void OnBluetoothRead(String message) {
        ChatMessage cm = new ChatMessage(true,message);
        chatArrayAdapter.add(cm);
    }

    @Override
    public void start(Intent intent) {

    }
}
