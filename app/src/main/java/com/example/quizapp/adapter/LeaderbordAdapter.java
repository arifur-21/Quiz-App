package com.example.quizapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.databinding.RowLeaderbordsBinding;
import com.example.quizapp.models.Users;

import java.util.ArrayList;

public class LeaderbordAdapter extends RecyclerView.Adapter<LeaderbordAdapter.LeaderbordViewHolder> {

    private Context context;
    private ArrayList<Users> arrayList;

    public LeaderbordAdapter(Context context, ArrayList<Users> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public LeaderbordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_leaderbords,parent, false);
        return new LeaderbordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderbordViewHolder holder, int position) {

        Users users = arrayList.get(position);
        holder.binding.nameId.setText(users.getName());
        holder.binding.coinsId.setText(String.valueOf(users.getCoins()));
        holder.binding.indexId.setText(String.format("#%d", position+1));
        Glide.with(context).load(users.getProfile())
                .into(holder.binding.imageViewId);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class LeaderbordViewHolder extends RecyclerView.ViewHolder {
RowLeaderbordsBinding binding;
        public LeaderbordViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowLeaderbordsBinding.bind(itemView);
        }
    }
}
