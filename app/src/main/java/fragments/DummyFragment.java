package fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.fvdo.R;
import com.fvdo.VersionModel;

import java.util.ArrayList;
import java.util.List;

import adapters.SimpleRecyclerAdapter;
import helper.GeneralHelper;

public class DummyFragment extends Fragment {
    int color;
    SimpleRecyclerAdapter adapter;
    ArrayList<String> friendName = new ArrayList<>();
    ArrayList<String> freindsId = new ArrayList<>();
    GeneralHelper generalHelper;
    View view;
    String fbid;


    public DummyFragment() {
    }

    @SuppressLint("ValidFragment")
    public DummyFragment(int color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dummy_fragment, container, false);

        final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
        frameLayout.setBackgroundColor(color);
        generalHelper = new GeneralHelper(getActivity());
        fbid = generalHelper.getFbID();
        getLikedmusic(fbid);

        return view;
    }


    private void getLikedmusic(String fbid) {
        /* make the API call */


        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                        System.out.println("Check friends response " + response.getJSONObject());
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setHasFixedSize(true);

                        List<String> list = new ArrayList<String>();
                        for (int i = 0; i < VersionModel.data.length; i++) {
                            list.add(VersionModel.data[i]);
                        }

                        adapter = new SimpleRecyclerAdapter(list);
                        recyclerView.setAdapter(adapter);

                    }
                }
        ).executeAsync();

    }


}