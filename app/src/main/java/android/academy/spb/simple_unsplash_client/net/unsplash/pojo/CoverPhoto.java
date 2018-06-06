package android.academy.spb.simple_unsplash_client.net.unsplash.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 06.06.2018.
 */

public class CoverPhoto {

    public class CoverPhotoUrls {

        @SerializedName("raw")
        @Expose
        private String raw;

        @SerializedName("full")
        @Expose
        private String full;

        @SerializedName("regular")
        @Expose
        private String regular;

        @SerializedName("small")
        @Expose
        private String small;

        @SerializedName("thumb")
        @Expose
        private String thumb;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getRegular() {
            return regular;
        }

        public void setRegular(String regular) {
            this.regular = regular;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

    }

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("urls")
    @Expose
    private CoverPhotoUrls urls;

    @SerializedName("liked_by_user")
    @Expose
    private Boolean likedByUser;

    public CoverPhotoUrls getUrls() {
        return urls;
    }

    public String getId() {
        return id;
    }

    public Boolean getLikedByUser() {
        return likedByUser;
    }
}
