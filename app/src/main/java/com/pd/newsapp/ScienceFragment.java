package com.pd.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScienceFragment extends Fragment {
    String api = "be55b62d63094334bf73aae59d10c128";
    ArrayList<ModalClass> modalClassArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewofscience;
    private String category = "science";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.sciencefragment, null );

        recyclerViewofscience = v.findViewById( R.id.recyclerviewofscience );
        modalClassArrayList = new ArrayList<>();
        recyclerViewofscience.setLayoutManager( new LinearLayoutManager( getContext() ) );
        adapter = new Adapter( getContext(), modalClassArrayList );
        recyclerViewofscience.setAdapter( adapter );
        findNews();

        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews( country, category, 100, api ).enqueue( new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    modalClassArrayList.addAll( response.body().getArticles() );
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        } );
    }
}
