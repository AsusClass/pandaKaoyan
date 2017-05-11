package activitytest.example.com.pandakaoyan.panda.shiti;

/**
 * Created by class on 2017/5/7.
 */

public class Post {

    private int imageId;
    private String username;
    private String time;
    private String tittle;
    private String content;

    public Post(String content, int imageId, String time, String tittle, String username) {
        this.content = content;
        this.imageId = imageId;
        this.time = time;
        this.tittle = tittle;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getTittle() {
        return tittle;
    }

    public int getImageId() {
        return imageId;
    }

    public String getUsername() {
        return username;

    }


}
