package com.example.stackdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.stackdemo.R;
import com.example.stackdemo.adapter.MainAdapter;
import com.example.stackdemo.data.model.Item;
import com.example.stackdemo.data.model.SOAnswersResponse;
import com.example.stackdemo.data.remote.ApiUtils;
import com.example.stackdemo.data.remote.SOService;
import com.example.stackdemo.inter.ItemMainInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemMainInterface {
    private RecyclerView recyclerView;
    private SOService mService;
    private MainAdapter mainAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mService = ApiUtils.getSoService();
        recyclerView = findViewById(R.id.actMain_rvQuestion);
        //mainAdapter = new MainAdapter(this,new ArrayList<Item>(0), new MainAdapter());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        loadAnswers();

    }

    private void loadAnswers() {
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {

            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
                if(response.isSuccessful()) {
                    mainAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

    private ArrayList<Item> genData() {

    }

    @Override
    public void onPostClick(int id) {
        Toast toast = Toast.makeText(MainActivity.this, "Post id is " + id, Toast.LENGTH_SHORT);
        toast.show();
    }
}