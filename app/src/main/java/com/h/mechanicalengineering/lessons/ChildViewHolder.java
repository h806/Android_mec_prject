package com.h.mechanicalengineering.lessons;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.h.mechanicalengineering.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;


public class ChildViewHolder extends GroupViewHolder {

    private TextView osName;

    public ChildViewHolder(View itemView) {
        super(itemView);

        osName = (TextView) itemView.findViewById(R.id.mobile_os);
    }

    @Override
    public void expand() {
        osName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
        Log.i("Adapter", "expand");
    }

    @Override
    public void collapse() {
        Log.i("Adapter", "collapse");
        osName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_black_24dp, 0);
    }
    public void setGroupName(ExpandableGroup group) {
        osName.setText(group.getTitle());
    }


}
