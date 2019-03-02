package cryptonite624.android.apps.com.cryptonitescout.Utils;

import android.content.Context;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;

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

        File file = new File(c.getExternalFilesDir(null), "schedule.csv");
        PrintWriter printWriter = new PrintWriter(file);

        Collection<String[]> data;
        List<Schedule> teams = daoSession.getScheduleDao().loadAll();

        CSVPrinter csvPrinter = new CSVPrinter(printWriter, CSVFormat.DEFAULT);

        for(Schedule r:teams){
            csvPrinter.printRecord(scheduletoString(r));
        }
    }

    public void loadRankingstoFile() throws IOException {
        File file = new File(c.getExternalFilesDir(null), "rankings.csv");
        PrintWriter printWriter = new PrintWriter(file);

        Collection<String[]> data;
        List<RankingData> teams = daoSession.getRankingDataDao().loadAll();

        CSVPrinter csvPrinter = new CSVPrinter(printWriter, CSVFormat.DEFAULT);

        for(RankingData r:teams){
            csvPrinter.printRecord(rankingtoString(r));
        }

    }

    public String[] scheduletoString(Schedule s){
        String[] ans = {s.getMatchnum()+"",s.getB1(),s.getB2(),s.getB3(),s.getR1(),s.getR2(),s.getR3()};
        return ans;
    }

    public String [] rankingtoString(RankingData data){
        String [] datas = {data.getTotalwins()+"",data.getTeamnum()+"",data.getMatchesplayed()+"",data.getTotalcargo()+"",
                data.getTotalhatches()+"",data.getClimbone()+"",data.getClimbtwo()+"",data.getClimbthree()+"",data.getClimbfailed()+"",
                data.getTeamkey(),data.getId()+""};
        return datas;
    }

}
