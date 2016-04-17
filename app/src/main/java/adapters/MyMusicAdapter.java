package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.fvdo.R;

import java.util.ArrayList;
import java.util.List;

import fbdatamodel.FBDataModel;

public class MyMusicAdapter extends BaseAdapter {
    private Context context;
    List<FBDataModel.DataList> dataLists;

    ArrayList<String> nameData = new ArrayList<>();
    ArrayList<String> idData = new ArrayList<>();

    boolean memCache = false;
    boolean fileCache = true;
    AQuery aq;

    public MyMusicAdapter(Context context, List<FBDataModel.DataList> musicList) {
        this.context = context;
        this.dataLists = musicList;
        aq = new AQuery(context);


        for (int j = 0; j < dataLists.size(); j++) {
            nameData.add(dataLists.get(j).name);
            idData.add(dataLists.get(j).id);

            System.out.println("Check ids   .. " + idData.get(j));
        }


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            vi = inflater.inflate(R.layout.items_gridview, null);
            holder = new ViewHolder();
            holder.musicpagename = (TextView) vi.findViewById(R.id.musicpagename);
            holder.imageView = (ImageView) vi.findViewById(R.id.imageView);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        holder.musicpagename.setText(nameData.get(position));


        String imageUrl = "http://graph.facebook.com/" + idData.get(position) + "/picture?type=large&redirect=true&width=200&height=150";
        aq.id(holder.imageView).image(imageUrl, memCache, fileCache, 0, 0);


        return vi;
    }

    public static class ViewHolder {
        public TextView musicpagename;
        public ImageView imageView;
    }

    @Override
    public int getCount() {
        if (nameData.size() <= 0)
            return 1;
        return nameData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}