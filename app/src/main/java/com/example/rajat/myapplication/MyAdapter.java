package com.example.rajat.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rajat.myapplication.databinding.ItemsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajat Sangrame on 13/1/20.
 * http://github.com/rajatsangrame
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> list;
    private OnItemClickListener mListener;

    public MyAdapter(OnItemClickListener listener) {

        mListener = listener;
        list = new ArrayList<>();
        list.add("A");
        list.add("B");
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemsBinding viewProductCategoryBinding = ItemsBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(viewProductCategoryBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view);
        }
    }

}
