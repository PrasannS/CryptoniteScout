package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class User extends SugarRecord<User> {
    String username;
    int loggedin;
    String type;
    String email;
    int currency;

    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(int loggedin) {
        this.loggedin = loggedin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
