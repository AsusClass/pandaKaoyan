package activitytest.example.com.pandakaoyan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import activitytest.example.com.pandakaoyan.panda.shiti.BasicActivity;

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
        dbHelper = new MyDatabaseHelper(this, "PandaKaoyan.db", null, 1);
        db = dbHelper.getWritableDatabase();
        username = (EditText) findViewById(R.id.Edit_username);
        password = (EditText) findViewById(R.id.Edit_password);
        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.TextView_login).setOnClickListener(this);
        findViewById(R.id.TextView_register).setOnClickListener(this);
        findViewById(R.id.login_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TextView_login:
            case R.id.button_login:  //登录跳转到主页
                login();
                break;
            case R.id.TextView_register://跳转到注册页面
                Intent intent = new Intent(LoginActivity.this, RegisterPageActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    public void login() {
        int image=userImage.getId();
        String u = username.getText().toString();
        String p = password.getText().toString();

        Cursor cursor = db.query("user", null, null, null, null, null, null);//查询user表中所有数据
        boolean isok = true;//信息合法标记
        if (u.equals("") || p.equals("")) {
            isok = false;//不合法
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
        }
        if (isok) {
            boolean temp = false;//登录成功标记
            if (cursor.moveToFirst()) {

                do {
                    //遍历cursor对象
                    String name = cursor.getString(cursor.getColumnIndex("username"));
                    String paw = cursor.getString(cursor.getColumnIndex("password"));
                    if (name.equals(u) && paw.equals(p)) {
                        temp = true;


                        Bundle bundle = new Bundle();
                        bundle.putString("username", u);
                        bundle.putInt("image",image );
                        Intent intent2 = new Intent(LoginActivity.this, HomePageActivity.class);
                        intent2.putExtra("bundle",bundle);
                        startActivity(intent2);
                        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                while (cursor.moveToNext());

            }
            if (!temp) {
                Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
    }


}



