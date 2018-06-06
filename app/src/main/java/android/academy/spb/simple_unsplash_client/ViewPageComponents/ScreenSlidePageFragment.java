package android.academy.spb.simple_unsplash_client.ViewPageComponents;

import android.academy.spb.simple_unsplash_client.CollectionRepository;
import android.academy.spb.simple_unsplash_client.R;
import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        String url = mCollection.getPreviewPhotos().get(0).getUrls().getSmall();
        Picasso.get().load(url).into(mImageView);

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
                Toast.makeText(getContext(), "Like", Toast.LENGTH_LONG).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Toast.makeText(getContext(), "Unlike", Toast.LENGTH_LONG).show();
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
