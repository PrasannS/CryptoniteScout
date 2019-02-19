package cryptonite624.android.apps.com.cryptonitescout;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ServerLoader {

    public ServerLoader(){
        uploadthings();
    }

    public static void main(String[] args){
        ServerLoader s= new ServerLoader();
    }

    public String[] matchids = {
            "2019week0_qm1",
            "2019week0_qm2",
            "2019week0_qm3",
            "2019week0_qm4",
            "2019week0_qm5",
            "2019week0_qm6",
            "2019week0_qm7",
            "2019week0_qm8",
            "2019week0_qm9",
            "2019week0_qm10",
            "2019week0_qm11",
            "2019week0_qm12",
            "2019week0_qm13",
    };

    public void uploadthings(){
        for(String turl:matchids) {
            String temp = "https://www.thebluealliance.com/api/v3/match/"+turl;
            new GetUrlContentTask().execute(turl);
        }
    }
    public int num=0;

    private class GetUrlContentTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            String content = "", line;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestProperty("Last-Modified","7pMGgXcAgBXg3hN4mchVQo67y6bi0bmWJv7S7YLU2mw8hre3B98frdj49mxlk7QR");
                connection.connect();
                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = rd.readLine()) != null) {
                    content += line + "\n";
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return content;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            // this is executed on the main thread after the process is over
            // update your UI here
            //displayMessage(result);
            System.out.println("Match://***"+num+"\n"+result);
            num++;
        }
    }


}
