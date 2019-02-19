package cryptonite624.android.apps.com.cryptonitescout.Models;

public class PitnoteData {

    int matchNum;
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

    public PitnoteData(int num, String comm, boolean onSite, boolean lvTwoStart, boolean crossBase,
                       boolean shift, int numBar, int numCharge, int CIMS, int minCIMS, double Demension,
                       String wheel, String layout, String leve, String intake, String languages ){
        num = matchNum;
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