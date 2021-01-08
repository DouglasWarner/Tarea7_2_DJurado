package com.example.inventory.data.repository;

import com.example.inventory.data.model.TypeUser;
import com.example.inventory.data.model.User;

import java.util.ArrayList;

/**
 * Esta clase sera accesible desde cualquier punto de la aplicacion.
 * Se accedera mediante el metodo estatico getInstance() que devielve
 * la instacnia del repositorio que seimpre se inicializa en un bloque
 * estatico
 */
public class UserRepository {
    /* Contructor privado donde se inicializaran las variables de objeto*/

    private static UserRepository repository;
    private User user;

    private ArrayList<User> users;
    private int id = 4;

    /**
     * Se inicializa las propiedade estaticas del repositorio en el bloque static
     */
    static {
        repository = new UserRepository();
    }

    /* Contructor */
    private UserRepository() {
        users = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        add(new User(1, "lourdes", "Lourdes18?", "Lourdes Rodriguez", "lourdes@iesportada.org", "/img/lourdes", TypeUser.MANAGER));
        add(new User(2, "dou", "Dou123456", "Jesus González", "jesus@iesportada.org", "/img/jesus", TypeUser.TECHNICAL));
        add(new User(3, "ana", "Ana20?", "Ana García", "ana@iesportada.org", "/img/angela", TypeUser.ADMIN));
    }

    private void add(User user) {
        users.add(user);
    }

    /**
     * Metodo que devuelve la instancia del objeto
     *
     * @return
     */
    public static UserRepository getInstance() {
        // No es necesario comprobar si es null porque el objeto porque el static {}
        // ya lo inicializa, por lo que solo deveremos devolverlo
        return repository;
    }

    /**
     * Metodo que valida si las crdenciales de un usuario en la lista del repositorio
     *
     * @param useraux
     * @param password
     * @return
     */
    public boolean validateCredentials(String useraux, String password) {
        for (User user : users) {
            if ((user.getUser().equals(useraux)) && (user.getPassword().equals(password)))
                return true;
        }
        return false;
    }

    public boolean userExists(String user) {

        for (User userTmp : users) {
            if (userTmp.getUser().equals(user))
                return true;
        }


        return false;
    }

    public void add(String user, String password, String email) {
        User userTemp = new User(id++, user, email, password, user, "/img", TypeUser.TECHNICAL);
        users.add(userTemp);
    }
}
