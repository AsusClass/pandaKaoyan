package activitytest.example.com.pandakaoyan.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import activitytest.example.com.pandakaoyan.R;
import activitytest.example.com.pandakaoyan.panda.shiti.ReplyContent;

/**
 * Created by class on 2017/5/10.
 */

public class ReplyAdapter extends BaseAdapter {
    private List<ReplyContent> replyContentList;
    private LayoutInflater inflater;


    public ReplyAdapter(List<ReplyContent> replyList, Context context) {
        this.replyContentList = replyList;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return replyContentList == null ? 0 : replyContentList.size();
    }

    @Override
    public ReplyContent getItem(int position) {
        return replyContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ReplyContent replyContent = getItem(position);
        View view;
        ImageView image;
        TextView name;
        TextView content,title;
        if (position == 0) {
            view = inflater.inflate(R.layout.post_item, null);
            image = (ImageView) view.findViewById(R.id.user_image2);
            name = (TextView) view.findViewById(R.id.username);
            content = (TextView) view.findViewById(R.id.post_item_content);
            title=(TextView)view.findViewById(R.id.post_item_content);
            title.setText(replyContent.getTitle());
        } else {

            view = inflater.inflate(R.layout.reply_item, null);

            image = (ImageView) view.findViewById(R.id.reply_item_image);
            name = (TextView) view.findViewById(R.id.reply_item_username);
            content = (TextView) view.findViewById(R.id.reply_item_content);
        }
        image.setImageResource(replyContent.getImage());
        name.setText(replyContent.getName());
        content.setText(replyContent.getContent());
        return view;
    }

   /* @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ReplyContent replyContent = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (position == 0) {

            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            ImageView image=(ImageView)view.findViewById(R.id.user_image2);
            TextView user=(TextView)view.findViewById(R.id.username);
            TextView title=(TextView)view.findViewById(R.id.post_item_title);
            TextView content=(TextView)view.findViewById(R.id.post_item_content);

            return view;
        } else {
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.image = (ImageView) view.findViewById(R.id.reply_item_image);
                viewHolder.name = (TextView) view.findViewById(R.id.reply_item_username);
                viewHolder.content = (TextView) view.findViewById(R.id.reply_item_content);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.image.setImageResource(replyContent.getImage());
            viewHolder.name.setText(replyContent.getName());
            viewHolder.content.setText(replyContent.getContent());


            return view;
        }
    }
*/

    class ViewHolder {
        TextView name;  //用户
        TextView content;//内容
        ImageView image;//头像
    }
}
