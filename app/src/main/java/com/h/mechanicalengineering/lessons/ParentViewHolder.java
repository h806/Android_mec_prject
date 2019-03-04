package com.h.mechanicalengineering.lessons;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.utilse.LessonsModel;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


public class ParentViewHolder extends ChildViewHolder implements View.OnClickListener {

    private TextView phoneName;
    Context context = itemView.getContext();


    public ParentViewHolder(View itemView) {
        super(itemView);

        phoneName = (TextView) itemView.findViewById(R.id.phone_name);
    }

    public void onBind(LessonsModel phone, ExpandableGroup group) {
        phoneName.setText(phone.getName());
        phoneName.setOnClickListener(this);


    }


//click on items of lessonActivity and send that id to showActivity to use for show its data

    @Override
    public void onClick(View view) {
        String id = phoneName.getText().toString();
        Intent intent = new Intent(context,ShowActivity.class);
        intent.putExtra("nameID",id+"");
        context.startActivity(intent);
    }
}