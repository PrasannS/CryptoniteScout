package cryptonite624.android.apps.com.cryptonitescout.Models;

import java.util.StringTokenizer;

public class Match {
    public String [] teamNums = new String[6];
    public int matchNum;
    public ActionMap [] maps = new ActionMap[6];
    public String[] comments = new String[6];

    public Match(){
    }

    public String toString() {
        String temp  = "";
        temp+=matchNum+"!@#";
        temp+=getTeamsString()+"!@#";
        for(int i = 0;i<6;i++){
            temp+=maps[i].toString()+"!@#"+"\n";
        }
        temp+=apppendComments();
        return temp;
    }

    public static Match parseMatch(String s){
        StringTokenizer stringTokenizer = new StringTokenizer("!@#");
        Match m = new Match();
        m.matchNum = Integer.parseInt(stringTokenizer.nextToken());
        m.teamNums = stringTokenizer.nextToken().split("&");
        ActionMap temp = new ActionMap();
        for (int i = 0; i<6;i++){
            temp.parseString(stringTokenizer.nextToken());
            m.maps[i] = temp;
        }
        for (int i = 0; i<6;i++){
            m.comments[i] = stringTokenizer.nextToken();
        }
        return m;
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
        for(String i:teamNums){
            temp+=i+"&";
        }
        return temp;
    }

    public void update(Match m){
        if(teamNums.equals(m.teamNums)&&matchNum==m.matchNum)
        for(int i =0;i<6;i++){
            if(maps[i]==null&&m.maps[i]!=null){
                maps[i] = m.maps[i];
            }
            if(comments[i]==""&&m.comments[i]!=""){
                comments[i] = m.comments[i];
            }
        }
    }
}
