package com.example.ecommerces.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.ecommerces.Adapter.ExploreMenuAdapter;
import com.example.ecommerces.Model.ExploreMenu;
import com.example.ecommerces.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FirebaseFirestore mStore;
    private List<ExploreMenu> exploreMenuList;
    private ExploreMenuAdapter menuAdapter;
    RecyclerView recyclerView_exploreMenu;

    public HomeFragment(){

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mStore = FirebaseFirestore.getInstance();
        recyclerView_exploreMenu=view.findViewById(R.id.rv_exploreMenu);

        exploreMenuList=new ArrayList<>();
        menuAdapter=new ExploreMenuAdapter(getContext(), exploreMenuList);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView_exploreMenu.setLayoutManager(layoutManager);
        recyclerView_exploreMenu.setAdapter(menuAdapter);
        mStore.collection("ExploreMenu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        ExploreMenu exploreMenu = document.toObject(ExploreMenu.class);
                        exploreMenuList.add(exploreMenu);
                        recyclerView_exploreMenu.setAdapter(menuAdapter);
                        menuAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView_exploreMenu.setAdapter(menuAdapter);
    }
}