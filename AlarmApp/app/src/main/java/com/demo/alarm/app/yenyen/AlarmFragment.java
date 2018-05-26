package com.demo.alarm.app.yenyen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.demo.alarm.app.yenyen.dialogs.CustomDialogSetting;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class AlarmFragment extends Fragment {
    private final String TAG = AlarmFragment.class.getName();
    @BindView(R.id.rv_alarm)
    RecyclerView rvAlarm;

    private AlarmAdapter adapter;
    private Realm realm = Realm.getDefaultInstance();

    public static AlarmFragment newInstance() {
        AlarmFragment fragment = new AlarmFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        adapter = new AlarmAdapter(realm.where(AlarmClockList.class).findFirst().getItemList(),
                new AlarmAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(AlarmClock item) {

                    }
                }, new AlarmAdapter.OnSettingClickListener() {
            @Override
            public void onSettingClick(final AlarmClock item) {
                final CustomDialogSetting dialog = new CustomDialogSetting();
                dialog.show(getActivity().getFragmentManager(), TAG);
                dialog.setListener(new CustomDialogSetting.OnEditClickListener() {
                    @Override
                    public void onEditClick() {

                    }
                }, new CustomDialogSetting.OnDeleteClickListener() {
                    @Override
                    public void onDeleteClick() {
                        RealmHelper.deleteItemAsync(item.getId());

                    }
                }, new CustomDialogSetting.OnDuplicateClickListener() {
                    @Override
                    public void onDuplicateClick() {

                    }
                }, new CustomDialogSetting.OnReviewClickListener() {
                    @Override
                    public void onReviewClick() {

                    }
                });

            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvAlarm.setLayoutManager(mLayoutManager);
        rvAlarm.setAdapter(adapter);
        rvAlarm.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    @OnClick(R.id.fab_add)
    public void add() {
        Intent intent = new Intent(getContext(), AddEditAlarmActivity.class);
        getContext().startActivity(intent);
    }

}
