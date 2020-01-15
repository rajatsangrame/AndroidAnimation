package com.example.rajat.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.rajat.myapplication.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener,
        OnItemClickListener {

    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);


        binding.container.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                binding.container.getViewTreeObserver().removeOnPreDrawListener(this);
                startAnimate();
                return true;
            }
        });

        binding.progressBar.setOnClickListener(this);
        binding.viewPlay.setOnClickListener(this);

    }

    private void startAnimate() {

        Transition transition = new Slide(Gravity.END);
        transition.setDuration(500);
        TransitionManager.beginDelayedTransition(binding.container, transition);

        binding.viewLeft.setVisibility(View.VISIBLE);
        binding.viewRight.setVisibility(View.VISIBLE);
    }

    private void bottomLayoutAnimation() {

        Transition transition = new Fade(Visibility.MODE_IN);
        transition.setStartDelay(200);
        transition.setDuration(1000);
        TransitionManager.beginDelayedTransition(binding.bottomLayout, transition);
        binding.viewBottomCorner.setVisibility(View.VISIBLE);
        binding.viewBottomCornerIcon.setVisibility(View.VISIBLE);

    }

    private void cornerIconAnimation() {

        Transition transition = new Fade(Visibility.MODE_IN);
        transition.setStartDelay(200);
        transition.setDuration(1000);
        TransitionManager.beginDelayedTransition(binding.bottomLayout, transition);
        binding.viewBottomCornerIcon.setVisibility(View.VISIBLE);

    }

    int progressInt = 1;
    Handler mHandler = new Handler();

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.progressBar) {

            binding.tvDownloaded.setVisibility(View.VISIBLE);
            binding.tvDownload.setVisibility(View.GONE);
            bottomLayoutAnimation();
            runnable.run();

        } else if (v.getId() == R.id.view_play) {

            MyBottomSheet bottomSheet = new MyBottomSheet(this);
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());

        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                updateProgress();
            } catch (Exception ignored) {

            } finally {
                mHandler.postDelayed(runnable, progressInt);
            }
        }
    };

    boolean temp = false;
    boolean tempPlay = false;

    private void updateProgress() {
        if (progressInt >= 70) {
            progressInt += 3;
            if (!temp) {
                binding.viewBottomCornerIcon.setVisibility(View.GONE);
                binding.viewBottomCornerIcon.setBackground(getDrawable(R.drawable.ic_done_black_24dp));
                cornerIconAnimation();
                temp = true;
            }
        } else {
            progressInt += 1;
        }
        if (progressInt > 100) {
            mHandler.removeCallbacks(runnable);
            if (!tempPlay) {
                playButtonAnimation();
                tempPlay = true;
            }

        } else {
            binding.progressBar.setProgress(progressInt);
        }
    }

    private void playButtonAnimation() {

        binding.container.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {

                binding.bottomLayout.setVisibility(View.INVISIBLE);

                binding.container.getViewTreeObserver().removeOnPreDrawListener(this);
                Transition transition = new Slide(Gravity.BOTTOM);
                transition.setDuration(500);
                TransitionManager.beginDelayedTransition(binding.container, transition);
                binding.viewPlay.setVisibility(View.VISIBLE);
                return true;

            }
        });
    }

    @Override
    public void onItemClick(View view) {

    }
}


