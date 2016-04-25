package jsonparser;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import helper.JsonParserHelper;
import lastfmdatamodel.AlbumInfoDataModel;

/**
 * Created by sujitshrestha on 4/20/16.
 */
public class AlbumAsyc extends
        AsyncTask<Void, Void, List<AlbumInfoDataModel>> {

    Context mContext;
    String mApiKey;
    String mArtistName;
    String mSongName;
    List<AlbumInfoDataModel> albumInfoDataModel;

    public AlbumAsyc(Context context, String apikey, String artistname, String songname) {
        this.mContext = context;
        this.mApiKey = apikey;
        this.mArtistName = artistname;
        this.mSongName = songname;


    }

    @Override
    protected List<AlbumInfoDataModel> doInBackground(Void... params) {

        JsonParserHelper jsonParser = new JsonParserHelper();
        String json = jsonParser.makeServiceCall("http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=" +
                mApiKey +
                "&artist=" +
                mArtistName +
                "&track=" +
                mSongName +
                "&format=json", JsonParserHelper.GET);

        try {
            System.out.println("Check this last fm response " + new JSONObject(json).optJSONObject("track"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (json != null) {
            try {

                if (new JSONObject(json).optJSONObject("track") == null) {

                } else {
                    AlbumInfoDataModel albumInfo = new AlbumInfoDataModel(new JSONObject(json).optJSONObject("track"));
                    albumInfoDataModel = new ArrayList<>();
                    albumInfoDataModel.add(albumInfo);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return albumInfoDataModel;
    }


    @Override
    protected void onPostExecute(List<AlbumInfoDataModel> track) {
        super.onPostExecute(track);


    }
}
