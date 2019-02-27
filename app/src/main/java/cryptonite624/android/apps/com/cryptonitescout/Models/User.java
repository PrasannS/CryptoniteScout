package cryptonite624.android.apps.com.cryptonitescout.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user")

public class User  {

    @Property(nameInDb = "userlastname")
    String userLastname;
    @Property(nameInDb = "userfirstname")
    String userFirstname;
    @Property(nameInDb = "loggedin")
    boolean loggedin;
    @Property(nameInDb = "type")
    String type;
    @Property(nameInDb = "email")
    String email;
    @Property(nameInDb = "password")
    String password;
    @Property(nameInDb = "currency")
    int currency;
    

    public User(){

    }

    @Generated(hash = 240337981)
    public User(String userLastname, String userFirstname, boolean loggedin,
            String type, String email, String password, int currency) {
        this.userLastname = userLastname;
        this.userFirstname = userFirstname;
        this.loggedin = loggedin;
        this.type = type;
        this.email = email;
        this.password = password;
        this.currency = currency;
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

    public boolean isLoggedin() {
        return loggedin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
