# Inventory_DJurado
### Dia 1
+ Truco para modificar una imagen para la adaptacion de los pixeles de los diferentes dispositivos. Desde este enlace [Enlace](https://romannurik.github.io/AndroidAssetStudio/) podemos modificar el tamaño de la imagen.
   * Tambien podemos utilizar el entorno, es decir crear un vector asset y pasarle una imagen con formato .svg
+ Completado la logica y la interfaz del Splash. El Splash solo nos interesa ejecutarlo una vez al iniciar la aplicación, por lo que si pulsamos el boton back no deberiamos de volver al SplashActivity. Esto se soluciona utilizando el metodo finish().
+ Explicado las caracteristicas del layout FrameLayout y conceptos de las herencias de todo layout.
+ Introducido un TextInputEditText para user y password.
+ Explicado los conceptos del peso, gravity de un layout y un elemento.
+ "Terminado" la interfaz del login

#### ConstraintLayout
* Si añadimos GuideLine hay que tener cuidado con los atributo width:match_parent que genera automaticamente el entorno, porque le estamos diciendo que su ancho depende de GuideLine y no si su padre que seria ContraintLayout.
    * Establecemos width:match_parent a 0 dp.
+ Explicado el layout TableLayout (Es muy antiguo y puede que no se utilice).
+ Explicado la manera de utilizar el componente View con el que se puede hacer muchas cosas.
+ Explicado el modelo MODELO-PRESENTADOR
    * Ha de crearse un repositorio (la clase que se encargara de todas las acciones que tenga que ver con la base de datos) por cada clase modelo si es necesario.
    * Ejemplo --> class User necesitara class UserRepository. Que es el que comprobara si el usuario existe, conectandose a la base de datos para comprobarlo
