package activitytest.example.com.pandakaoyan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import activitytest.example.com.pandakaoyan.panda.shiti.BasicActivity;

/*
关于用户的个人介绍
 */
public class AboutmeActivityActivity extends BasicActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutme_activity);
    }
}
