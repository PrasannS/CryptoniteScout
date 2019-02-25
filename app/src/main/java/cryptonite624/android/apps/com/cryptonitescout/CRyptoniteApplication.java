package cryptonite624.android.apps.com.cryptonitescout;


import android.app.Application;
import android.content.res.Configuration;
import android.widget.Toast;

import com.orm.SchemaGenerator;
import com.orm.SugarApp;
import com.orm.SugarContext;
import com.orm.SugarDb;


public class CRyptoniteApplication extends Application implements ServerLoader.ServerLoadListener{


    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());


        // create table if not exists
        SchemaGenerator schemaGenerator = new SchemaGenerator(this);
        schemaGenerator.createDatabase(new SugarDb(this).getDB());
    }

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
        SugarContext.terminate();
    }

    @Override
    public void onServerLoad() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "This is a message displayed in a Toast",
                Toast.LENGTH_LONG);

        toast.show();

    }
}
