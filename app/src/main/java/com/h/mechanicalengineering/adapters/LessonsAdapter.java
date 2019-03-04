package com.h.mechanicalengineering.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h.mechanicalengineering.lessons.GroupSaver;
import com.h.mechanicalengineering.lessons.ChildViewHolder;
import com.h.mechanicalengineering.utilse.LessonsModel;
import com.h.mechanicalengineering.lessons.ParentViewHolder;
import com.h.mechanicalengineering.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;


public class LessonsAdapter extends ExpandableRecyclerViewAdapter<ChildViewHolder, ParentViewHolder> {

    private Activity activity;

    public LessonsAdapter(Activity activity, List<GroupSaver> groups) {
        super(groups);
        this.activity = activity;

    }

    @Override
    public ChildViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_view_holder, parent, false);

        return new ChildViewHolder(view);
    }

    @Override
    public ParentViewHolder onCreateChildViewHolder(ViewGroup parent, final int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.child_view_holder, parent, false);

        return new ParentViewHolder(view);


    }

    @Override
    public void onBindGroupViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupName(group);
    }

    @Override
    public void onBindChildViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final LessonsModel phone = (LessonsModel) ((GroupSaver) group).getItems().get(childIndex);
        holder.onBind(phone,group);
    }


}