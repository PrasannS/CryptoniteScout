package cryptonite624.android.apps.com.cryptonitescout.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "config")
public class Config {

    public String getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser;
    }

    public int getCurrentmatch() {
        return currentmatch;
    }

    public void setCurrentmatch(int currentmatch) {
        this.currentmatch = currentmatch;
    }

    public String getEventkey() {
        return eventkey;
    }

    public void setEventkey(String eventkey) {
        this.eventkey = eventkey;
    }

    public Config(){

    }

    @Generated(hash = 93525242)
    public Config(String currentuser, int currentmatch, String eventkey) {
        this.currentuser = currentuser;
        this.currentmatch = currentmatch;
        this.eventkey = eventkey;
    }

    //email
    @Property(nameInDb = "currentuser")
    String currentuser;
    @Property(nameInDb = "currentmatch")
    int currentmatch;
    @Property(nameInDb = "eventkey")
    String eventkey;

}
