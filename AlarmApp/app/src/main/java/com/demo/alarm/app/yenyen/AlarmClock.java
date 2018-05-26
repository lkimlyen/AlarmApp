package com.demo.alarm.app.yenyen;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.annotations.PrimaryKey;

/**
 * Created by macbook on 5/26/18.
 */

public class AlarmClock  extends RealmObject {
    @PrimaryKey
    private int id;
    private int hour;
    private int minute;
    private String repeat;
    private String weeks;
    private String tag;
    private String ringName;
    private String ringUrl;
    private int ringPager;
    private boolean vibrate;
    private int napTimes;
    private boolean onOff;

    public AlarmClock(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRingName() {
        return ringName;
    }

    public void setRingName(String ringName) {
        this.ringName = ringName;
    }

    public String getRingUrl() {
        return ringUrl;
    }

    public void setRingUrl(String ringUrl) {
        this.ringUrl = ringUrl;
    }

    public int getRingPager() {
        return ringPager;
    }

    public void setRingPager(int ringPager) {
        this.ringPager = ringPager;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public int getNapTimes() {
        return napTimes;
    }

    public void setNapTimes(int napTimes) {
        this.napTimes = napTimes;
    }

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }

    static void delete(Realm realm, int id) {
        AlarmClock item = realm.where(AlarmClock.class).equalTo("id", id).findFirst();
        // Otherwise it has been deleted already.
        if (item != null) {
            item.deleteFromRealm();
        }
    }

    static void create(Realm realm, AlarmClock item) {
        AlarmClockList parent = realm.where(AlarmClockList.class).findFirst();
        RealmList<AlarmClock> items = parent.getItemList();
        items.clear();
        realm.copyToRealmOrUpdate(item);
        RealmResults<AlarmClock> realmResults = realm.where(AlarmClock.class).sort("id", Sort.DESCENDING).findAll();
        for (AlarmClock qrCode : realmResults){
            items.add(qrCode);
        }
    }
}
