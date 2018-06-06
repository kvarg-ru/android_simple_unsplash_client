package android.academy.spb.simple_unsplash_client.net.unsplash.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 24.05.2018.
 */

public class Collection {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("published_at")
    @Expose
    private String publishedAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("curated")
    @Expose
    private Boolean curated;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("total_photos")
    @Expose
    private Integer totalPhotos;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("share_key")
    @Expose
    private Object shareKey;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("cover_photo")
    @Expose
    private CoverPhoto coverPhoto = null;
    @SerializedName("preview_photos")
    @Expose
    private List<PreviewPhoto> previewPhotos = null;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("links")
    @Expose
    private Links links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getCurated() {
        return curated;
    }

    public void setCurated(Boolean curated) {
        this.curated = curated;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Integer getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(Integer totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public Object getShareKey() {
        return shareKey;
    }

    public void setShareKey(Object shareKey) {
        this.shareKey = shareKey;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public List<PreviewPhoto> getPreviewPhotos() {
        return previewPhotos;
    }

    public void setPreviewPhotos(List<PreviewPhoto> previewPhotos) {
        this.previewPhotos = previewPhotos;
    }

    public CoverPhoto getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(CoverPhoto coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
