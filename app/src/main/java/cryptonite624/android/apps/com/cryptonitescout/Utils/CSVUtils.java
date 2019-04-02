package cryptonite624.android.apps.com.cryptonitescout.Utils;

import android.content.Context;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.greenrobot.greendao.query.WhereCondition;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cryptonite624.android.apps.com.cryptonitescout.CRyptoniteApplication;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMap;
import cryptonite624.android.apps.com.cryptonitescout.Models.ActionMapDao;
import cryptonite624.android.apps.com.cryptonitescout.Models.Comment;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;
import cryptonite624.android.apps.com.cryptonitescout.SettingsActivity;

import static cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils.tournamentAverageCargo;
import static cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils.tournamentAverageHatch;
import static cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils.tournamentHighestClimbLv;
import static cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils.tournamentTotalCargos;
import static cryptonite624.android.apps.com.cryptonitescout.Utils.ActionMapUtils.tournamentTotalHatches;

public class CSVUtils {

    public DaoSession daoSession;
    public Context c;

    //must pass in getApplication
    public CSVUtils(Context app,Context context){
        daoSession = ((CRyptoniteApplication)app).getDaoSession();
        c = context;
    }

    public void loadSchedule() throws IOException{
        /*
        String filename = "myfile";
        String fileContents = "Hello world!";
        FileOutputStream outputStream;

        try {
            //outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream = new FileOutputStream(filename);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        File file = new File(c.getExternalFilesDir(null)+"", "schedule.csv");
        FileWriter fileWriter = new FileWriter(file);

        Collection<String[]> data;
        List<Schedule> teams = daoSession.getScheduleDao().loadAll();

        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

        for(Schedule r:teams){
            csvPrinter.printRecord(scheduletoString(r));
        }
        fileWriter.flush();
        fileWriter.close();
        csvPrinter.close();
    }

    public String[] maptotString(ActionMap map){
        List<ActionMap> m  = new ArrayList<>();
        m.add(map);
        String[] ans = {map.getTeamnum()+"",map.getMatchnum()+"",map.getPos(),map.getEndclimb()+"",ActionMapUtils.tournamentTotalCargos(m)+"",ActionMapUtils.tournamentTotalHatches(m)+""};
        return ans;
    }

    public void loadRankingstoFile() throws IOException {
        File file = new File(c.getExternalFilesDir(null)+"", "rankings.csv");
        FileWriter fileWriter = new FileWriter(file);
        List<ActionMap>maps = daoSession.getActionMapDao().loadAll();

        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

        for(ActionMap r:maps){
            csvPrinter.printRecord(maptotString(r));
        }
        fileWriter.flush();
        fileWriter.close();
        csvPrinter.close();

    }

    public void loadCommentstoFile() throws IOException {
        File file = new File(c.getExternalFilesDir(null)+"", "comments.csv");
        FileWriter fileWriter = new FileWriter(file);

        List<Comment> teams = daoSession.getCommentDao().loadAll();

        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

        for(Comment r:teams){
            csvPrinter.printRecord(commenttoString(r));
        }
        fileWriter.flush();
        fileWriter.close();
        csvPrinter.close();

    }

    public String[] commenttoString(Comment s){
        String[] ans = {s.getMatchnum()+"",s.getTeamnum()+"",s.getBroken()+"",s.getWhybroken()+"",s.getDefense()+"",s.getCargoefficiency()+"",s.getHatchefficiency()+"",""+s.getComment()};
        return ans;
    }
    

    public String[] scheduletoString(Schedule s){
        String[] ans = {s.getMatchnum()+"",s.getB1()+"",s.getB2()+"",s.getB3()+"",s.getR1()+"",s.getR2()+"",s.getR3()};
        return ans;
    }

    public String [] rankingtoString(RankingData data){
        String [] datas = {data.getTotalwins()+"",data.getTeamnum()+"",data.getMatchesplayed()+"",data.getTotalcargo()+"",
                data.getTotalhatches()+"",data.getClimbone()+"",data.getClimbtwo()+"",data.getClimbthree()+"",data.getClimbfailed()+"",
                data.getTeamkey()+"",data.getId()+""};
        return datas;
    }

    public Double[] generateDatas(List<ActionMap>a){
        Double [] row  = new Double[5];
        row[0] = tournamentTotalHatches(a)*1.0;
        row[1] = tournamentTotalCargos(a)*1.0;
        row[2] = tournamentAverageHatch(a);
        row[3] = tournamentAverageCargo(a);
        row[4] = tournamentHighestClimbLv(a)*1.0;
        return row;
    }

    public String[][]generaterankings(){
        List<ActionMap> teamnums = daoSession.getActionMapDao().queryBuilder().where(
                new WhereCondition.StringCondition("SELECT DISTINCT "+ ActionMapDao.Properties.Teamnum+" FROM  "+ActionMapDao.TABLENAME)).list();
        Double[][] ans = new Double[teamnums.size()][5];
        int cur = 0;
        for(ActionMap a: teamnums){
            ans[cur]=generateDatas(daoSession.getActionMapDao().queryBuilder().where(ActionMapDao.Properties.Teamnum.eq(a.getTeamnum())).list());
        }
        return generateStringrs(ans);
    }

    public String[][]generateStringrs(Double[][] d){
        String[][]arr1=new String[d.length][d[0].length];
        for(int i = 0;i<d.length;i++){
            for(int j  =0 ;j<d[0].length;j++){
                arr1[i][j] = d[i][j]+"";
            }
        }
        return arr1;

    }

}
