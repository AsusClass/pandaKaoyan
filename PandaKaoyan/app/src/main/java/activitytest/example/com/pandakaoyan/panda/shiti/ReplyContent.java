package activitytest.example.com.pandakaoyan.panda.shiti;

/**
 * Created by class on 2017/5/9.
 */

public class ReplyContent {
    String name;
    String content;
    int image;
    String title;
    public ReplyContent(int image,String name,String content) {
        this.content = content;
        this.name = name;
        this.image=image;
    }

    public ReplyContent(int image, String name, String title, String content) {
        this.content = content;
        this.image = image;
        this.name = name;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}