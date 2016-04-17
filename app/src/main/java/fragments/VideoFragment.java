package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fvdo.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

import adapters.MusicRecyclerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import jsonparser.LastFmAsyc;
import lastfmdatamodel.LastFmDataModel;

public class VideoFragment extends Fragment implements MusicRecyclerAdapter.OnItemClickListener {


    @Bind(R.id.dummyfrag_scrollableview)
    RecyclerView dummyfragScrollableview;
    MusicRecyclerAdapter adapter;
    List<LastFmDataModel.Track> trackList;


    public VideoFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, view);
        String artistname = getArguments().getString("artistname");
        getVideoList(artistname);
        return view;
    }

    private void getVideoList(String artistname) {
        String apiKey = getActivity().getResources().getString(R.string.lastfmapi);
        LastFmAsyc lastFmAsyc = new LastFmAsyc(getActivity(), apiKey, artistname);
        try {
            trackList = lastFmAsyc.execute().get();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            dummyfragScrollableview.setLayoutManager(linearLayoutManager);
            dummyfragScrollableview.setHasFixedSize(true);
            adapter = new MusicRecyclerAdapter(getActivity(),trackList);
            dummyfragScrollableview.setAdapter(adapter);

            adapter.clickListener = this;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        System.out.println("Check this ck...");
    }
}
