package activitytest.example.com.pandakaoyan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activitytest.example.com.pandakaoyan.adapters.PostAdapter;
import activitytest.example.com.pandakaoyan.panda.shiti.BasicActivity;
import activitytest.example.com.pandakaoyan.panda.shiti.Post;
/*
首页界面 登录后推荐帖子
 */
public class HomePageActivity extends BasicActivity {

    private List<Post> posts = new ArrayList<>();
    private String user;
    private int imageId;
    private ListView listView;
    private TextView back, pagename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);


        listView = (ListView) findViewById(R.id.first_page_listView);
        pagename = (TextView) findViewById(R.id.page_name);
        back = (TextView) findViewById(R.id.back);
        pagename.setText("您的位置:首页");



        initPosts();//初始化listview
        PostAdapter adapter = new PostAdapter(HomePageActivity.this, R.layout.home_list_item, posts);
        listView.setAdapter(adapter);//启动适配器

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = posts.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("image", post.getImageId());
                bundle.putString("username", post.getUsername());
                bundle.putString("title", post.getTittle());
                bundle.putString("content", post.getContent());//以上传递楼主的信息

                Intent intent = new Intent(HomePageActivity.this, PostContentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });





        //返回功能
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.this.finish();
            }
        });


    }




    //初始化数据（模拟数据）
    private void initPosts() {

        for (int i = 0; i < 3; i++) {
            Post post = new Post("相对布局 android:layout_above  为将该控件的底部放在指定id控件的上方" +
                    "android:layout_below   同理类似，将该控件的顶部放在指定id控件的下方", R.drawable.user1, "20分钟前",
                    "你知道小仙女是什么吗？", "我全家都是仙女！");

            posts.add(post);
            Post post1 = new Post("相对布局 android:layout_above  为将该控件的底部放在指定id控件的上方" +
                    "android:layout_below   同理类似，将该控件的顶部放在指定id控件的下方", R.drawable.user2, "20分钟前",
                    "你知道小仙女是什么吗？", "我就是小仙女啊哒哒");
            posts.add(post1);
            Post post2 = new Post("相对布局 android:layout_above  为将该控件的底部放在指定id控件的上方" +
                    "android:layout_below   同理类似，将该控件的顶部放在指定id控件的下方", R.drawable.user3, "20分钟前",
                    "你知道小仙女是什么吗？", "就要喝养乐多");
            posts.add(post2);
            Post post3 = new Post("相对布局 android:layout_above  为将该控件的底部放在指定id控件的上方" +
                    "android:layout_below   同理类似，将该控件的顶部放在指定id控件的下方", R.drawable.user4, "20分钟前",
                    "你知道小仙女是什么吗？", "我才是小仙女呢");
            posts.add(post3);

        }


    }

}
