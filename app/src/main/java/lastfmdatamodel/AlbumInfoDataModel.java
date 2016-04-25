package lastfmdatamodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujitshrestha on 4/20/16.
 */
public class AlbumInfoDataModel {
    private static String TAG_NAME = "name";
    private static String TAG_IMAGE = "image";
    private static String TAG_IMAGEURL = "#text";
    private static String TAG_ALBUM = "album";
    private static String TAG_DURATION = "duration";
    private static String TAG_TITLE = "title";


    public String name;
    public String duration;
    public Album album;


    public AlbumInfoDataModel(JSONObject jsonObject) {

        System.out.println("Check this asyc json " + jsonObject);


        this.name = jsonObject.optString(TAG_NAME);
        System.out.println("Check this asyc name " + name);
        this.duration = jsonObject.optString(TAG_DURATION);
        System.out.println("Check this asyc duration " + duration);
        if (!jsonObject.isNull(TAG_ALBUM)) {
            this.album = new Album(jsonObject.optJSONObject(TAG_ALBUM));
            System.out.println("Check this asyc album " + album);
        }


    }

    public static class Album {
        public String title;
        public List<Image> images;

        public Album(JSONObject jsonObject) {
            this.title = jsonObject.optString(TAG_TITLE);
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
