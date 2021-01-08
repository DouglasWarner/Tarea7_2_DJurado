package com.example.inventory.iu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.inventory.R;
import com.example.inventory.iu.dependency.ListDependencyFragment;
import com.example.inventory.iu.dependency.ListDependencyPresenter;

public class DashBoardFragment extends Fragment {

    private ImageButton buttonListDependency;
    private ImageButton buttonSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonListDependency = view.findViewById(R.id.btDependencia);
        buttonListDependency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListDependency();
            }
        });

        buttonSettings = view.findViewById(R.id.btAjustes);

        buttonSettings.setOnClickListener(this::onClick);

    }


    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btAjustes:
                showPreference();
                break;
        }
    }

    public void onClickListDependency()
    {
        NavHostFragment.findNavController(this).navigate(R.id.action_dashBoardFragment_to_listDependencyFragment);
    }

    private void showPreference()
    {
        NavHostFragment.findNavController(this).navigate(R.id.action_dashBoardFragment_to_settingFragment, null);
    }
}