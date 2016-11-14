package com.practice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gaofeng on 2016-11-14.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {

    List<String> data;
    private static final int HEADER_TYPE = 0;
    private static final int FOOTER_TYPE = 1;
    private static final int NORMAL_TYPE = 2;


    public MyAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutID = 0;
        switch (viewType) {
            case HEADER_TYPE: {
                layoutID = R.layout.header_layout;
                break;
            }
            case FOOTER_TYPE: {
                layoutID = R.layout.footer_layout;
                break;
            }
            case NORMAL_TYPE: {
                layoutID = R.layout.item_layout;
                break;
            }
            default:{
                break;
            }
        }
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if(getItemViewType(position)==NORMAL_TYPE) {
            holder.setText(data.get(position-1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        }
        if (position == getItemCount() - 1) {
            return FOOTER_TYPE;
        }
        return NORMAL_TYPE;
    }

    @Override
    public int getItemCount() {
        return data.size() + 2;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemTv;
        View itemView;
        int viewType;

        public ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            this.itemView = itemView;
            switch (viewType) {
                case HEADER_TYPE: {
                    itemTv = null;
                    break;
                }
                case FOOTER_TYPE: {
                    itemTv = null;
                    break;
                }
                case NORMAL_TYPE: {
                    itemTv = (TextView) itemView.findViewById(R.id.itemTv);
                    break;
                }
                default:{
                    itemTv=null;
                    break;
                }
            }
        }

        public int getViewType() {
            return viewType;
        }

        public void setText(String text) {
            if (itemTv != null)
                itemTv.setText(text);
        }
    }
}
