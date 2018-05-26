package com.demo.alarm.app.yenyen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by macbook on 5/26/18.
 */

public class AlarmAdapter extends RealmRecyclerViewAdapter<AlarmClock, AlarmAdapter.HistoryHolder> {

    private boolean inDeletionMode = false;
    private Set<Integer> countersToDelete = new HashSet<>();
    private OnItemClickListener listener;
    private OnSettingClickListener onSettingClickListener;

    public AlarmAdapter(OrderedRealmCollection<AlarmClock> data, OnItemClickListener listener,
                        OnSettingClickListener onSettingClickListener) {
        super(data, true);
        setHasStableIds(true);
        this.listener = listener;
        this.onSettingClickListener = onSettingClickListener;
    }

    void enableDeletionMode(boolean enabled) {
        inDeletionMode = enabled;
        if (!enabled) {
            countersToDelete.clear();
        }
        notifyDataSetChanged();
    }



    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alarm, parent, false);
        HistoryHolder holder = new HistoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        final AlarmClock obj = getItem(position);
        setDataToViews(holder, obj);
        holder.bind(obj, listener, onSettingClickListener);


    }

    private void setDataToViews(HistoryHolder holder, AlarmClock item) {
        holder.txtTime.setText(item.getHour()+":"+item.getMinute());
        holder.txtRepeat.setText(item.getRepeat());
        holder.tbTurn.setChecked(item.isOnOff());

    }


    public class HistoryHolder extends RecyclerView.ViewHolder {

        TextView txtTime;
        TextView txtRepeat;
        ToggleButton tbTurn;
        ImageView imgSetting;
        LinearLayout llRepeat;

        private HistoryHolder(View v) {
            super(v);
            txtTime = (TextView) v.findViewById(R.id.tv_time);
            txtRepeat = (TextView) v.findViewById(R.id.tv_repeat);
            tbTurn = (ToggleButton) v.findViewById(R.id.tb_turn);
            imgSetting = (ImageView) v.findViewById(R.id.img_setting);
            llRepeat = (LinearLayout) v.findViewById(R.id.ll_repeat);
        }


        private void bind(final AlarmClock item, final OnItemClickListener listener,
                          final OnSettingClickListener onSettingClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

            imgSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSettingClickListener.onSettingClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(AlarmClock item);
    }

    public interface OnSettingClickListener {
        void onSettingClick(AlarmClock item);
    }


}
