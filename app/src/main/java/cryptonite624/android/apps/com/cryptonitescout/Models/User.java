package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class User extends SugarRecord {
    String username;
    int loggedin;
    String type;
    String email;
    int currency;

    public User(){

    }
}
