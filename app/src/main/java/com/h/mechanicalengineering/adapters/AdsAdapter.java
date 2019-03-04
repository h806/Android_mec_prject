package com.h.mechanicalengineering.adapters;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.h.mechanicalengineering.R;
import com.h.mechanicalengineering.ads.ShowAd;
import com.h.mechanicalengineering.utilse.AdsModel;

import java.util.List;


public class AdsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private Activity activity;
    private List<AdsModel> productList;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;




    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public AdsAdapter(RecyclerView recyclerView, List<AdsModel> productList, Activity activity) {
        this.productList = productList;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return productList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context scontext = parent.getContext();


        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.ads_item, parent, false);
            return new UserViewHolder(view ,scontext);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            AdsModel items = productList.get(position);
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.txtTitle.setText(items.getTitle());
            userViewHolder.txtSubtitle.setText(items.getSubtitle());
            userViewHolder.txtCity.setText(items.getCity());
            userViewHolder.txtDate.setText(items.getDate());

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }


    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {




        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar1);
        }
    }

    private class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtSubtitle,txtDate,txtCity;
        ImageView imageView;

        public Context mcontext;

        public UserViewHolder(View view , final Context context) {

            super(view);

            this.mcontext = context;

            txtTitle = itemView.findViewById(R.id.ads_title);
            txtSubtitle = itemView.findViewById(R.id.sub_title);
            txtCity = itemView.findViewById(R.id.city);
            txtDate = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.img_employ);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    AdsModel adsModel = productList.get(position);
                    String id = adsModel.getTitle();

                    Intent intent = new Intent(context,ShowAd.class);

                    intent.putExtra("title",adsModel.getTitle());
                    intent.putExtra("subtitle",adsModel.getSubtitle());
                    intent.putExtra("describtion",adsModel.getDescription());
                    intent.putExtra("degree",adsModel.getDegree());
                    intent.putExtra("expire_date",adsModel.getDate());
                    intent.putExtra("phone",adsModel.getPhone());
                    intent.putExtra("email",adsModel.getEmail());
                    intent.putExtra("city",adsModel.getCity());

                    context.startActivity(intent);
                }
            });
        }
    }
}
