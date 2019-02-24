package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class DrawMap extends SugarRecord{

    String image;
    String name;

    public DrawMap(){
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
