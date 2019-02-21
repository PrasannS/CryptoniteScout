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

    ServerLoadListener serverLoadListener = new ServerLoadListener() {
        @Override
        public void onServerLoad() {
        }
    };

    public TBA tba;
    public static String event = "2019week0";

    public ServerLoader(){
        // Set TBA auth token
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
            Match[] matches = tba.getMatches("2019week0");
            TBA.sort(matches, SortingType.DATE);
            int cur = 0;
            for(Match m:matches){
                Schedule.addScheduleFromAPI(m,cur);
                cur++;
            }
            serverLoadListener.onServerLoad();
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
        }
    }

    public interface ServerLoadListener{
        void onServerLoad();
    }



}
