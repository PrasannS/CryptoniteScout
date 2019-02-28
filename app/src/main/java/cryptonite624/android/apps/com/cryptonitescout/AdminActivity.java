package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cryptonite624.android.apps.com.cryptonitescout.Models.User;

public class AdminActivity extends AppCompatActivity implements BluetoothHandler.BluetoothListener{

    public String[][] table;
    int row = 0;
    User user;

    public BluetoothHandler bluetoothHandler;
    public String[] usertoString(User user){
        String[] userArray = {user.getLoggedin()+"",user.getCurrency()+"",user.getType(),user.getPassword(),
                user.getEmail(),user.getUserLastname(),user.getUserFirstname()};
        return userArray;
    }

    public String[][] getArrFromUsers() {
        row+=1;
        int track =0;
        String[][] arr = new String[row][7];
        //load previous
        for(int i=0;i<table.length;i++)
        {
            for(int j=0;j<table[i].length;j++)
            {
                arr[i][j] = table[i][j];
            }
        }
        //loads in next row
        String[] info = usertoString(user);
        for(int j=0;j<7;j++) {
            arr[row][j]= info[track];
            track++;
        }
        table = arr;
        return table;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        bluetoothHandler = new BluetoothHandler(getApplication(),this);
        bluetoothHandler.startlooking();
    }

    @Override
    public void OnBluetoothRead(String message) {
        Toast.makeText(AdminActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void makediscoverable() {

    }
}
