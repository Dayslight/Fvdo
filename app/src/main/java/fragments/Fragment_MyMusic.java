package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.fvdo.R;

import java.util.ArrayList;
import java.util.List;

import activities.ListMusicActivity;
import adapters.MyMusicAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import fbdatamodel.FBDataModel;
import helper.GeneralHelper;

public class Fragment_MyMusic extends Fragment {


    List<FBDataModel.DataList> arrayartist = new ArrayList<FBDataModel.DataList>();
    @Bind(R.id.musicgridview)
    GridView musicgridview;
    GeneralHelper generalHelper;
    FBDataModel fbDataModel;
    String fbid;


    public static Fragment_MyMusic newInstance() {
        return new Fragment_MyMusic();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_gridview, container, false);
        ButterKnife.bind(this, view);
        generalHelper = new GeneralHelper(getActivity());
        fbid = generalHelper.getFbID();

        System.out.println("check this fbid " + fbid);


        getLikedmusic(fbid);
        return view;
    }


    private void getLikedmusic(String fbid) {
        /* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + fbid + "/music",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                    /* handle the result */
                        System.out.println("Check this response: " + response.getJSONObject());
                        fbDataModel = new FBDataModel(response.getJSONObject());
                        arrayartist = fbDataModel.dataList;


                        musicgridview.setAdapter(new MyMusicAdapter(getActivity(), arrayartist));
                        musicgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                Intent intent = new Intent(getActivity(), ListMusicActivity.class);
                                intent.putExtra("artistname", arrayartist.get(position).name);
                                intent.putExtra("artistimage",arrayartist.get(position).id);
                                startActivity(intent);
                            }
                        });

                    }
                }


        ).executeAsync();


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}