package cryptonite624.android.apps.com.cryptonitescout.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "drawmap")
public class DrawMap {

    @Id(autoincrement = true)
    Long id;
    @Property(nameInDb = "image")
    String image;
    @Property(nameInDb = "name")
    String name;

    public DrawMap(){
    }

    @Generated(hash = 1574861921)
    public DrawMap(Long id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
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

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
