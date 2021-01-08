package com.example.inventory.iu.dependency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.iu.adapter.DependencyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListDependencyFragment extends Fragment implements ListDependencyContract.View {

    private LinearLayout llLoading;
    private LinearLayout llNoData;
    private RecyclerView rvDependency;
    private DependencyAdapter adapter;
    private ListDependencyPresenter presenter;

    private boolean reverseSort;

    public ListDependencyFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_list_dependency, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llLoading = view.findViewById(R.id.llLoading);
        llNoData = view.findViewById(R.id.llNoData);
        rvDependency = view.findViewById(R.id.rvDependency);

        // 1. Crear adapter
        adapter = new DependencyAdapter(new ArrayList<>(), new DependencyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Se ha pulsado en " + adapter.getDependencyItem(rvDependency.getChildAdapterPosition(v)).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // 2. Crear el diseño del RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        // 3. Se asigna el diseño al RecyclerView
        rvDependency.setLayoutManager(layoutManager);
        // 4. Vincula la vista al modelo
        rvDependency.setAdapter(adapter);

        presenter = new ListDependencyPresenter(this);

        reverseSort = false;

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void setNoData() {
        llNoData.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo que muestra el LinearLayout que contiene el progressbar
     */
    @Override
    public void showProgress() {
        llLoading.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        llLoading.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<Dependency> list) {
        // 1. Si esta visible NODATA se cambia visibilidad a GONE
        if(llNoData.getVisibility() == View.VISIBLE)
            llNoData.setVisibility(View.GONE);
        // 2. Se carga los datos en el Recycler
        adapter.update(list);
    }

    public void toEditDependency(Dependency dependency)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable("dependency", dependency);
        NavHostFragment.findNavController(this).navigate(R.id.action_listDependencyFragment_to_editDependencyFragment, bundle);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_by_shortname:
                //TODO hacer que ordene
                adapter.SortByShortName(reverseSort);
                reverseSort = !reverseSort;
                return true;
            case R.id.action_sort_by_name:
                //TODO hacer que ordene
                adapter.SortByName(reverseSort);
                reverseSort = !reverseSort;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_list_dependency, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
