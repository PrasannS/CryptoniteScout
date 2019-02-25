package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cryptonite624.android.apps.com.cryptonitescout.Models.RankingData;
import de.codecrafters.tableview.TableDataAdapter;

public class RankingDataAdapter extends TableDataAdapter<RankingData> {

    private static final int TEXT_SIZE = 14;

    public RankingDataAdapter(Context context, ArrayList<RankingData> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final RankingData rankData = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderRank(rankData);
                break;
            case 1:
                renderedView = renderCargoAverage(rankData);
                break;
            case 2:
                renderedView = renderHatchAverage(rankData);
                break;
            case 3:
                renderedView = renderWinrate(rankData);
                break;
        }

        return renderedView;
    }

    private View renderRank(final RankingData data){
        return renderString("" + data.getRanking());
    }

    private View renderCargoAverage(final RankingData data){
        return renderString("" + data.getCargoAvg());
    }

    private View renderHatchAverage(final RankingData data){
        return renderString("" + data.getHatchAvg());
    }

    private View renderWinrate(final RankingData data){
        return renderString("" + data.getWinRate());
    }

    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
}
