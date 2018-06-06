package android.academy.spb.simple_unsplash_client.ViewPageComponents;

import android.academy.spb.simple_unsplash_client.App;
import android.academy.spb.simple_unsplash_client.CollectionRepository;
import android.academy.spb.simple_unsplash_client.R;
import android.academy.spb.simple_unsplash_client.net.unsplash.api.UnsplashApi;
import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.IconType;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 24.05.2018.
 */

public class ScreenSlidePageFragment extends Fragment {

    private static final String ARG_COLLECTION_ID = "args:collection_id";

    private Collection mCollection;

    private TextView mTitleTV;
    private TextView mDescriptionTV;
    private ImageView mImageView;
    private LikeButton mLikeButton;

    public interface FragmentListener {

        void onPreviewCollectionClick(int collectionId);

    }

    private FragmentListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = getArguments().getInt(ARG_COLLECTION_ID);
        mCollection = CollectionRepository.getInstance().getById(id);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        mTitleTV = rootView.findViewById(R.id.titleTextView);
        mDescriptionTV = rootView.findViewById(R.id.descriprionTextView);
        mImageView = rootView.findViewById(R.id.pictureImageView);
        mLikeButton = rootView.findViewById(R.id.likeButton);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitleTV.setText(mCollection.getTitle());
        mDescriptionTV.setText(mCollection.getDescription());
        //String url = mCollection.getPreviewPhotos().get(0).getUrls().getSmall();
        String url = mCollection.getCoverPhoto().getUrls().getSmall();
        Picasso.get().load(url).into(mImageView);

        if (mCollection.getCoverPhoto().getLikedByUser()) {
            Log.d("CollectionView", "Already Liked!");
            //mLikeButton.setEnabled(true);
        }

        if (listener != null) {
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPreviewCollectionClick(mCollection.getId());
                }
            });
        }

        mLikeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                performLikeOrUnlike(true);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                performLikeOrUnlike(false);
            }
        });

    }

    public static ScreenSlidePageFragment newInstance(int id) {

        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_COLLECTION_ID, id);
        fragment.setArguments(bundle);
        return fragment;

    }

    private UnsplashApi getUnsplashApi() {
        return ((App) getActivity().getApplication()).getUnsplashApi();
    }

    private void performLikeOrUnlike(final boolean like) {

        Callback<Void> callback = new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (like) {
                    if (response.code() == 201) {
                        Toast.makeText(getContext(), "Liked successful", Toast.LENGTH_LONG).show();
                        Log.d("CollectionView", "Liked successful");
                    } else {
                        Toast.makeText(getContext(), "Liked NOT successful", Toast.LENGTH_LONG).show();
                        Log.d("CollectionView", "Liked NOT successful");
                    }

                } else {
                    if (response.code() == 200) {
                        Toast.makeText(getContext(), "Unliked successful", Toast.LENGTH_LONG).show();
                        Log.d("CollectionView", "Unliked successful");
                    } else {
                        Toast.makeText(getContext(), "Unliked NOT successful", Toast.LENGTH_LONG).show();
                        Log.d("CollectionView", "Unliked NOT successful");
                    }


                }

                Log.d("CollectionView", "Responce code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Shit happened!", Toast.LENGTH_LONG).show();
                Log.d("CollectionView", "Like operation is failed");
            }
        };

        if (like) {
            getUnsplashApi().likePhoto(mCollection.getCoverPhoto().getId()).enqueue(callback);
        } else {
            getUnsplashApi().unlikePhoto(mCollection.getCoverPhoto().getId()).enqueue(callback);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentListener) {

            listener = (FragmentListener) context;

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
