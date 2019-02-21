package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.util.StringTokenizer;

import cryptonite624.android.apps.com.cryptonitescout.Models.Comment;

public class CommentUtils {

    public static Comment parseComment(String s){
        Comment c = new Comment();
        StringTokenizer st = new StringTokenizer(s, ";~");
        c.comment = st.nextToken();
        c.teamnum = Integer.parseInt(st.nextToken());
        c.pos = Integer.parseInt(st.nextToken());
        c.hatchefficiency = Integer.parseInt(st.nextToken());
        c.cargoefficiency = Integer.parseInt(st.nextToken());
        c.defense = Integer.parseInt(st.nextToken());
        c.excessivefouls = Boolean.parseBoolean(st.nextToken());
        c.broken = Boolean.parseBoolean(st.nextToken());
        c.whybroken = st.nextToken();

        return c;
    }

    public static String toString(Comment c){
        String temp = "";
        temp += c.comment + ";~" + c.teamnum + ";~" + c.pos + ";~" + c.hatchefficiency + ";~" + c.cargoefficiency + ";~" + c.defense + ";~" + c.excessivefouls + ";~" + c.broken + ";~" + c.whybroken + ";~";
        return temp;
    }
}
