package cryptonite624.android.apps.com.cryptonitescout;

import cryptonite624.android.apps.com.cryptonitescout.Models.BasicMatchEntry;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataTransferI {
    /*
    Get request to fetch city weather.Takes in two parameter-city name and API key.
    */
    /* Over here we are sending a POST request with two fields as POST request body params */
    @FormUrlEncoded
    @POST("apploader.php")
    Call< BasicMatchEntry > postMatchData(@Field("teamnum") Integer teamnum, @Field("switchcubes") Integer switchcubes, @Field("scalecubes") Integer scalecubes);
}