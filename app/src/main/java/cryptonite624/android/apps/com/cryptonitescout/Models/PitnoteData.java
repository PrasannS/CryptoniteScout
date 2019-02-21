package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.orm.SugarRecord;

public class PitnoteData extends SugarRecord<PitnoteData> {

    public int teamnum;
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

    public PitnoteData(){

    }

    public PitnoteData(int num, String comm, boolean onSite, boolean lvTwoStart, boolean crossBase,
                       boolean shift, int numBar, int numCharge, int CIMS, int minCIMS, double Demension,
                       String wheel, String layout, String leve, String intake, String languages ){
        num = teamnum;
        comm = Comment;
        onSite = ProgrammerOnSite;
        lvTwoStart = LevelTwoStart;
        crossBase = CrossBase;
        shift = shifter;
        numBar = numBatteries;
        numCharge = numChargers;
        CIMS = numCIMS;
        minCIMS = numMiniCIMS;
        Demension = robotDimension;
        wheel = wheels;
        layout = layouts;
        leve = Levels;
        intake = Intake;
        languages = language;
    }

}