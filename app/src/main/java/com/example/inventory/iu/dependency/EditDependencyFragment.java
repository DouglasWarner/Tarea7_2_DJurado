package com.example.inventory.iu.dependency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class EditDependencyFragment extends Fragment {

    private TextInputEditText tieNombre;
    private TextInputEditText tieNombreCorto;
    private TextInputEditText tieDescription;
    private TextInputEditText tieUrlImagne;
    private ImageButton btnEditDependency;
    private Dependency dependency;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_dependency, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tieNombre = view.findViewById(R.id.tieNombreDependency);
        tieNombreCorto = view.findViewById(R.id.tieNombreCortoDependency);
        tieDescription = view.findViewById(R.id.tieDescripcionDependency);
        tieUrlImagne = view.findViewById(R.id.tieUrlImagenDependency);
        btnEditDependency = view.findViewById(R.id.imgbtnGuardar);

        Bundle bundle = getArguments();
        dependency = (Dependency)bundle.getSerializable("dependency");

        tieNombre.setText(dependency.getName());
        tieNombreCorto.setText(dependency.getShortName());
        tieDescription.setText(dependency.getDescription());
        tieUrlImagne.setText(dependency.getUrlImage());

        btnEditDependency.setOnClickListener(v -> {
            SaveDependency(tieNombre.getText().toString(), tieNombreCorto.getText().toString(), tieDescription.getText().toString(), tieUrlImagne.getText().toString());
            toListDependecy();
        });

    }

    private void toListDependecy() {
        NavHostFragment.findNavController(this).navigate(R.id.action_editDependencyFragment_to_listDependencyFragment);
    }

    private void SaveDependency(String nombre, String nombreCorto, String description, String urlImagen)
    {
        List<Dependency> list = DependencyRepository.getInstance().getList();
        Dependency editDependency = new Dependency(nombre, nombreCorto, description, urlImagen);

        list.set(list.indexOf(dependency), editDependency);
    }
}