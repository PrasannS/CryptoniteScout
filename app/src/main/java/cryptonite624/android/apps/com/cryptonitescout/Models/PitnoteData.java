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

    public int getTeamnum() {
        return teamnum;
    }

    public void setTeamnum(int teamnum) {
        this.teamnum = teamnum;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public boolean isProgrammerOnSite() {
        return ProgrammerOnSite;
    }

    public void setProgrammerOnSite(boolean programmerOnSite) {
        ProgrammerOnSite = programmerOnSite;
    }

    public boolean isLevelTwoStart() {
        return LevelTwoStart;
    }

    public void setLevelTwoStart(boolean levelTwoStart) {
        LevelTwoStart = levelTwoStart;
    }

    public boolean isCrossBase() {
        return CrossBase;
    }

    public void setCrossBase(boolean crossBase) {
        CrossBase = crossBase;
    }

    public boolean isShifter() {
        return shifter;
    }

    public void setShifter(boolean shifter) {
        this.shifter = shifter;
    }

    public int getNumBatteries() {
        return numBatteries;
    }

    public void setNumBatteries(int numBatteries) {
        this.numBatteries = numBatteries;
    }

    public int getNumChargers() {
        return numChargers;
    }

    public void setNumChargers(int numChargers) {
        this.numChargers = numChargers;
    }

    public int getNumCIMS() {
        return numCIMS;
    }

    public void setNumCIMS(int numCIMS) {
        this.numCIMS = numCIMS;
    }

    public int getNumMiniCIMS() {
        return numMiniCIMS;
    }

    public void setNumMiniCIMS(int numMiniCIMS) {
        this.numMiniCIMS = numMiniCIMS;
    }

    public double getRobotDimension() {
        return robotDimension;
    }

    public void setRobotDimension(double robotDimension) {
        this.robotDimension = robotDimension;
    }

    public String getWheels() {
        return wheels;
    }

    public void setWheels(String wheels) {
        this.wheels = wheels;
    }

    public String getLayouts() {
        return layouts;
    }

    public void setLayouts(String layouts) {
        this.layouts = layouts;
    }

    public String getLevels() {
        return Levels;
    }

    public void setLevels(String levels) {
        Levels = levels;
    }

    public String getIntake() {
        return Intake;
    }

    public void setIntake(String intake) {
        Intake = intake;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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