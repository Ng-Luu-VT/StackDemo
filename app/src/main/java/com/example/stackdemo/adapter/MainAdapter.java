package com.example.stackdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stackdemo.R;
import com.example.stackdemo.data.model.Item;
import com.example.stackdemo.inter.ItemMainInterface;
import com.example.stackdemo.item.MainItem;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.NewViewHolder> {


    private List<Item> mData;
    private Context mContext;

    public MainAdapter(ItemMainInterface itemMainInterface) {
        this.itemMainInterface = itemMainInterface;
    }

    private ItemMainInterface itemMainInterface;

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_main,parent,false);
        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewHolder holder, int position) {
        Item item = mData.get(position);
        TextView textView = holder.tvQuestion;
        holder.binData(item,textView);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void updateAnswers(List<Item> items){
        mData = items;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition){
        return mData.get(adapterPosition);
    }

    public class NewViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.actMain_tvQuestion);
            tvQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemMainInterface != null) itemMainInterface.onPostClick(getAdapterPosition());

                }
            });
        }

        public void binData(Item item, TextView textView) {
            textView.setText(item.getOwner().getDisplayName());
        }
    }

    public void setItemMainInterface(ItemMainInterface itemMainInterface) {
        this.itemMainInterface = itemMainInterface;
    }


}
