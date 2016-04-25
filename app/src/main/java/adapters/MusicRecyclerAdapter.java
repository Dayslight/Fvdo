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
    //    ArrayList<String> duration = new ArrayList<>();
//    ArrayList<String> album = new ArrayList<>();
//    ArrayList<String> image = new ArrayList<>();
//    List<AlbumInfoDataModel> albumAsycs = new ArrayList<>();
    Boolean isHomeList = false;
    String apiKey;
    Context context;
    public static OnItemClickListener clickListener;


    public void setHomeActivitiesList(List<LastFmDataModel.Track> trackList) {

        for (int i = 0; i < trackList.size(); i++) {
            String songname = trackList.get(i).name;
            String artistname = trackList.get(i).artist.name;

            this.tracks.add(songname);

//
//            System.out.println("Songname " + songname);
//            System.out.println("Songname artistname " + artistname);
//
//            AlbumAsyc albumAsyc = new AlbumAsyc(context, apiKey, artistname, songname);
//            try {
//                albumAsycs = albumAsyc.execute().get();
//                for (int j = 0; j < 1; j++) {
//                    String duration = "";
//                    if (albumAsycs.get(j).duration != null) {
//                        duration = albumAsycs.get(j).duration;
//                    } else {
//                        this.duration.add(duration);
//
//                    }
//                    System.out.println("Songname duration " + albumAsycs.get(j).duration);
//                }
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
        }

    }

    public MusicRecyclerAdapter(Context context, List<LastFmDataModel.Track> trackList) {
        isHomeList = true;
        this.context = context;
        apiKey = context.getResources().getString(R.string.lastfmapi);
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
//            versionViewHolder.durationtxt.setText(duration.get(i));
        }

    }

    @Override
    public int getItemCount() {
        return tracks == null ? 0 : tracks.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        TextView title, durationtxt;

        public VersionViewHolder(View itemView) {
            super(itemView);
            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            title = (TextView) itemView.findViewById(R.id.listitem_name);
            durationtxt = (TextView) itemView.findViewById(R.id.durationtxt);
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