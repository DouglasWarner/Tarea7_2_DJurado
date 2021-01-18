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
import com.example.inventory.iu.base.BaseDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListDependencyFragment extends Fragment implements ListDependencyContract.View, DependencyAdapter.OnManagerDependencyListener, BaseDialogFragment.OnPositiveClickListener {

    private LinearLayout llLoading;
    private LinearLayout llNoData;
    private RecyclerView rvDependency;
    private DependencyAdapter adapter;
    private ListDependencyPresenter presenter;
    private Dependency deleted;

    private boolean reverseSort;

    public ListDependencyFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
        adapter = new DependencyAdapter(new ArrayList<>(), this);

//        onDeleteDependency(adapter.getDependencyItem(rvDependency.getChildAdapterPosition(v)));
//        Toast.makeText(view.getContext(), "Se ha pulsado en " + adapter.getDependencyItem(rvDependency.getChildAdapterPosition(v)).getName(), Toast.LENGTH_SHORT).show();

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

        if(getArguments() !=null)
            if(getArguments().getBoolean(BaseDialogFragment.CONFIRM_DELETE)) {
                presenter.delete(deleted);

            }
//                onPositiveClick();
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

    /**
     * Este metodo es el que se ejecuta cuando se ha eliminado correctamente una dependencia
     * de la base de datos y se muestra un Snackbar con la opcion UNDO
     */
    @Override
    public void onsuccessDeleted() {
        adapter.delete(deleted);
        showSnackBarDeleted();
    }

    private void showSnackBarDeleted() {
        Snackbar.make(getView(), getString(R.string.confirm_delete_dependency), Snackbar.LENGTH_SHORT)
                .setAction(getString(R.string.undo), v -> {
                    undoDeleted();
                }).show();
    }

    /**
     * Método de vuelve a insertar la dependencia borrada en la base de datos
     */
    private void undoDeleted() {

    }

    @Override
    public void onSuccess(List<Dependency> list) {
        // 1. Si esta visible NODATA se cambia visibilidad a GONE
        if(llNoData.getVisibility() == View.VISIBLE)
            llNoData.setVisibility(View.GONE);
        // 2. Se carga los datos en el Recycler
        adapter.update(list);
    }

    @Override
    public void onEditDependency(View v) {
        Dependency dependency = adapter.getDependencyItem(rvDependency.getChildAdapterPosition(v));
        Toast.makeText(getContext(), "Se ha pulsado en " + dependency.getName(), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("dependency", dependency);
        NavHostFragment.findNavController(this).navigate(R.id.action_listDependencyFragment_to_editDependencyFragment, bundle);
    }

    @Override
    public void onDeleteDependency(View v) {
        Dependency dependency = adapter.getDependencyItem(rvDependency.getChildAdapterPosition(v));

        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, getString(R.string.title_delete_dependency));
        bundle.putString(BaseDialogFragment.MESSAGE, String.format(getString(R.string.message_delete_dependency), dependency.getShortName()));
        deleted = dependency;
        NavHostFragment.findNavController(this).navigate(R.id.action_listDependencyFragment_to_baseDialogFragment, bundle);
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

    /**
     * Este metodo se ejecuta cuando el usuario ha pulsado el boton ACEPTAR
     * en el cuadro de dialogo que pide confirmación
     */
    @Override
    public void onPositiveClick() {
        Toast.makeText(getContext(), "Nunca se ejecuta", Toast.LENGTH_SHORT).show();
    }
}
