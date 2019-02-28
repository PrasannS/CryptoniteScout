package cryptonite624.android.apps.com.cryptonitescout.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "pitnote")
public class PitnoteData  {

    @Id(autoincrement = true)
    Long id;
    @Property(nameInDb = "teamnum")
    int teamnum;
    @Property(nameInDb = "comment")
    String Comment;
    @Property(nameInDb = "programmeronsite")
    boolean ProgrammerOnSite;
    @Property(nameInDb = "leveltwostart")
    boolean LevelTwoStart;
    @Property(nameInDb = "crossbase")
    boolean CrossBase;
    @Property(nameInDb = "shifter")
    boolean shifter;
    @Property(nameInDb = "numbatteries")
    int numBatteries;
    @Property(nameInDb = "numchargers")
    int numChargers;
    @Property(nameInDb = "numcims")
    int numCIMS;
    @Property(nameInDb = "numminicims")
    int numMiniCIMS;
    @Property(nameInDb = "robotdimensions")
    double robotDimension;
    @Property(nameInDb = "wheels")
    String wheels;
    @Property(nameInDb = "layouts")
    String layouts;
    @Property(nameInDb = "levels")
    String Levels;
    @Property(nameInDb = "intake")
    String Intake;
    @Property(nameInDb = "language")
    String language;
    @Property(nameInDb = "image")
    String image;


    public PitnoteData(){

    }

    @Generated(hash = 1934040361)
    public PitnoteData(Long id, int teamnum, String Comment, boolean ProgrammerOnSite, boolean LevelTwoStart,
            boolean CrossBase, boolean shifter, int numBatteries, int numChargers, int numCIMS, int numMiniCIMS,
            double robotDimension, String wheels, String layouts, String Levels, String Intake, String language,
            String image) {
        this.id = id;
        this.teamnum = teamnum;
        this.Comment = Comment;
        this.ProgrammerOnSite = ProgrammerOnSite;
        this.LevelTwoStart = LevelTwoStart;
        this.CrossBase = CrossBase;
        this.shifter = shifter;
        this.numBatteries = numBatteries;
        this.numChargers = numChargers;
        this.numCIMS = numCIMS;
        this.numMiniCIMS = numMiniCIMS;
        this.robotDimension = robotDimension;
        this.wheels = wheels;
        this.layouts = layouts;
        this.Levels = Levels;
        this.Intake = Intake;
        this.language = language;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getProgrammerOnSite() {
        return this.ProgrammerOnSite;
    }

    public boolean getLevelTwoStart() {
        return this.LevelTwoStart;
    }

    public boolean getCrossBase() {
        return this.CrossBase;
    }

    public boolean getShifter() {
        return this.shifter;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}