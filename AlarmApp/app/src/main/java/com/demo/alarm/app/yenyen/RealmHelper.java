package com.demo.alarm.app.yenyen;

import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by macbook on 5/26/18.
 */

public class RealmHelper {
    private static Realm mRealm = Realm.getDefaultInstance();
    public static void addItemAsync(final AlarmClock item) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AlarmClock.create(realm, item);
            }
        });
    }

    public static RealmResults<AlarmClock> getAll() {
        return mRealm.where(AlarmClock.class).findAll();
    }


    public static int getMaxId() {
        int nextId = 0;
        Number id = mRealm.where(AlarmClock.class).max("id");
        // If id is null, set it to 1, else set increment it by 1
        nextId = (id == null) ? 0 : id.intValue();
        return nextId;
    }

    public static void deleteItemAsync(final int id) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AlarmClock.delete(realm, id);
            }
        });
    }


    public static void deleteItemsAsync(Realm realm, Collection<Integer> ids) {
        // Create an new array to avoid concurrency problem.
        final Integer[] idsToDelete = new Integer[ids.size()];
        ids.toArray(idsToDelete);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Integer id : idsToDelete) {
                    AlarmClock.delete(realm, id);
                }
            }
        });
    }

}
