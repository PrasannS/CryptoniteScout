package cryptonite624.android.apps.com.cryptonitescout;


import android.app.Application;
import android.content.res.Configuration;
import android.widget.Toast;

import cryptonite624.android.apps.com.cryptonitescout.Models.Config;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoMaster;
import cryptonite624.android.apps.com.cryptonitescout.Models.DaoSession;
import cryptonite624.android.apps.com.cryptonitescout.Models.User;


public class CRyptoniteApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DbOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();
        Config def = new Config();
        User u = new User();
        if(mDaoSession.getConfigDao().loadAll().size()==0) {
            def.setEventkey("2019tx");
            def.setCurrentmatch(0);
            def.setCurrentuser("c");
            mDaoSession.getConfigDao().save(def);
        }
        if(mDaoSession.getUserDao().loadAll().size()==0){
            u.setType("B1D");
            u.setEmail("c");
            u.setPassword("c");
            u.setUserLastname("c");
            u.setLoggedin(true);
            u.setUserFirstname("c");
            mDaoSession.getUserDao().save(u);
        }
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
