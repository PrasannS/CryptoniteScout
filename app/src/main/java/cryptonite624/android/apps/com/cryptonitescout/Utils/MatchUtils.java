package cryptonite624.android.apps.com.cryptonitescout.Utils;

public class MatchUtils {
    public String apppendComments(String [] comments){
        String temp  = "";
        for(int i = 0;i<6;i++){
            temp+=comments[i]+"!@#"+"\n";
        }
        return temp;
    }

    public String getTeamsString(int teamNums[]){
        String temp = "";
        for(int i:teamNums){
            temp+=i+"&";
        }
        return temp;
    }


}
