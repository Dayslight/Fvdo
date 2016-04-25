package jsonparser;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import helper.JsonParserHelper;
import lastfmdatamodel.LastFmDataModel;

/**
 * Created by sujitshrestha on 4/16/16.
 */
public class LastFmAsyc extends
        AsyncTask<Void, Void, List<LastFmDataModel.Track>> {

    Context mContext;
    String mApiKey;
    String mArtistName;
    LastFmDataModel lastFmDataModel;

    public LastFmAsyc(Context context, String apikey, String artistname) {
        this.mContext = context;
        this.mApiKey = apikey;
        this.mArtistName = artistname;


    }

    @Override
    protected List<LastFmDataModel.Track> doInBackground(Void... params) {

        JsonParserHelper jsonParser = new JsonParserHelper();
        String json = jsonParser.makeServiceCall("http://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=" +
                mArtistName +
                "&api_key=" +
                mApiKey +
                "&format=json", JsonParserHelper.GET);

        try {
            System.out.println("Check this last fm response " + new JSONObject(json).optJSONObject("toptracks"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (json != null) {
            try {
                lastFmDataModel = new LastFmDataModel(new JSONObject(json).optJSONObject("toptracks"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return lastFmDataModel.tracks;
    }


    @Override
    protected void onPostExecute(List<LastFmDataModel.Track> track) {
        super.onPostExecute(track);


    }
}
