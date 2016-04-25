package ormodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

/**
 * Created by sujitshrestha on 4/9/16.
 */
public class DataModel extends Model {
    @Column(name = "name")
    public String name;
    @Column(name = "fbid")
    public String fbid;
    @Column(name = "nexturl")
    public String nexturl;


    public DataModel() {
        super();
    }


    public DataModel(String name, String fbid, String nexturl) {
        super();
        this.name = name;
        this.fbid = fbid;
        this.nexturl = nexturl;
    }


//    public static List<DataModel> getAll(DataModel category) {
//        return new Select()
//                .from(DataModel.class)
//                .where("Category = ?", category.getId())
//                .orderBy("Name ASC")
//                .execute();
//    }


}
