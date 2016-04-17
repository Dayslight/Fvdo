package fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fvdo.R;

import adapters.ViewPager_Adapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {


    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        setupViewPager(viewpager);
        tablayout.setupWithViewPager(viewpager);

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewpager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
//                        showToast("One");
                        break;
                    case 1:
//                        showToast("Two");
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPager_Adapter adapter = new ViewPager_Adapter(getFragmentManager());
        adapter.addFrag(new Fragment_MyMusic(), "My Musics");
        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.ripple_material_light)), "Friends Music");
        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark)), "Explore");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
