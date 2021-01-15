package com.example.inventory.iu.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.inventory.R;

public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_preferences);
        initPreferenceAccount();
        // Se quiere recoger el evento onSharedPreferenceChanged cuando la preferencia lista
        // cambie
        onSharedPreferenceChanged(PreferenceManager.getDefaultSharedPreferences(getContext()), getString(R.string.key_ringtone_notification));
    }

    private void initPreferenceAccount() {
        Preference accountPreferences = getPreferenceManager().findPreference(getString(R.string.key_account));

        accountPreferences.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                NavHostFragment.findNavController(SettingFragment.this).navigate(R.id.action_settingFragment_to_accountFragment);
                return true;
            }
        });
    }

    // Como el Fragment no gestiona las preferencias, solo se guarda el primer elemento seleccionado de la lista,
    // que es al momento de crear el fragment,
    // por lo que hay que escribir esa gestion en onResume y onPause.
    @Override
    public void onResume() {
        super.onResume();
        // Se registra el listener en el PreferenceManager
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Eliminamos el registro del listener en PreferenceManager
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // key => el atributo name del preference.
        Preference preference = findPreference(key);

        if(key.equals(getString(R.string.key_ringtone_notification))) {
            //La preferencia que ha lanzado el evento, es la lista, por tanto, podemos hacer un downcasting
            ListPreference listPreference = (ListPreference) preference;

            //Vamos a obtener el indice del valor seleccionado
            int index = listPreference.findIndexOfValue(sharedPreferences.getString(key,""));
            //Vemos si el valor es mayor a cero y por tanto existe el valor y se modifica el summary
            if(index >= 0)
            {
                preference.setSummary(listPreference.getEntries()[index]);
            }
            else
                preference.setSummary(sharedPreferences.getString(key,""));
        }
    }
}
