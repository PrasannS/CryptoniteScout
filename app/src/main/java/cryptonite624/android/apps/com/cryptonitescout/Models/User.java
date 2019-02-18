package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class User extends SugarRecord<User> {
     String username;
     int status;
     String type;
     String email;
     int currency;

    public User(){

    }
}
