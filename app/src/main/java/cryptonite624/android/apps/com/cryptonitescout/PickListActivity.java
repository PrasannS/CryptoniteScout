package cryptonite624.android.apps.com.cryptonitescout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cpjd.models.teams.Team;

import java.util.ArrayList;

import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class PickListActivity extends AppCompatActivity {

    TableView<RankingData> pickListTableView;
    private static final String[] TABLE_HEADERS = { "This", "is", "a", "test" };

    private ArrayList<RankingData> rankings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        rankings = new ArrayList<RankingData>();

        pickListTableView = (TableView<RankingData>) findViewById(R.id.picklisttable);

        pickListTableView = (TableView<RankingData>) findViewById(R.id.tableView);
        RankingDataAdapter adapter = new RankingDataAdapter(this, rankings);
        pickListTableView.setDataAdapter(adapter);
        pickListTableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pick_list);



    }
}
