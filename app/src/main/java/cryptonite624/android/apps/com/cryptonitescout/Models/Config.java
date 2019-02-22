package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class Config extends SugarRecord{

    public User getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(User currentuser) {
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
    User currentuser;
    int currentmatch;
    String eventkey;

}
