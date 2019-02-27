package cryptonite624.android.apps.com.cryptonitescout.Models;



import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "rankingdata")
public class RankingData {

    public RankingData(){

    }

    @Generated(hash = 22484713)
    public RankingData(Long id, int rankpoint, int totalwins, int teamnum, int matchesplayed, double totalcargo, double totalhatches, double climbone, double climbtwo, double climbthree, double climbfailed, String teamkey) {
        this.id = id;
        this.rankpoint = rankpoint;
        this.totalwins = totalwins;
        this.teamnum = teamnum;
        this.matchesplayed = matchesplayed;
        this.totalcargo = totalcargo;
        this.totalhatches = totalhatches;
        this.climbone = climbone;
        this.climbtwo = climbtwo;
        this.climbthree = climbthree;
        this.climbfailed = climbfailed;
        this.teamkey = teamkey;
    }

    @Id(autoincrement = true)
    Long id;

    @Property(nameInDb = "rankpoint")
     int rankpoint           ;
    @Property(nameInDb = "totalwins")
     int totalwins             ;
    @Property(nameInDb = "teamnum")
     int teamnum             ;
    @Property(nameInDb = "matchesplayed")
     int matchesplayed       ;
    @Property(nameInDb = "totalcargo")
     double totalcargo         ;
    @Property(nameInDb = "totalhatches")
     double totalhatches         ;
    @Property(nameInDb = "climbone")
     double climbone         ;
    @Property(nameInDb = "climbtwo")
    double climbtwo       ;
    @Property(nameInDb = "climbthree")
     double climbthree       ;
    @Property(nameInDb = "climbfailed")
    double climbfailed;
    @Property(nameInDb = "teamkey")
    String teamkey;

    /*public RankingData(int rankpoint, int totalwins, int teamnum, int matchesplayed, double totalcargo, double totalhatches, double climbone, double climbtwo, double climbthree, double climbfailed, String teamkey) {
        this.rankpoint = rankpoint;
        this.totalwins = totalwins;
        this.teamnum = teamnum;
        this.matchesplayed = matchesplayed;
        this.totalcargo = totalcargo;
        this.totalhatches = totalhatches;
        this.climbone = climbone;
        this.climbtwo = climbtwo;
        this.climbthree = climbthree;
        this.climbfailed = climbfailed;
        this.teamkey = teamkey;
    }*/

    public int getRankpoint() {

        return rankpoint;
    }

    public void setRankpoint(int rankpoint) {
        this.rankpoint = rankpoint;
    }

    public int getTotalwins() {
        return totalwins;
    }

    public void setTotalwins(int totalwins) {
        this.totalwins = totalwins;
    }

    public int getTeamnum() {
        return teamnum;
    }

    public void setTeamnum(int teamnum) {
        this.teamnum = teamnum;
    }

    public int getMatchesplayed() {
        return matchesplayed;
    }

    public void setMatchesplayed(int matchesplayed) {
        this.matchesplayed = matchesplayed;
    }

    public double getTotalcargo() {
        return totalcargo;
    }

    public void setTotalcargo(double totalcargo) {
        this.totalcargo = totalcargo;
    }

    public double getTotalhatches() {
        return totalhatches;
    }

    public void setTotalhatches(double totalhatches) {
        this.totalhatches = totalhatches;
    }

    public double getClimbone() {
        return climbone;
    }

    public void setClimbone(double climbone) {
        this.climbone = climbone;
    }

    public double getClimbtwo() {
        return climbtwo;
    }

    public void setClimbtwo(double climbtwo) {
        this.climbtwo = climbtwo;
    }

    public double getClimbthree() {
        return climbthree;
    }

    public void setClimbthree(double climbthree) {
        this.climbthree = climbthree;
    }

    public double getClimbfailed() {
        return climbfailed;
    }

    public void setClimbfailed(double climbfailed) {
        this.climbfailed = climbfailed;
    }

    public String getTeamkey() {
        return teamkey;
    }

    public void setTeamkey(String teamkey) {
        this.teamkey = teamkey;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
