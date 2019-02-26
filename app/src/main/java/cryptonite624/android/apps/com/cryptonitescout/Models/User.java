package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class User extends SugarRecord {
    String userLastname;
    String userFirstname;
    boolean loggedin;
    String type;
    String email;
    String password;
    int currency;

    public User(String userLastname, String userFirstname, boolean loggedin, String type, String email, String password, int currency){

    }

    public User(){

    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userLastname = userFirstname;
    }

    public boolean getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
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
