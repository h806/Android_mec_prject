package com.h.mechanicalengineering.lessons;
import android.annotation.SuppressLint;

import com.h.mechanicalengineering.utilse.LessonsModel;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

@SuppressLint("ParcelCreator")
public class GroupSaver extends ExpandableGroup<LessonsModel> {

    public GroupSaver(String title, List<LessonsModel> items) {
        super(title, items);
    }

}