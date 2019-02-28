package cryptonite624.android.apps.com.cryptonitescout;


import android.app.Application;
import android.content.res.Configuration;
import android.widget.Toast;

import cryptonite624.android.apps.com.cryptonitescout.Models.Config;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoMaster;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;


public class CRyptoniteApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DbOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();
        Config def = new Config();
        def.setEventkey("2019week0");
        def.setCurrentmatch(0);
        def.setCurrentuser("default@default.com");
        mDaoSession.getConfigDao().save(def);

    }

    private DaoSession mDaoSession;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}
