package cryptonite624.android.apps.com.cryptonitescout;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Models.User;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class AdminActivity extends AppCompatActivity implements BluetoothHandler.BluetoothListener{

    public TableView<String[]> tableView;
    public DaoSession daoSession;
    public static String [] userHeaders = {"Logged in","Currency","Type","Password","Email","Last name","First name"};

    public String[][] table;
    int row = 0;
    User user;

    public BluetoothHandler bluetoothHandler;
    public String[] usertoString(User user){
        String[] userArray = {user.getLoggedin()+"",user.getCurrency()+"",user.getType(),
                user.getEmail(),user.getUserLastname(),user.getUserFirstname()};
        return userArray;
    }

    public String[][] getArrFromUsers(List<User> users) {
        String[][] arr = new String[users.size()][6];
        int curr = 0;
        for(User r : users){
            arr[curr] = usertoString(r);
        }
        return arr;
    }

    public static String password = "scoutsrock";

    public boolean checkpassword(String attempt){
        return attempt.equals(password);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_admin);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Verification Code");
        alert.setMessage("Enter in code");
// Create TextView
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(checkpassword(input.getText().toString())){
                    //TODO handle logged in
                }


                // Do something with value!
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();

        bluetoothHandler = new BluetoothHandler(getApplication(),this);
        bluetoothHandler.startlooking();

        daoSession = ((CRyptoniteApplication)getApplication()).getDaoSession();
        List<User> users = daoSession.getUserDao().loadAll();
        tableView = (TableView<String[]>) findViewById(R.id.tableView);
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, getArrFromUsers(users)));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, userHeaders));
        tableView.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
            }
        });
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
