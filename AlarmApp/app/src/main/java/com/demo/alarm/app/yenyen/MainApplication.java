package com.demo.alarm.app.yenyen;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by macbook on 5/26/18.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.createObject(AlarmClockList.class);
                    }})
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
