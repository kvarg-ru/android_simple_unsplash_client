package android.academy.spb.simple_unsplash_client.net.unsplash.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 24.05.2018.
 */

public class PreviewPhoto {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("urls")
    @Expose
    private PreviewPhotoUrls urls;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PreviewPhotoUrls getUrls() {
        return urls;
    }

    public void setUrls(PreviewPhotoUrls urls) {
        this.urls = urls;
    }

}
