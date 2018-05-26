package com.demo.alarm.app.yenyen;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Collection;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class AddEditAlarmActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private final String TAG = AddEditAlarmActivity.class.getName();
    private static final int REQUEST_RING_SELECT = 1;
    private Boolean isMondayChecked = false;
    private Boolean isTuesdayChecked = false;
    private Boolean isWednesdayChecked = false;
    private Boolean isThursdayChecked = false;
    private Boolean isFridayChecked = false;
    private Boolean isSaturdayChecked = false;
    private Boolean isSundayChecked = false;
    private StringBuilder mRepeatStr;

    @BindView(R.id.tp_time)
    TimePicker tpTime;

    @BindView(R.id.txt_ring_name)
    TextView txtRingName;

    @BindView(R.id.tb_monday)
    ToggleButton tbMonday;

    @BindView(R.id.tb_tuesday)
    ToggleButton tbTuesday;

    @BindView(R.id.tb_wednesday)
    ToggleButton tbWednesday;

    @BindView(R.id.tb_thursday)
    ToggleButton tbThursday;

    @BindView(R.id.tb_friday)
    ToggleButton tbFriday;

    @BindView(R.id.tb_saturday)
    ToggleButton tbSaturday;

    @BindView(R.id.tb_sunday)
    ToggleButton tbSunday;

    private TreeMap<Integer, String> mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_alarm);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tpTime.setIs24HourView(true);
        SharedPreferences share =getSharedPreferences(
                Constants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
        int currentHour = share.getInt(Constants.DEFAULT_ALARM_HOUR, tpTime.getCurrentHour());
        int currentMinute = share.getInt(Constants.DEFAULT_ALARM_MINUTE, tpTime.getCurrentMinute());
        tpTime.setCurrentHour(currentHour);
        tpTime.setCurrentMinute(currentMinute);

        tpTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

            }

        });


    }
    private void initRepeat() {
        mRepeatStr = new StringBuilder();
        mMap = new TreeMap<>();
        tbMonday.setOnCheckedChangeListener(this);
        tbTuesday.setOnCheckedChangeListener(this);
        tbWednesday.setOnCheckedChangeListener(this);
        tbThursday.setOnCheckedChangeListener(this);
        tbFriday.setOnCheckedChangeListener(this);
        tbSaturday.setOnCheckedChangeListener(this);
        tbSunday.setOnCheckedChangeListener(this);
    }

    private void initRing() {
        SharedPreferences share = getSharedPreferences(
                Constants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
        String ringName = share.getString(Constants.RING_NAME,
                getString(R.string.default_ring));
        String ringUrl = share.getString(Constants.RING_URL,
                Constants.DEFAULT_RING_URL);
        txtRingName.setText(ringName);
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.tb_monday:
                if (isChecked) {
                    isMondayChecked = true;
                    mMap.put(1, getString(R.string.monday));
                    setRepeatDescribe();
                } else {
                    isMondayChecked = false;
                    mMap.remove(1);
                    setRepeatDescribe();
                }
                break;
            case R.id.tb_tuesday:
                if (isChecked) {
                    isTuesdayChecked = true;
                    mMap.put(2, getString(R.string.tuesday));
                    setRepeatDescribe();
                } else {
                    isTuesdayChecked = false;
                    mMap.remove(2);
                    setRepeatDescribe();
                }
                break;
            case R.id.tb_wednesday:
                if (isChecked) {
                    isWednesdayChecked = true;
                    mMap.put(3, getString(R.string.wednesday));
                    setRepeatDescribe();
                } else {
                    isWednesdayChecked = false;
                    mMap.remove(3);
                    setRepeatDescribe();
                }
                break;
            case R.id.tb_thursday:
                if (isChecked) {
                    isThursdayChecked = true;
                    mMap.put(4, getString(R.string.thursday));
                    setRepeatDescribe();
                } else {
                    isThursdayChecked = false;
                    mMap.remove(4);
                    setRepeatDescribe();
                }
                break;
            case R.id.tb_friday:
                if (isChecked) {
                    isFridayChecked = true;
                    mMap.put(5, getString(R.string.friday));
                    setRepeatDescribe();
                } else {
                    isFridayChecked = false;
                    mMap.remove(5);
                    setRepeatDescribe();
                }
                break;
            case R.id.tb_saturday:
                if (isChecked) {
                    isSaturdayChecked = true;
                    mMap.put(6, getString(R.string.saturday));
                    setRepeatDescribe();
                } else {
                    isSaturdayChecked = false;
                    mMap.remove(6);
                    setRepeatDescribe();
                }
                break;
            case R.id.tb_sunday:
                if (isChecked) {
                    isSundayChecked = true;
                    mMap.put(7, getString(R.string.sunday));
                    setRepeatDescribe();
                } else {
                    isSundayChecked = false;
                    mMap.remove(7);
                    setRepeatDescribe();
                }
                break;
            case R.id.tb_vibrate:
                if (isChecked) {
                    MyUtil.vibrate(this);
                } else {
                }
                break;

        }

    }

    /**
     * 设置重复描述的内容
     */
    private void setRepeatDescribe() {
        // 全部选中
        if (isMondayChecked & isTuesdayChecked & isWednesdayChecked
                & isThursdayChecked & isFridayChecked & isSaturdayChecked
                & isSundayChecked) {
//            mRepeatDescribe.setText(getResources()
//                    .getString(R.string.every_day));
//            mAlarmClock.setRepeat(getString(R.string.every_day));
//            // 响铃周期
//            mAlarmClock.setWeeks("2,3,4,5,6,7,1");
            // 周一到周五全部选中
        } else if (isMondayChecked & isTuesdayChecked & isWednesdayChecked
                & isThursdayChecked & isFridayChecked & !isSaturdayChecked
                & !isSundayChecked) {
//            mRepeatDescribe.setText(getString(R.string.week_day));
//            mAlarmClock.setRepeat(getString(R.string.week_day));
//            mAlarmClock.setWeeks("2,3,4,5,6");
            // 周六、日全部选中
        } else if (!isMondayChecked & !isTuesdayChecked & !isWednesdayChecked
                & !isThursdayChecked & !isFridayChecked & isSaturdayChecked
                & isSundayChecked) {
//            mRepeatDescribe.setText(getString(R.string.week_end));
//            mAlarmClock.setRepeat(getString(R.string.week_end));
//            mAlarmClock.setWeeks("7,1");
            // 没有选中任何一个
        } else if (!isMondayChecked & !isTuesdayChecked & !isWednesdayChecked
                & !isThursdayChecked & !isFridayChecked & !isSaturdayChecked
                & !isSundayChecked) {
//            mRepeatDescribe.setText(getString(R.string.repeat_once));
//            mAlarmClock.setRepeat(getResources()
//                    .getString(R.string.repeat_once));
//            mAlarmClock.setWeeks(null);

        } else {
            mRepeatStr.setLength(0);
            mRepeatStr.append(getString(R.string.week));
            Collection<String> col = mMap.values();
            for (String aCol : col) {
                mRepeatStr.append(aCol).append(getResources().getString(R.string.caesura));
            }
            // 去掉最后一个"、"
            mRepeatStr.setLength(mRepeatStr.length() - 1);
//            mRepeatDescribe.setText(mRepeatStr.toString());
//            mAlarmClock.setRepeat(mRepeatStr.toString());

            mRepeatStr.setLength(0);
            if (isMondayChecked) {
                mRepeatStr.append("M,");
            }
            if (isTuesdayChecked) {
                mRepeatStr.append("T,");
            }
            if (isWednesdayChecked) {
                mRepeatStr.append("W,");
            }
            if (isThursdayChecked) {
                mRepeatStr.append("Th,");
            }
            if (isFridayChecked) {
                mRepeatStr.append("F,");
            }
            if (isSaturdayChecked) {
                mRepeatStr.append("Sa,");
            }
            if (isSundayChecked) {
                mRepeatStr.append("S,");
            }
        }

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_RING_SELECT:
                String name = data.getStringExtra(Constants.RING_NAME);
                String url = data.getStringExtra(Constants.RING_URL);
                int ringPager = data.getIntExtra(Constants.RING_PAGER, 0);
                txtRingName.setText(name);

        }
    }


}
