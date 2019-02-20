package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.util.StringTokenizer;

import cryptonite624.android.apps.com.cryptonitescout.Models.PitnoteData;

public class PitnoteDataUtils {

    public int matchNum;
    public String Comment;
    public boolean ProgrammerOnSite;
    public boolean LevelTwoStart;
    public boolean CrossBase;
    public boolean shifter;
    public int numBatteries;
    public int numChargers;
    public int numCIMS;
    public int numMiniCIMS;
    public double robotDimension;
    public String wheels;
    public String layouts;
    public String Levels;
    public String Intake;
    public String language;

    public static String toSTring(PitnoteData data){
        String temp = data.teamnum+" "+data.Comment+" "+data.ProgrammerOnSite+" "+data.LevelTwoStart+" "+data.CrossBase+" "+data.shifter+
                " "+data.numBatteries+" "+data.numChargers+" "+data.numCIMS+" "+data.numMiniCIMS+" "+data.robotDimension+" "+data.wheels+
                " "+data.layouts+" "+data.Levels+" "+data.Intake+" "+data.language;
        return temp;
    }

    public static PitnoteData parsePitnoteData(String temp){
        StringTokenizer st = new StringTokenizer(temp," ");
        PitnoteData pitnote = new PitnoteData();
        pitnote.teamnum = Integer.parseInt(st.nextToken());
        pitnote.Comment = st.nextToken();
        pitnote.ProgrammerOnSite = Boolean.parseBoolean(st.nextToken());
        pitnote.LevelTwoStart = Boolean.parseBoolean(st.nextToken());
        pitnote.CrossBase = Boolean.parseBoolean(st.nextToken());
        pitnote.shifter = Boolean.parseBoolean(st.nextToken());
        pitnote.numBatteries = Integer.parseInt(st.nextToken());
        pitnote.numChargers = Integer.parseInt(st.nextToken());
        pitnote.numCIMS = Integer.parseInt(st.nextToken());
        pitnote.numMiniCIMS = Integer.parseInt(st.nextToken());
        pitnote.robotDimension = Double.parseDouble(st.nextToken());
        pitnote.wheels = st.nextToken();
        pitnote.layouts = st.nextToken();
        pitnote.Levels = st.nextToken();
        pitnote.Intake = st.nextToken();
        pitnote.language = st.nextToken();

        return pitnote;
    }

}
