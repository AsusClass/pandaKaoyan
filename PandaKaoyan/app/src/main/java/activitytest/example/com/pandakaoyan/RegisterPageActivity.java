package activitytest.example.com.pandakaoyan;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import activitytest.example.com.pandakaoyan.iterface.HttpCallbackListener;
import activitytest.example.com.pandakaoyan.panda.shiti.BasicActivity;
import activitytest.example.com.pandakaoyan.panda.shiti.HttpURLUti;
import activitytest.example.com.pandakaoyan.panda.shiti.MyDatabaseHelper;

/*
注册界面
 */
public class RegisterPageActivity extends BasicActivity implements View.OnClickListener {
    private SQLiteDatabase db;
    private EditText user;
    private EditText password;
    private ImageView picture;
    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    private MyDatabaseHelper dbHelper;
    private String u, p;
    private String TAG = "RegisterPageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        user = (EditText) findViewById(R.id.edit_username);
        password = (EditText) findViewById(R.id.edit_password);
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

                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
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
                u = user.getText().toString();//获取注册时用户输入的内容
                p = password.getText().toString();
                int userImage = picture.getId();
                boolean isok = true;//注册信息合法标记
                if (u.equals("") || u.equals("!") || ",".equals(u) || ".".equals(u)
                        || p.equals("") || userImage == 0) {
                    isok = false;
                    Toast.makeText(RegisterPageActivity.this, "用户所填信息不合法！", Toast.LENGTH_SHORT).show();
                }
                if (isok) {
                    String address = "http://192.168.23.1:8080/pdky/RegisterServlet?username=" + u + "&password=" + p;
                    Log.d("LoginActivity", "---------" + address);
                    HttpURLUti.sendHttpRequest(address, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            try {
                                Log.d("LoginActivity", "---------" + response);
                                JSONObject jsonObject = new JSONObject(response.toString());
                                String temp = jsonObject.getString("json");
                                if (temp.equals("true")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterPageActivity.this, "注册成功！！！", Toast.LENGTH_SHORT).show();
                                            Intent intent1 = new Intent(RegisterPageActivity.this, LoginActivity.class);
                                            startActivity(intent1);
                                        }
                                    });



                                } else {
                                    Toast.makeText(RegisterPageActivity.this, "用户名已存在！", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
                    break;


                }
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