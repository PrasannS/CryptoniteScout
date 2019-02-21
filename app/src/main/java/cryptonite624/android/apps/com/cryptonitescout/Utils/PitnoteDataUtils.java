package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.util.StringTokenizer;

import cryptonite624.android.apps.com.cryptonitescout.Models.PitnoteData;

public class PitnoteDataUtils {

    int teamnum;
    String Comment;
    boolean ProgrammerOnSite;
    boolean LevelTwoStart;
    boolean CrossBase;
    boolean shifter;
    int numBatteries;
    int numChargers;
    int numCIMS;
    int numMiniCIMS;
    double robotDimension;
    String wheels;
    String layouts;
    String Levels;
    String Intake;
    String language;

    public static String toSTring(PitnoteData data){
        String temp = data.getTeamnum()+" "+data.getComment()+" "+data.isProgrammerOnSite()+" "+data.isLevelTwoStart()+" "+data.isCrossBase()+" "+data.isShifter()+
                " "+data.getNumBatteries()+" "+data.getNumChargers()+" "+data.getNumCIMS()+" "+data.getNumMiniCIMS()+" "+data.getRobotDimension()+" "+data.getWheels()+
                " "+data.getLayouts()+" "+data.getLevels()+" "+data.getIntake()+" "+data.getLanguage();
        return temp;
    }

    public static PitnoteData parsePitnoteData(String temp){
        StringTokenizer st = new StringTokenizer(temp," ");
        PitnoteData pitnote = new PitnoteData();
        pitnote.setTeamnum(Integer.parseInt(st.nextToken()));
        pitnote.setComment(st.nextToken());
        pitnote.setProgrammerOnSite(Boolean.parseBoolean(st.nextToken()));
        pitnote.setLevelTwoStart(Boolean.parseBoolean(st.nextToken()));
        pitnote.setCrossBase(Boolean.parseBoolean(st.nextToken()));
        pitnote.setShifter(Boolean.parseBoolean(st.nextToken()));
        pitnote.setNumBatteries(Integer.parseInt(st.nextToken()));
        pitnote.setNumChargers(Integer.parseInt(st.nextToken()));
        pitnote.setNumCIMS(Integer.parseInt(st.nextToken()));
        pitnote.setNumMiniCIMS(Integer.parseInt(st.nextToken()));
        pitnote.setRobotDimension(Double.parseDouble(st.nextToken()));
        pitnote.setWheels(st.nextToken());
        pitnote.setLayouts(st.nextToken());
        pitnote.setLevels( st.nextToken());
        pitnote.setIntake(st.nextToken());
        pitnote.setLanguage(st.nextToken());

        return pitnote;
    }

}
