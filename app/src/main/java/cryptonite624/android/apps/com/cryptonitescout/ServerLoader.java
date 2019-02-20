package cryptonite624.android.apps.com.cryptonitescout;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
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

    public static String turl = "2018mxmo";

    public static String temp = "https://www.thebluealliance.com/api/v3/event/"+turl+"/matches/simple";

    public void uploadthings(){

            //String temp = "https://www.thebluealliance.com/api/v3/event/"+turl+"/matches/simple";
            //new GetUrlContentTask().execute(turl);
        try {
            sendGET();
        } catch (IOException e) {
            e.printStackTrace();
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
                connection.setRequestProperty("If-Modified-Since","7pMGgXcAgBXg3hN4mchVQo67y6bi0bmWJv7S7YLU2mw8hre3B98frdj49mxlk7QR");
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

    private static void sendGET() throws IOException {
        URL obj = new URL(temp);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-TBA-Auth-Key","7pMGgXcAgBXg3hN4mchVQo67y6bi0bmWJv7S7YLU2mw8hre3B98frdj49mxlk7QR" );
        con.setRequestProperty("accept","application/json");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }


}
