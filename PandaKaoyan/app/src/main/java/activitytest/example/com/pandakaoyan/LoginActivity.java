package activitytest.example.com.pandakaoyan;
/*
登录界面  主活动
 */


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimerTask;

import activitytest.example.com.pandakaoyan.iterface.HttpCallbackListener;
import activitytest.example.com.pandakaoyan.panda.shiti.BasicActivity;
import activitytest.example.com.pandakaoyan.panda.shiti.HttpURLUti;
import activitytest.example.com.pandakaoyan.panda.shiti.MyDatabaseHelper;

public class LoginActivity extends BasicActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper;
    private EditText username;
    private EditText password;
    private SQLiteDatabase db;
    private ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        //  dbHelper = new MyDatabaseHelper(this, "PandaKaoyan.db", null, 1);
        // db = dbHelper.getWritableDatabase();//获取SQL操作对象


        username = (EditText) findViewById(R.id.Edit_username);
        password = (EditText) findViewById(R.id.Edit_password);
        userImage = (ImageView) findViewById(R.id.login_image);
        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.TextView_login).setOnClickListener(this);
        findViewById(R.id.TextView_register).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TextView_login:
            case R.id.button_login:  //登录跳转到主页
                // login();
                String u = username.getText().toString();
                String p = password.getText().toString();
                if (u.equals("") || p.equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }                break;
            case R.id.TextView_register://跳转到注册页面
                Intent intent = new Intent(LoginActivity.this, RegisterPageActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    //登录逻辑 与服务器通讯
    public void login() {

        String u = username.getText().toString();
        String p = password.getText().toString();
        boolean isok = true;//信息合法标记
        if (u.equals("") || p.equals("")) {
            isok = false;//不合法
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
        }
        if (isok) {
            String address = "http://192.168.23.1:8080/pdky/LoginServlet?username="+u+"&password="+p;
            Log.d("LoginActivity", "---------" + address);
            HttpURLUti.sendHttpRequest(address, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    try {
                        Log.d("LoginActivity", "---------" + response);
                        JSONObject jsonObject = new JSONObject(response.toString());
                        String temp = jsonObject.getString("json");
                        if (temp.equals("true")) {
                            doLogin();
                        } else {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
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


        }
    }


    //登录成功跳转
    private void doLogin() {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {

                Intent intent2 = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(intent2);
                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();

            }
        });
    }

}


