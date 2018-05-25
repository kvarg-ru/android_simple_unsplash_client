package android.academy.spb.simple_unsplash_client.ViewPageComponents;

import android.academy.spb.simple_unsplash_client.CollectionRepository;
import android.academy.spb.simple_unsplash_client.R;
import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;
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

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitleTV.setText(mCollection.getTitle());
        mDescriptionTV.setText(mCollection.getDescription());
        String url = mCollection.getPreviewPhotos().get(0).getUrls().getSmall();
        Picasso.get().load(url).into(mImageView);

    }

    public static ScreenSlidePageFragment newInstance(int id) {

        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_COLLECTION_ID, id);
        fragment.setArguments(bundle);
        return fragment;

    }
}
