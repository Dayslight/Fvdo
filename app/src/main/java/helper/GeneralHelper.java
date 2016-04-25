package helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sujitshrestha on 4/9/16.
 */
public class GeneralHelper {

    Context mContext;
    String genpref = "generalPref";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public GeneralHelper(Context context) {

        this.mContext = context;
        this.pref = this.mContext
                .getSharedPreferences(genpref, Context.MODE_PRIVATE);

    }

    public void setFbID(String fbid) {
        editor = pref.edit();
        editor.putString("fbid", fbid);
        editor.apply();

    }


    public String getFbID() {
        return pref.getString("fbid", "");

    }


}
