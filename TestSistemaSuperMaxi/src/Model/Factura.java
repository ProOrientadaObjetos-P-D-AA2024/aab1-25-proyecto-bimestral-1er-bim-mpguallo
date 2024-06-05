
package Model;

// Importa las clases Cliente y Producto del paquete Controller
import Controller.Cliente;
import Controller.Producto;
import java.io.Serializable; // Importa la interfaz Serializable para permitir la serialización de objetos
import java.util.ArrayList; // Importa la clase ArrayList para manejar listas dinámicas
import java.util.List; // Importa la interfaz List para manejar listas

// Declaración de la clase Factura que implementa la interfaz Serializable
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L; // Identificador único de la versión serializada de la clase

    private Cliente cliente; // Cliente asociado a la factura
    private List<Producto> productos; // Lista de productos incluidos en la factura
    private double total; // Total de la factura
    private double totalImpuestos; // Total de impuestos aplicados a la factura

    // Constructor de la clase Factura que recibe un cliente como parámetro
    public Factura(Cliente cliente) {
        this.cliente = cliente; // Asigna el cliente recibido al cliente de la factura
        this.productos = new ArrayList<>(); // Inicializa la lista de productos como un ArrayList vacío
        this.total = 0.0; // Inicializa el total de la factura en 0
        this.totalImpuestos = 0.0; // Inicializa el total de impuestos en 0
    }

    // Método para agregar un producto a la factura
    public void agregarProducto(Producto producto) {
        productos.add(producto); // Agrega el producto a la lista de productos
        total += producto.getPrecioFinal(); // Aumenta el total de la factura sumando el precio final del producto
        totalImpuestos += producto.getPrecioFinal() * 0.15; // Calcula los impuestos aplicados al producto y los suma al total de impuestos
    }

    // Método para obtener el total de la factura
    public double getTotal() {
        return total; // Retorna el total de la factura
    }

    // Método para obtener el total de impuestos aplicados a la factura
    public double getTotalImpuestos() {
        return totalImpuestos; // Retorna el total de impuestos
    }

    // Método para obtener el total de la factura incluyendo impuestos
    public double getTotalConImpuestos() {
        return total + totalImpuestos; // Retorna el total de la factura sumando los impuestos
    }

    // Método para obtener la lista de productos de la factura
    public List<Producto> getProductos() {
        return productos; // Retorna la lista de productos
    }

    // Método para generar una representación en forma de cadena de la factura
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Crea un StringBuilder para construir la representación de la factura

        // Agrega la cabecera de la factura
        sb.append("\n-Factura Generada");
        sb.append("\n|-------------------------------------|\n");
        sb.append("|             Supermaxi               |\n");
        sb.append("|               Loja                  |\n");
        sb.append("|                                     |\n");
        sb.append("|               FACTURA               |\n");
        sb.append("|                                     |\n");
        sb.append("| No: 002-002-0000000815              |\n");
        sb.append("| NUMERO DE AUTORIZACION              |\n");
        sb.append("| 0000512034600123113020              |\n");
        sb.append("|                                     |\n");
        sb.append("| FECHA DE AUTORIZACION               |\n");
        sb.append("| 2023-12-16                          |\n");
        sb.append("|                                     |\n");
        sb.append("| EMISION: NORMAL                     |\n");
        sb.append("| CLAVE DE ACCESO                     |\n");
        sb.append("| 0501254052504632879051              |\n");
        sb.append("|-------------------------------------|\n");

        // Agrega la información del cliente
        sb.append("| Información del Cliente:            |\n"); // Cabecera de la sección de información del cliente
        sb.append("| Nombre y Apellidos: ").append(cliente.getNombre()).append("|\n"); // Nombre y apellidos del cliente
        sb.append("| Cédula: ").append(cliente.getRuc_cedula()).append("      |\n"); // Número de cédula del cliente
        sb.append("| Direccion: ").append(cliente.getDireccion()).append("            |\n"); // Dirección del cliente
        sb.append("| Teléfono: ").append(cliente.getTelefono()).append("             |\n"); // Teléfono del cliente
        sb.append("|-------------------------------------|\n"); // Línea separadora

        // Agrega la tabla de productos
        sb.append("| Descripción               Precio    |\n");
        sb.append("|-------------------------------------|\n");
        for (Producto prod : productos) {
            sb.append("| ").append(String.format("%-25s $%.2f", prod.getNombreProducto(), prod.getPrecioFinal())).append(" |\n");
        }
        sb.append("|-------------------------------------|\n");

        // Agrega los totales de la factura
        sb.append(String.format("| Total:                     $%.2f    |\n", total));
        sb.append(String.format("| Total Impuestos:           $%.2f    |\n", totalImpuestos));
        sb.append(String.format("| Total con Impuestos:      $%.2f    |\n", getTotalConImpuestos()));
        sb.append("|-------------------------------------|\n");

        return sb.toString(); // Retorna la representación de la factura como una cadena
    }
}
