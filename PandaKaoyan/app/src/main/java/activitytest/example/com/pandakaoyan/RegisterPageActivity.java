package activitytest.example.com.pandakaoyan;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import activitytest.example.com.pandakaoyan.panda.shiti.BasicActivity;

public class RegisterPageActivity extends BasicActivity implements View.OnClickListener {
    private SQLiteDatabase db;
    private EditText user;
    private EditText password;
    private ImageView picture;
    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    private MyDatabaseHelper dbHelper;
    private String u, p;
private String TAG="RegisterPageActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        picture = (ImageView) findViewById(R.id.user_image);
        findViewById(R.id.take_photo).setOnClickListener(this);//拍照设置设置监听器

        findViewById(R.id.button_submitRegister).setOnClickListener(this);//注册确认设置监听器


        dbHelper = new MyDatabaseHelper(this, "PandaKaoyan.db", null, 1);
        db = dbHelper.getWritableDatabase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo://拍张照片
                //创建File对象，用于存储拍照后的图片
                String imagename = System.currentTimeMillis() + "";
                File outputImage = new File(getExternalCacheDir(), imagename);
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(RegisterPageActivity.this, "activitytest.example.com.pandakaoyan.fileprovider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                //启动相机
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                break;
            case R.id.button_submitRegister:
                //注册





                user = (EditText) findViewById(R.id.edit_username);
                password = (EditText) findViewById(R.id.edit_password);
                u = user.getText().toString();
                p = password.getText().toString();
                int userImage=picture.getId();

                boolean isok = true;//注册信息合法标记
                if (u.equals("") || u.equals("!") || ",".equals(u) || ".".equals(u)
                        || p.equals("")||userImage==0) {
                    isok = false;
                    Toast.makeText(RegisterPageActivity.this, "用户所填信息不合法！", Toast.LENGTH_SHORT).show();
                }
                if (isok) {
                    Cursor cursor = db.query("user", null, null, null, null, null, null);//查询user表中所有数据
                    boolean isUsernameExit = false;
                    if (cursor.moveToFirst()) {
                        do {
                            //遍历cursor对象
                            String name = cursor.getString(cursor.getColumnIndex("username"));
                            if (name.equals(user.getText().toString())) {
                                isUsernameExit = true;
                                Toast.makeText(RegisterPageActivity.this, "用户名已存在请重新输入！", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                    if (!isUsernameExit) {   //如果用户名可用保存新用户信息
                        // db.execSQL("insert into user(username,user_image,password) values(?,?,?)", new String[]{u, userImage, p});

                        dbHelper = new MyDatabaseHelper(this, "PandaKaoyan.db", null, 1);
                        db = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();//装数据
                        values.put("username", u);
                        values.put("user_image", userImage);
                        values.put("password", p);
                        db.insert("user", null, values);//插入数据到表中
                        values.clear();
                        //并跳转到登录界面
                        intent = new Intent(RegisterPageActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegisterPageActivity.this, "注册成功!请登录！", Toast.LENGTH_SHORT).show();

                    }
                }

                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }


}
