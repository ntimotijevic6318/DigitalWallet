package com.example.wallet.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.wallet.model.Item;

public class ItemDiffItemCallback extends DiffUtil.ItemCallback<Item> {
    @Override
    public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
        return
                 oldItem.getTitle().equals(newItem.getTitle())
                && oldItem.getAmount().equals(newItem.getAmount());
    }
}
