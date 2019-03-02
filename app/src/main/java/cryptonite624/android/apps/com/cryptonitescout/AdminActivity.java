package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Models.User;
import cryptonite624.android.apps.com.cryptonitescout.Utils.UserUtils;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class AdminActivity extends AppCompatActivity implements BluetoothHandler.BluetoothListener{

    public TableView<String[]> tableView;
    public DaoSession daoSession;
    public static String [] userHeaders = {"Logged in","Currency","Type","Password","Email","Name","Tablet#"};

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daoSession = ((CRyptoniteApplication)getApplication()).getDaoSession();
        List<User> users = daoSession.getUserDao().loadAll();
        tableView = (TableView<String[]>) findViewById(R.id.tableView);
        tableView.setColumnCount(6);
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, getArrFromUsers(users)));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, userHeaders));
        tableView.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {

            }
        });

        setContentView(R.layout.activity_admin);

        bluetoothHandler = new BluetoothHandler(getApplication(),this);
        bluetoothHandler.startlooking();
        try {
            bluetoothHandler.sendMessage('u', UserUtils.toString(users.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void OnBluetoothRead(String message) {
        Toast.makeText(AdminActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }


}
