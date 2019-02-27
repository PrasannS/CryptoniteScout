package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;

import com.cpjd.main.TBA;
import com.cpjd.models.matches.Match;
import com.cpjd.sorting.SortingType;

import cryptonite624.android.apps.com.cryptonitescout.Models.Schedule;


public class ServerLoader {

    ServerLoadListener serverLoadListener;

    public TBA tba;
    public static String event = "2019week0";

    public ServerLoader(ServerLoadListener sl){
        // Set TBA auth token
        serverLoadListener =sl;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
        TBA.setAuthToken("7pMGgXcAgBXg3hN4mchVQo67y6bi0bmWJv7S7YLU2mw8hre3B98frdj49mxlk7QR");

// Create TBA object
         tba = new TBA();
    }

    public void loadFromTBA(){
        new LoadAllMatches().execute();
    }

    private class LoadAllMatches extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... strings) {
            //if(Schedule.listAll(Schedule.class).size() > 0)
            //Schedule.deleteAll(Schedule.class);
            try {
                Match[] matches = tba.getMatches("2019week0");
                TBA.sort(matches, SortingType.DATE);
                int cur = 0;
                for(Match m:matches){
                    addScheduleFromAPI(m,cur);
                    cur++;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

            serverLoadListener.onServerLoad();
        }
    }

    public void addScheduleFromAPI(Match m,int num){
        Schedule schedule = new Schedule();
        schedule.setMatchnum( num);
        String[] red = m.getRed().getTeamKeys();
        String[] blue = m.getBlue().getTeamKeys();
        schedule.setR1(red[0]);
        schedule.setR2(red[1]);
        schedule.setR3(red[2]);
        schedule.setB1(blue[0]);
        schedule.setB2(blue[1]);
        schedule.setB3(blue[2]);
        serverLoadListener.addSchedule(schedule);
    }

    public interface ServerLoadListener{
        void onServerLoad();
        void addSchedule(Schedule s);
    }



}
