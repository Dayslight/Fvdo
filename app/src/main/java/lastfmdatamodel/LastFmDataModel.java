package lastfmdatamodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujitshrestha on 4/16/16.
 */
public class LastFmDataModel {

    private static String TAG_TRACK = "track";
    private static String TAG_NAME = "name";
    private static String TAG_URL = "url";
    private static String TAG_ARTIST = "artist";
    private static String TAG_IMAGE = "image";
    private static String TAG_IMAGEURL = "#text";


    public List<Track> tracks;

    public LastFmDataModel(JSONObject jsonObject) {

        if (!jsonObject.isNull(TAG_TRACK)) {
            this.tracks = new ArrayList<>();
            try {
                JSONArray jArr = jsonObject.getJSONArray(TAG_TRACK);
                for (int i = 0; i < jArr.length(); i++) {
                    this.tracks.add(new Track(jArr.getJSONObject(i)));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    public static class Track {
        public String name;
        public Artist artist;
        public List<Image> images;
        public String url;

        public Track(JSONObject jsonObject) {

            this.name = jsonObject.optString(TAG_NAME);
            this.url = jsonObject.optString(TAG_URL);
            this.artist = new Artist(jsonObject.optJSONObject(TAG_ARTIST));
            if (!jsonObject.isNull(TAG_IMAGE)) {

                this.images = new ArrayList<>();
                try {
                    JSONArray jArr = jsonObject.getJSONArray(TAG_IMAGE);
                    for (int i = 0; i < jArr.length(); i++) {
                        this.images.add(new Image(jArr.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


    }

    public static class Artist {

        public String name;

        public Artist(JSONObject jsonObject) {
            this.name = jsonObject.optString(TAG_NAME);


        }
    }


    public static class Image {
        public ImageDetail imagedetails;

        public Image(JSONObject jsonObject) {
            this.imagedetails = new ImageDetail(jsonObject);


        }
    }

    public static class ImageDetail {
        public String imageurl;
        public ImageDetail(JSONObject jsonObject) {
            this.imageurl = jsonObject.optString(TAG_IMAGEURL);
        }


    }
}
