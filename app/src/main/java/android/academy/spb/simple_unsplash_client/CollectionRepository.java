package android.academy.spb.simple_unsplash_client;

import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 25.05.2018.
 */

public class CollectionRepository {


    private static CollectionRepository mINSTANCE = new CollectionRepository();

    private HashMap<Integer, Collection> mCollectionHashMap;

    public CollectionRepository() {

        mCollectionHashMap = new HashMap<>();

    }

    public static CollectionRepository getInstance() {

        return mINSTANCE;

    }

    public Collection getById(int id) {

        return mCollectionHashMap.get(id);

    }

    public void save(Collection collection) {

        mCollectionHashMap.put(collection.getId(), collection);

    }

    public List<Collection> getCollectionList() {

        return (List<Collection>) mCollectionHashMap.values();

    }

    public List<Integer> getIdList() {

        return new ArrayList<>(mCollectionHashMap.keySet());

    }

}
