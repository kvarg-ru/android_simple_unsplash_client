package android.academy.spb.simple_unsplash_client.PreviewFragment;

import android.academy.spb.simple_unsplash_client.R;
import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.PreviewPhoto;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 29.05.2018.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ImageHolder> {

    private List<PreviewPhoto> mPreviewPhotoList;

    public PhotosAdapter(List<PreviewPhoto> previewPhotoList) {
        this.mPreviewPhotoList = previewPhotoList;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_photo, parent, false);
        return new ImageHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

        PreviewPhoto previewPhoto = mPreviewPhotoList.get(position);

        Picasso.get().load(previewPhoto.getUrls().getSmall()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mPreviewPhotoList.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageHolder (View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photoImageView);
        }

    }
}
