package activitytest.example.com.pandakaoyan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import activitytest.example.com.pandakaoyan.R;
import activitytest.example.com.pandakaoyan.panda.shiti.Post;

/**
 * Created by class on 2017/5/7.
 */

public class PostAdapter extends ArrayAdapter<Post> {
    private int resourceId;

    public PostAdapter(Context context, int resource, List<Post> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);

        View view;
        ViewHolder viewHolder;
//提升listview的运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.userImage = (ImageView) view.findViewById(R.id.post_user_image);
            viewHolder.username = (TextView) view.findViewById(R.id.post_username);
            viewHolder.post_time = (TextView) view.findViewById(R.id.post_time);
            viewHolder.post_title = (TextView) view.findViewById(R.id.post_title);
            viewHolder.post_abstract = (TextView) view.findViewById(R.id.content_abstract);
            view.setTag(viewHolder);//将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.userImage.setImageResource(post.getImageId());

        viewHolder.username.setText(post.getUsername());
        viewHolder.post_time.setText(post.getTime());
        viewHolder.post_title.setText(post.getTittle());
        viewHolder.post_abstract.setText(post.getContent());
        return view;
    }


    class ViewHolder {
        ImageView userImage;
        TextView username;
        TextView post_time;
        TextView post_title;
        TextView post_abstract;//摘要
    }
}
