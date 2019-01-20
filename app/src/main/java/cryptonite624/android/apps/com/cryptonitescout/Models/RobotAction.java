package cryptonite624.android.apps.com.cryptonitescout.Models;
import android.os.Parcel;
import android.os.Parcelable;

public class RobotAction implements Parcelable{
    //matchstatus, 0 = pregame, 1 = auton, 2 = teleop, 3 = endgame
    //actionCode 0 = not on switch, 1 = red switch 1, 2 = blue switch1, 3 = blue scale, 4 = red scale, 5 = red switch2, 6 = blue switch, 7 = invalid click
    public int matchStatus;
    public String actionCode;
    public boolean hatch;

    public RobotAction(){

    }

    public RobotAction(String ac, int ms){
        actionCode = ac;
        matchStatus = ms;
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
}
