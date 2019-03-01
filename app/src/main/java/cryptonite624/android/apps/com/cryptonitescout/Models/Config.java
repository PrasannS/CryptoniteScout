package cryptonite624.android.apps.com.cryptonitescout.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "config")
public class Config {

    @Id(autoincrement = true)
    Long id;

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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTabletnumber() {
        return this.tabletnumber;
    }

    public void setTabletnumber(int tabletnumber) {
        this.tabletnumber = tabletnumber;
    }

    public Config(){

    }

    @Generated(hash = 26492953)
    public Config(Long id, String currentuser, int currentmatch, String eventkey,
            int tabletnumber) {
        this.id = id;
        this.currentuser = currentuser;
        this.currentmatch = currentmatch;
        this.eventkey = eventkey;
        this.tabletnumber = tabletnumber;
    }

    //email
    @Property(nameInDb = "currentuser")
    String currentuser;
    @Property(nameInDb = "currentmatch")
    int currentmatch;
    @Property(nameInDb = "eventkey")
    String eventkey;
    @Property(nameInDb = "tabletnumber")
    int tabletnumber;

}
