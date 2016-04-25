package jsonparser;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by sujitshrestha on 4/9/16.
 */
public class FBJsonParser extends
        AsyncTask<Void, Void, JSONObject> {

    public FBJsonParser(Context context, String json) {


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        return null;
    }


    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}
