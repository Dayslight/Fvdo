package adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fvdo.R;

import java.util.ArrayList;
import java.util.List;

import lastfmdatamodel.LastFmDataModel;

public class MusicRecyclerAdapter extends RecyclerView.Adapter<MusicRecyclerAdapter.VersionViewHolder> {
    ArrayList<String> tracks = new ArrayList<>();
    Boolean isHomeList = false;
    Context context;
    public static OnItemClickListener clickListener;


    public void setHomeActivitiesList(List<LastFmDataModel.Track> trackList) {

        for (int i = 0; i < trackList.size(); i++) {
            this.tracks.add(trackList.get(i).name);
        }

    }

    public MusicRecyclerAdapter(Context context, List<LastFmDataModel.Track> trackList) {
        isHomeList = true;
        this.context = context;
        setHomeActivitiesList(trackList);
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_musicrecycler, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        if (isHomeList) {
            versionViewHolder.title.setText(tracks.get(i));
        } else {
            versionViewHolder.title.setText(tracks.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return tracks == null ? 0 : tracks.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        TextView title;

        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            title = (TextView) itemView.findViewById(R.id.listitem_name);

            if (isHomeList) {
                itemView.setOnClickListener(this);
            }

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }


}