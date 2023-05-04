package com.example.wallet.view.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallet.R;
import com.example.wallet.model.Item;
import com.example.wallet.view.activities.EditActivity;
import com.example.wallet.view.activities.InformationActivity;
import com.example.wallet.view.recycler.adapter.ItemAdapter;
import com.example.wallet.view.recycler.differ.ItemDiffItemCallback;
import com.example.wallet.viewmodel.SharedViewModel;
import com.example.wallet.viewmodel.ShowViewModel;

public class ExpensesFragment extends Fragment {


    ShowViewModel showViewModel;
    SharedViewModel sharedViewModel;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    public static final String ClcikedKey = "ClickedItem";

    public  ExpensesFragment(){
        super(R.layout.exprenses_fragment_layout);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showViewModel = new ViewModelProvider(this).get(ShowViewModel.class);
        // sa requireActivity() u ViewModelProvider viewModel vezujemo za activity
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // moramo da prosledjujemo view kroz metode jer nismo u activity, pa po default-u nemamo pristup metodama kao sto su findViewById
        init(view);
    }

    private void init(View view) {
        initView(view);
        initListeners();
        initObservers(view);
        initRecycler();
    }

    private void initRecycler() {

        itemAdapter = new ItemAdapter(new ItemDiffItemCallback() , item-> {
            Toast.makeText(getContext(), String.valueOf(item.getId()) , Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getContext() , InformationActivity.class);
            intent.putExtra(ClcikedKey , item);
            startActivity(intent);
            return null;
        }, itemToDelete->{
            sharedViewModel.removeExpenses(itemToDelete);
            return  null;
        },  itemToEdit->{
            Intent intent = new Intent(getContext() , EditActivity.class);
            intent.putExtra(IncomFragment.EditKey,  itemToEdit);
            startActivityForResult(intent, 111);
            return null;
        });



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(itemAdapter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 111:
                if(resultCode == Activity.RESULT_OK){

                    Item item = (Item)data.getExtras().getSerializable("message");
                    sharedViewModel.editItemExpense(item);
                }
        }
    }


    private void initListeners() {
    }


    private void initView(View view) {
        recyclerView = view.findViewById(R.id.RecyclerViewExpenses);
    }

    @SuppressLint("WrongConstant")
    private void initObservers(View view) {
        sharedViewModel.getItemsExpenses().observe(getViewLifecycleOwner(), items -> {
            itemAdapter.submitList(items);
        });
    }


}
