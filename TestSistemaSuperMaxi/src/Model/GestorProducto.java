// Define el paquete Model donde se encuentra la clase GestorProducto
package Model;

// Importa la clase Producto del paquete Controller
import Controller.Producto;
import java.io.*; // Importa todas las clases relacionadas con la entrada/salida de datos
import java.util.ArrayList; // Importa la clase ArrayList para manejar listas dinámicas
import java.util.List; // Importa la interfaz List para manejar listas

// Declaración de la clase GestorProducto
public class GestorProducto {
    private static final String FILE_NAME = "productos.dat"; // Define el nombre del archivo donde se guardarán los productos
    private List<Producto> productos; // Declara una lista de productos

    // Constructor de la clase GestorProducto
    public GestorProducto() {
        productos = new ArrayList<>(); // Inicializa la lista de productos como un ArrayList vacío
        cargarProductos(); // Carga los productos almacenados previamente
    }

    // Método para agregar un producto a la lista y guardarlo en el archivo
    public void agregarProducto(Producto producto) {
        productos.add(producto); // Agrega el producto a la lista
        guardarProductos(); // Guarda la lista actualizada en el archivo
    }

    // Método para eliminar un producto de la lista y actualizar el archivo
    public void eliminarProducto(Producto producto) {
        productos.remove(producto); // Elimina el producto de la lista
        guardarProductos(); // Guarda la lista actualizada en el archivo
    }

    // Método privado para guardar la lista de productos en el archivo
    private void guardarProductos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(productos); // Escribe la lista de productos en el archivo
        } catch (IOException e) {
            e.printStackTrace(); // Maneja cualquier excepción de entrada/salida imprimiendo el error
        }
    }

    // Método privado para cargar la lista de productos desde el archivo
    private void cargarProductos() {
        File file = new File(FILE_NAME); // Crea un objeto File con el nombre del archivo
        if (file.exists()) { // Verifica si el archivo existe
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                productos = (List<Producto>) ois.readObject(); // Lee la lista de productos desde el archivo
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace(); // Maneja cualquier excepción de entrada/salida o de clase no encontrada imprimiendo el error
            }
        }
    }

    // Método para obtener la lista de productos
    public List<Producto> getProductos() {
        return productos; // Retorna la lista de productos
    }
}