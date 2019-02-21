package cryptonite624.android.apps.com.cryptonitescout;

import android.app.Application;

import com.orm.SugarApp;


public class CRyptoniteApplication extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate(){
        SugarContext.terminate();
        super.onTerminate();
    }
}
