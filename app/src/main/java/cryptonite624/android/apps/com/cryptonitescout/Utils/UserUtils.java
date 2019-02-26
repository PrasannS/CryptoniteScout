package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.util.StringTokenizer;

import cryptonite624.android.apps.com.cryptonitescout.Models.User;

public class UserUtils {


    public static String toString(User data){
        String userData = data.getUserFirstname()+""+data.getUserLastname()+""+data.getEmail()+""+
                data.getPassword()+""+data.getType()+""+data.getCurrency()+""+data.getLoggedin();
        return userData;
    }

    public static User parseUser(String s){
        User user = new User();
        StringTokenizer st = new StringTokenizer(s," ");
        user.setUserFirstname(st.nextToken());
        user.setUserLastname(st.nextToken());
        user.setLoggedin(Boolean.parseBoolean(st.nextToken()));
        user.setType(st.nextToken());
        user.setEmail(st.nextToken());
        user.setPassword(st.nextToken());
        user.setCurrency(Integer.parseInt(st.nextToken()));

        return user;
    }
}
