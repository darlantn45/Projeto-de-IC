package br.edu.utfpr.geladeira_v5.ui.prescricao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.edu.utfpr.geladeira_v5.R;

public class PrescricaoFragment extends Fragment {

    private PrescricaoViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(PrescricaoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_antropometrico, container, false);
        final TextView textView = root.findViewById(R.id.tv_github_search_results_json);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}