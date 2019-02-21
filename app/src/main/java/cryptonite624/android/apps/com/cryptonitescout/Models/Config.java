package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class Config extends SugarRecord<Config> {
    public boolean isCommentscout() {
        return commentscout;
    }

    public void setCommentscout(boolean commentscout) {
        this.commentscout = commentscout;
    }

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
    boolean commentscout;
    String currentuser;
    int currentmatch;
    String eventkey;

}
