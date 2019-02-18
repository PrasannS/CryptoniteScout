package cryptonite624.android.apps.com.cryptonitescout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> teams = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> teams){
        mContext = context;
        this.teams = teams;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dashboard_matchlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final int position = i;
        holder.team1.setText(teams.get(position));
        holder.team2.setText(teams.get(position));
        holder.team3.setText(teams.get(position));
        holder.team4.setText(teams.get(position));
        holder.team5.setText(teams.get(position));
        holder.team6.setText(teams.get(position));



    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView team1;
        TextView team2;
        TextView team3;
        TextView team4;
        TextView team5;
        TextView team6;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            team1 = itemView.findViewById(R.id.matchlist_team1);
            team2 = itemView.findViewById(R.id.matchlist_team2);
            team3 = itemView.findViewById(R.id.matchlist_team3);
            team4 = itemView.findViewById(R.id.matchlist_team4);
            team5 = itemView.findViewById(R.id.matchlist_team5);
            team6 = itemView.findViewById(R.id.matchlist_team6);
            //parentLayout = itemView.findViewById(R.id.recyclerview_comments);
        }
    }

}
