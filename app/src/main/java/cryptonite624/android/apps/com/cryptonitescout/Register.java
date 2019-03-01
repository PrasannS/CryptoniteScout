package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cpjd.models.matches.MatchAlliance;

import cryptonite624.android.apps.com.cryptonitescout.Models.Config;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.User;
import cryptonite624.android.apps.com.cryptonitescout.Utils.UserUtils;

public class Register extends AppCompatActivity implements BluetoothHandler.BluetoothListener{

    private Button toLoging;
    private AutoCompleteTextView fn;
    private AutoCompleteTextView ln;
    private EditText tn;
    private EditText ps;
    private AutoCompleteTextView em;
    private EditText position;
    BluetoothHandler bluetoothHandler;
    public DaoSession daoSession;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tn = findViewById(R.id.tabletnum);
        em = findViewById(R.id.register_email);
        fn = findViewById(R.id.last_name_register);
        ln = findViewById(R.id.first_name_register);
        ps = findViewById(R.id.register_password);
        position = findViewById(R.id.register_color);
        bluetoothHandler = new BluetoothHandler(getApplication(),this);
        daoSession = ((CRyptoniteApplication)getApplication()).getDaoSession();


        toLoging = findViewById(R.id.toLogin_register);
        toLoging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.setUserFirstname(fn.getText().toString());
                u.setUserLastname(ln.getText().toString());
                u.setEmail(em.getText().toString());
                u.setPassword(ps.getText().toString());
                u.setType(position.getText().toString());
                daoSession.getUserDao().save(u);
                try {
                    bluetoothHandler.sendMessage('u', UserUtils.toString(u));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(Register.this, LoginActivity.class));

                Config config = daoSession.getConfigDao().loadAll().get(0);
                config.setTabletnumber(Integer.parseInt(tn.getText().toString()));
                daoSession.getConfigDao().update(config);
            }
        });
    }

    @Override
    public void OnBluetoothRead(String message) {
        Toast.makeText(Register.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void makediscoverable() {

    }
}
