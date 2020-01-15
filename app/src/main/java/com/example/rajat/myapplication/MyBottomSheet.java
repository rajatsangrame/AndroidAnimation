package com.example.rajat.myapplication;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.rajat.myapplication.databinding.FragmentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Created by Rajat Sangrame on 15/1/20.
 * http://github.com/rajatsangrame
 */
public class MyBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {

    private OnItemClickListener listener;
    private FragmentBottomSheetBinding binding;


    public MyBottomSheet() {
    }

    public MyBottomSheet(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false);
        binding.viewNext.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.mainContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                binding.mainContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                startAnimate0();
                return true;
            }
        });


        binding.containerOne.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                binding.containerOne.getViewTreeObserver().removeOnPreDrawListener(this);
                startAnimate1();
                return true;
            }
        });

    }


    int nextCount = 0;

    @Override
    public void onClick(View view) {

        if (nextCount > 0) {
            return;
        }
        if (view.getId() == R.id.view_next) {

            nextCount++;

            binding.containerOne.setVisibility(View.GONE);
            binding.containerTwo.setVisibility(View.VISIBLE);

            binding.viewT1.setVisibility(View.GONE);

            binding.mainContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    binding.mainContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                    startAnimate0();
                    return true;
                }
            });

            binding.linearLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    binding.linearLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                    startAnimate21();
                    return true;
                }
            });

            binding.containerTwo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    binding.containerTwo.getViewTreeObserver().removeOnPreDrawListener(this);
                    startAnimate22();
                    return true;
                }
            });

        }

        listener.onItemClick(view);
    }

    private void startAnimate0() {

        Transition transition = new Fade(Visibility.MODE_IN);
        transition.setStartDelay(200);
        transition.setDuration(500);
        TransitionManager.beginDelayedTransition(binding.mainContainer, transition);
        binding.viewT1.setVisibility(View.VISIBLE);
    }

    private void startAnimate1() {

        Transition transition = new Slide(Gravity.END);
        transition.setDuration(500);
        TransitionManager.beginDelayedTransition(binding.containerOne, transition);

        binding.view11.setVisibility(View.VISIBLE);
        binding.view12.setVisibility(View.VISIBLE);
        binding.view14.setVisibility(View.VISIBLE);
        binding.view13.setVisibility(View.VISIBLE);
        binding.veiwRound2.setVisibility(View.VISIBLE);
        binding.viewRound1.setVisibility(View.VISIBLE);

    }


    private void startAnimate21() {

        Transition transition = new Slide(Gravity.END);
        transition.setDuration(500);
        TransitionManager.beginDelayedTransition(binding.linearLayout, transition);

        binding.view22.setVisibility(View.VISIBLE);
        binding.view23.setVisibility(View.VISIBLE);
        binding.view24.setVisibility(View.VISIBLE);

    }

    private void startAnimate22() {

        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(500);
        TransitionManager.beginDelayedTransition(binding.containerTwo, transition);

        binding.view26.setVisibility(View.VISIBLE);
        binding.view27.setVisibility(View.VISIBLE);
        binding.view25.setVisibility(View.VISIBLE);
        binding.tvWallet.setVisibility(View.VISIBLE);

    }
}
