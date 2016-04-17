package fbdatamodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bijaybogati on 4/9/16.
 */
public class FBDataModel {

    private static String TAG_DATA = "data";
    private static String TAG_ID = "id";
    private static String TAG_NAME = "name";
    private static String TAG_PAGING = "paging";
    private static String TAG_CURSORS = "cursors";
    private static String TAG_AFTER = "after";
    private static String TAG_BEFORE = "before";
    private static String TAG_NEXT = "next";


    public List<DataList> dataList;
    public Paging paging;


    public FBDataModel(JSONObject jsonObject) {

        this.paging = new Paging(jsonObject.optJSONObject(TAG_PAGING));
        this.dataList = new ArrayList<DataList>();
        if (!jsonObject.isNull(TAG_DATA)) {
            JSONArray jArr = null;
            try {
                jArr = jsonObject.getJSONArray(TAG_DATA);
                for (int i = 0; i < jArr.length(); i++) {
                    this.dataList.add(new DataList(jArr.getJSONObject(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }


    public static class DataList {
        public String id;
        public String name;


        public DataList(JSONObject jsonObject) {


            this.id = jsonObject.optString(TAG_ID);
            this.name = jsonObject.optString(TAG_NAME);

        }


    }


    public static class Paging {

        public Cursoritem cursor;
        public String next;


        public Paging(JSONObject jsonObject) {
            this.next = jsonObject.optString(TAG_NEXT);
            this.cursor = new Cursoritem(jsonObject.optJSONObject(TAG_CURSORS));


        }


    }


    public static class Cursoritem {
        public String after;
        public String before;


        public Cursoritem(JSONObject jsonObject) {
            this.after = jsonObject.optString(TAG_AFTER);
            this.before = jsonObject.optString(TAG_BEFORE);
        }
    }


}
