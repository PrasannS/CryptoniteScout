package cryptonite624.android.apps.com.cryptonitescout.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicMatchEntry {
    @SerializedName("teamnum")
    @Expose
    private Integer teamnum;
    @SerializedName("scalecubes")
    @Expose
    private Integer scalecubes;
    @SerializedName("switchcubes")
    @Expose
    private Integer switchcubes;
    public Integer getTeamnum() {
        return teamnum;
    }
    public void setTeamnum(Integer tn) {
        this.teamnum = tn;
    }

    public Integer getScalecubes() {
        return scalecubes;
    }
    public void setScalecubes(Integer scalecubes) {
        this.scalecubes = scalecubes;
    }

    public void setSwitchcubes(Integer dt) {
        this.switchcubes = dt;
    }
    public Integer getSwitchcubes() {
        return switchcubes;
    }

}
