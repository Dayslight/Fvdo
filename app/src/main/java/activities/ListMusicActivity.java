package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.fvdo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import fragments.VideoFragment;

public class ListMusicActivity extends AppCompatActivity {


    boolean memCache = false;
    boolean fileCache = true;
    AQuery aq;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.profile_image)
    CircleImageView profileImage;
    @Bind(R.id.artistnametxt)
    TextView artistnametxt;
    @Bind(R.id.numberofsongstxt)
    TextView numberofsongstxt;
    @Bind(R.id.videoframe)
    FrameLayout videoframe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        aq = new AQuery(getApplicationContext());


        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbarLayout.setTitle("Top Videos");


        Intent intent = getIntent();
        String artistname = intent.getExtras().getString("artistname");
        String imageUrl = "http://graph.facebook.com/" + intent.getExtras().getString("artistimage") + "/picture?type=large&redirect=true&width=200&height=150";

        Bundle bundle = new Bundle();
        bundle.putString("artistname", artistname);


        aq.id(profileImage).image(imageUrl, memCache, fileCache, 0, 0);
        artistnametxt.setText(artistname);


        Fragment fragment = new VideoFragment();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.videoframe, fragment).commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:   //this item has your app icon
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

}

