package com.example.rajat.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.example.rajat.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(new MyAdapter(this));
        binding.recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onItemClick(View v) {

        View v1 = v.findViewById(R.id.view1);
        View v2 = v.findViewById(R.id.view2);
        Activity activityContext = MainActivity.this;
        final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                activityContext,
                Pair.create(v1, v1.getTransitionName()), Pair.create(v2, v2.getTransitionName()));
        startActivity(new Intent(activityContext, SecondActivity.class), options.toBundle());
    }
}
