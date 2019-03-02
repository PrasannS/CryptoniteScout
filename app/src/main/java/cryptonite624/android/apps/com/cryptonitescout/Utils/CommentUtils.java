package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.util.StringTokenizer;

import cryptonite624.android.apps.com.cryptonitescout.Models.Comment;

public class CommentUtils {

    public static Comment parseComment(String s){
        Comment c = new Comment();
        StringTokenizer st = new StringTokenizer(s, ";~");
        c.setComment( st.nextToken());
        c.setTeamnum( Integer.parseInt(st.nextToken()));
        c.setPos( Integer.parseInt(st.nextToken()));
        c.setHatchefficiency( Integer.parseInt(st.nextToken()));
        c.setCargoefficiency( Integer.parseInt(st.nextToken()));
        c.setDefense( Integer.parseInt(st.nextToken()));
        c.setExcessivefouls(Boolean.parseBoolean(st.nextToken()));
        c.setBroken(Boolean.parseBoolean(st.nextToken()));
        c.setWhybroken( st.nextToken());
        c.setMatchnum(Integer.parseInt(st.nextToken()));

        return c;
    }

    public static String toString(Comment c){
        String temp = "";
        temp += c.getComment() + ";~" + c.getTeamnum() + ";~" + c.getPos() + ";~" + c.getHatchefficiency() + ";~" + c.getCargoefficiency() + ";~" + c.getDefense() + ";~" + c.isExcessivefouls() + ";~" + c.isBroken() + ";~" + c.getWhybroken() + ";~"+c.getMatchnum()+ ";~";
        return temp;
    }
}
