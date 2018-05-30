package android.academy.spb.simple_unsplash_client.PreviewFragment;

import android.academy.spb.simple_unsplash_client.CollectionRepository;
import android.academy.spb.simple_unsplash_client.R;
import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by User on 29.05.2018.
 */

public class PreviewFragment extends Fragment {

    private final static String ARG_COLLECTION_ID = "args:collection_id";

    private Collection mCollection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int collection_id = getArguments().getInt(ARG_COLLECTION_ID);
        mCollection = CollectionRepository.getInstance().getById(collection_id);

        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_preview, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.previewRecyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        PhotosAdapter photosAdapter = new PhotosAdapter(mCollection.getPreviewPhotos());
        recyclerView.setAdapter(photosAdapter);

        return rootView;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.preview, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.openInBrowser:
                openBrowser(mCollection.getLinks().getHtml());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void openBrowser(String url) {

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public static PreviewFragment newInstance(int id) {

        PreviewFragment fragment = new PreviewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_COLLECTION_ID, id);
        fragment.setArguments(bundle);

        return fragment;

    }
}
