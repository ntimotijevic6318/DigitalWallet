package com.example.wallet.view.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallet.R;
import com.example.wallet.model.Item;

import java.util.function.Function;

public class ItemAdapter extends ListAdapter<Item, ItemAdapter.ViewHolder> {


    Function<Item , Void> onItemClicked;
    Function<Item , Void> onItemDeleted;
    Function<Item  , Void> onItemEdit;

    public ItemAdapter(@NonNull DiffUtil.ItemCallback<Item> diffCallback , Function<Item , Void> onItemClicked , Function<Item , Void> onItemDeleted , Function<Item , Void> onItemEdit) {
        super(diffCallback);
        this.onItemClicked = onItemClicked;
        this.onItemDeleted = onItemDeleted;
        this.onItemEdit = onItemEdit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_item, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
            Item item = getItem(position);
            onItemClicked.apply(item);
            return null;
        } , positionDeleted -> {
            Item item  = getItem(positionDeleted);
            onItemDeleted.apply(item);
            return  null;
        } , postionEdit-> {
            Item item = getItem(postionEdit);
            onItemEdit.apply(item);
            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = getItem(position);
        holder.bind(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked , Function<Integer, Void> onItemDeleted , Function<Integer,Void> onItemEdit) {
            super(itemView);
            this.context = context;


            itemView.setOnClickListener(v-> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION){
                    onItemClicked.apply(getAdapterPosition());
                }
            });


            itemView.findViewById(R.id.delete).setOnClickListener(v->{
                if(getAdapterPosition() != RecyclerView.NO_POSITION){
                    onItemDeleted.apply(getAdapterPosition());
                }
            });


            itemView.findViewById(R.id.edit).setOnClickListener(v->{
                if(getAdapterPosition() != RecyclerView.NO_POSITION){
                    onItemEdit.apply(getAdapterPosition());
                }
            });



        }

        public void bind(Item item) {
             ImageView imageView = itemView.findViewById(R.id.money);
            if ((item.isIncome())) {
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.atachgreen));
            } else {
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.attachred));
            }
            // Glide.with(context).load(item.getPicture()).circleCrop().into(imageView);
            ((TextView)itemView.findViewById(R.id.title)).setText(item.getTitle());
            ((TextView)itemView.findViewById(R.id.amount)).setText(item.getAmount());
        }

    }
}
