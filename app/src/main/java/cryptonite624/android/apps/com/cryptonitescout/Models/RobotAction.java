package cryptonite624.android.apps.com.cryptonitescout.Models;
import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

public class RobotAction extends SugarRecord<RobotAction> implements Parcelable{
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    //actionCode 0 = not on switch, 1 = red switch 1, 2 = blue switch1, 3 = blue scale, 4 = red scale, 5 = red switch2, 6 = blue switch, 7 = invalid click
    public int matchStatus;
    public String actionCode;
    public boolean hatch;
    public int habLevel;
    public int time;

    public RobotAction(){

    }

    public RobotAction(String ac, int ms){
        actionCode = ac;
        matchStatus = ms;
    }

    public RobotAction(String str){
        parseString(str);

    }


    protected RobotAction(Parcel in) {
        matchStatus = in.readInt();
        actionCode = in.readString();
        hatch = in.readByte() != 0;
    }

    public static final Creator<RobotAction> CREATOR = new Creator<RobotAction>() {
        @Override
        public RobotAction createFromParcel(Parcel in) {
            return new RobotAction(in);
        }

        @Override
        public RobotAction[] newArray(int size) {
            return new RobotAction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(matchStatus);
        dest.writeString(actionCode);
        dest.writeByte((byte) (hatch ? 1 : 0));
    }

    public void parseString(String s){
        String [] parsed = s.split(" ");
        for(int i = 0; i < parsed.length; i++){
            String [] parsed1 = parsed[i].split(",");
            int counter = 0;

            for(String str: parsed1){
                if(counter == 0){
                    matchStatus = Integer.parseInt(str);
                    counter++;
                }
                else if(counter == 1){
                    actionCode = str;
                    counter++;
                }
                else if(counter == 2){
                    if(Integer.parseInt(str) == 0){
                        hatch = false;
                        counter++;
                    }
                    else if(Integer.parseInt(str) == 1){
                        hatch = true;
                        counter++;
                    }
                }
                else if(counter == 3){
                    time = Integer.parseInt(str);
                }
            }
        }
    }


    public String toString(){
        /*if( hatch)
        return actionCode + 0+""+matchStatus;
        return actionCode+1+""+matchStatus;*/
        return actionCode + " " + hatch + " " + time;

    }

}
