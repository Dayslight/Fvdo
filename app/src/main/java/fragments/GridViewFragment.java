package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.fvdo.R;

import java.util.ArrayList;
import java.util.List;

import adapters.GridviewAdapter;

public class GridViewFragment extends Fragment {

    AsymmetricGridView listView;
    GridviewAdapter gridviewAdapter;

    ArrayList<String> arrayartist = new ArrayList<String>();


    public static GridViewFragment newInstance() {
        return new GridViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



            View view = inflater.inflate(R.layout.fragment_gridview, container, false);

            //temp
        try {
            arrayartist.add("");
            arrayartist.add("");
            arrayartist.add("");
            arrayartist.add("");
            arrayartist.add("");
            arrayartist.add("");


            listView = (AsymmetricGridView) view.findViewById(R.id.listView);
            // Choose your own preferred column width
            listView.setRequestedColumnWidth(Utils.dpToPx(getActivity(), 120));
            final List<AsymmetricItem> items = new ArrayList<>();

            // initialize your items array
            gridviewAdapter = new GridviewAdapter(getActivity(),listView, arrayartist);
            AsymmetricGridViewAdapter asymmetricAdapter =
                    new AsymmetricGridViewAdapter<>(getActivity(), listView, gridviewAdapter);
            listView.setAdapter(asymmetricAdapter);

            listView.setAllowReordering(true);

            listView.isAllowReordering(); // true


        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}