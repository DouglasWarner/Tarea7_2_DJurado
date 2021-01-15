package com.example.inventory.iu.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.inventory.R;

/**
 * Clase que gestiona el acceso a las preferencias de la aplicación
 */
public class InventoryPreferences {
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password";
    private Context context;
    private static InventoryPreferences instance;


    private InventoryPreferences (Context context)
    {
        this.context = context;
    }

    static public void newInstance(Context context)
    {
        if(instance==null)
          instance = new InventoryPreferences(context);
    }

    static public InventoryPreferences getInstance()
    {
        return instance;
    }

    /**
     * Metodo que escribe en el fichero de preferencias la informacion del usuario
     * @param user
     * @param password
     * @return true si se realizo correctamente
     */
    public boolean putUser(String user, String password){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(context.getResources().getString(R.string.key_user), user);
        editor.putString(context.getResources().getString(R.string.key_password), password);
        editor.commit();

        return true;
    }
}
