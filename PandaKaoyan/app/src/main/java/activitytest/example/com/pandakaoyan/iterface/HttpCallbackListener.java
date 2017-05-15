package activitytest.example.com.pandakaoyan.iterface;

/**
 * Created by class on 2017/5/14.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
