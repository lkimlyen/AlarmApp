package com.demo.alarm.app.yenyen;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by macbook on 5/26/18.
 */

public class AlarmClockList extends RealmObject {
    @SuppressWarnings("unused")
    private RealmList<AlarmClock> itemList;

    public RealmList<AlarmClock> getItemList() {
        return itemList;
    }
}
