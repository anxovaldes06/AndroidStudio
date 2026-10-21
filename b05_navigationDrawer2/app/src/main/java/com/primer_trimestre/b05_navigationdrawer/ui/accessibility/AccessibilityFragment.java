package com.primer_trimestre.b05_navigationdrawer.ui.accessibility;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.primer_trimestre.b05_navigationdrawer.databinding.FragmentAccessibilityBinding;

public class AccessibilityFragment extends Fragment {

    private FragmentAccessibilityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccessibilityViewModel accessibilityViewModel =
                new ViewModelProvider(this).get(AccessibilityViewModel.class);

        binding = FragmentAccessibilityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAccessibility;
        accessibilityViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}