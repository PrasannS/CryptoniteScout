package cryptonite624.android.apps.com.cryptonitescout;


import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.Models.User;
import de.codecrafters.tableview.TableView;

//Dylan

public class AdminActivity {
    public String[][] table;
    int row = 0;
    User user;
    public AdminActivity(User u)
    {
        user = u;
    }
    public String[] usertoString(User user){
        String[] userArray = {user.getLoggedin()+"",user.getCurrency()+"",user.getType(),user.getPassword(),
                user.getEmail(),user.getUserLastname(),user.getUserFirstname()};
        return userArray;
    }

    public String[][] getArrFromUsers() {
        row+=1;
        int track =0;
        String[][] arr = new String[row][7];
        //load previous
        for(int i=0;i<table.length;i++)
        {
            for(int j=0;j<table[i].length;j++)
            {
                arr[i][j] = table[i][j];
            }
        }
        //loads in next row
        String[] info = usertoString(user);
            for(int j=0;j<7;j++) {
                arr[row][j]= info[track];
                track++;
            }
        table = arr;
        return table;
    }

}
