package com.h.mechanicalengineering.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.dictionary.Show_Dictionary_Activity;
import com.h.mechanicalengineering.lessons.ShowActivity;
import com.h.mechanicalengineering.utilse.DictionaryModel;

import java.util.List;


public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.viewHolder> {
    public List<DictionaryModel> data;


    public DictionaryAdapter(){

    }
   public void setData(List<DictionaryModel> data){

        this.data=data;
        notifyDataSetChanged();

   }


    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context=parent.getContext();

        LayoutInflater inflater=LayoutInflater.from(context);
        View wordView=inflater.inflate(R.layout.recycler_item,parent,false);

        viewHolder holder=new viewHolder(wordView,context);

        return holder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        //          setText textView
        DictionaryModel dictionaryModel=data.get(position);
        holder.wordText.setText(dictionaryModel.getDefinition());
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

       public class viewHolder extends RecyclerView.ViewHolder {

        //           Click on items
        public Context context;
        public TextView wordText;
        public viewHolder(View itemView , final Context context) {
            super(itemView);
            this.context=context;

            wordText=(TextView) itemView.findViewById(R.id.wordText);

            //click on items of dictionary and get id and send to showActivity

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    DictionaryModel dictionaryModel=data.get(position);
                    String id = dictionaryModel.getDefinition();
                    Intent intent = new Intent(context,Show_Dictionary_Activity.class);
                    intent.putExtra("nameID",id);
                    context.startActivity(intent);
                }

            });
        }
    }
}
