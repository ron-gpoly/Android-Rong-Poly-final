package com.example.classwork_recyclerview.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classwork_recyclerview.R;
import com.example.classwork_recyclerview.model.ModelList;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {
    private List<ModelList> data;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public Adapter(List<ModelList> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new ListViewHolder(itemView, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView desc;
        private TextView price1;
        private TextView price2;

        public ListViewHolder(@NonNull View itemView, OnItemClickListener onItemClick) {
            super(itemView);
            View rooView = itemView.findViewById(R.id.onClick);
            rooView.setOnClickListener( view -> {
                if (onItemClick != null) {
                    onItemClick.onItemClick(getBindingAdapterPosition());
                }
            } );



            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            price1 = itemView.findViewById(R.id.price1);
            price2 = itemView.findViewById(R.id.price2);


        }

        public void setData(ModelList item) {
            image.setImageResource(item.getImage());
            title.setText(item.getTitle());
            desc.setText(item.getDesc());
            price1.setText("$ " + item.getPrice1());
            price2.setText("$ " + item.getPrice2());

        }
    }
}
