package cryptonite624.android.apps.com.cryptonitescout.Models;

public class Match {
    public int [] teamNums = new int[6];
    public int matchNum;
    public ActionMap [] maps = new ActionMap[6];
    public String[] comments = new String[6];

    public Match(){
    }

    public String toString() {
        String temp  = "";
        for(int i = 0;i<6;i++){
            temp+=maps[i].toString()+"!@#"+"\n";
        }
        return temp;
    }
    public String apppendComments(){
        String temp  = "";
        for(int i = 0;i<6;i++){
            temp+=comments[i]+"!@#"+"\n";
        }
        return temp;
    }

    public String getTeamsString(){
        String temp = "";
        for(int i:teamNums){
            temp+=i+"&";
        }
        return temp;
    }




}
