package activitytest.example.com.pandakaoyan.panda.shiti;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by class on 2017/5/5.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_USER = "create table user(" + "id integer primary key autoincrement," +
            "username text," +"user_image int,"+ "password text)";
    private static final String CREATE_USERINFO="create table user_info("+"user_id int auto_increment primary key," +
            "user_image varchar(10) not null,"+"username varchar(10) not null," +
            "password varchar(20) not null,"+"age tinyint,sex char(1),"+"shool varchar(10)," +
            "target_school varchar(20) not null,"+"exam_time varchar(10) not null," +
            "major varchar(10) not null,"+"city varchar(10))";
    private static  final  String CREATE_POST="create table post("+"post_id int auto_increment primary key,"
            +"post_author varchar(10),"+"post_content text not null,"
            +"post_hidden tinyint)";
    private static  final  String CREATE_DISCUSS="create table disscuss("+"disscuss_id int auto_increment primary key,"
            +"discuss_author varchar(10),"+"to_post_id int not null,"+"disscuss_content text not null,"+
            "disscuss_hidden tinyint)";
    private static final String CREATE_REPLY="create table reply("+"reply_id int auto_increment primary key,"+"reply_author varchar(10),"+
            "to_discuss_id int not null,"+"reply_content text not null,"+"reply_hidden tinyint)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_USERINFO);
        db.execSQL(CREATE_POST);
        db.execSQL(CREATE_DISCUSS);
        db.execSQL(CREATE_REPLY);
        //Log.d("MyDatabaseHelper.this", "cerate table succeed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
