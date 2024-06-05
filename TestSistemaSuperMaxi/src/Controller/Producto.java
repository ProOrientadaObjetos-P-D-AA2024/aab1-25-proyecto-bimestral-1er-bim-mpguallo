
package Controller;

import java.io.Serializable; // Importa la interfaz Serializable del paquete java.io para permitir la serialización de objetos
import java.time.LocalDate; // Importa la clase LocalDate del paquete java.time para manejar fechas
import java.time.temporal.ChronoUnit; // Importa la clase ChronoUnit del paquete java.time.temporal para cálculos de tiempo

public class Producto implements Serializable { // Define la clase Producto, que implementa la interfaz Serializable para permitir la serialización de objetos
    private static final long serialVersionUID = 1L; // Identificador único para la versión serializada de la clase

    private String nombreProducto; // Declara una variable de instancia para almacenar el nombre del producto
    private LocalDate fechaCaducidad; // Declara una variable de instancia para almacenar la fecha de caducidad del producto
    private double precio; // Declara una variable de instancia para almacenar el precio del producto
    private double precioPromo; // Declara una variable de instancia para almacenar el precio promocional del producto
    private int stock; // Declara una variable de instancia para almacenar el stock del producto
    private CategoriaProducto categoria; // Declara una variable de instancia para almacenar la categoría del producto

    public Producto(String nombreProducto, LocalDate fechaCaducidad, double precio, double precioPromo, int stock, CategoriaProducto categoria) { // Define el constructor de la clase Producto, que recibe varios parámetros para inicializar los atributos
        this.nombreProducto = nombreProducto; // Inicializa el nombre del producto con el valor proporcionado
        this.fechaCaducidad = fechaCaducidad; // Inicializa la fecha de caducidad con el valor proporcionado
        this.precio = precio; // Inicializa el precio con el valor proporcionado
        this.precioPromo = precioPromo; // Inicializa el precio promocional con el valor proporcionado
        this.stock = stock; // Inicializa el stock con el valor proporcionado
        this.categoria = categoria; // Inicializa la categoría con el valor proporcionado
    }

    public String getNombreProducto() { // Método para obtener el nombre del producto
        return nombreProducto; // Devuelve el nombre del producto
    }

    double getPrecio() { // Método (modificador de acceso de paquete) para obtener el precio del producto
        return precio; // Devuelve el precio del producto
    }

    public double getPrecioPromocional() { // Método para obtener el precio promocional del producto
        return precioPromo; // Devuelve el precio promocional del producto
    }

    public int getStock() { // Método para obtener el stock del producto
        return stock; // Devuelve el stock del producto
    }

    public CategoriaProducto getCategoria() { // Método para obtener la categoría del producto
        return categoria; // Devuelve la categoría del producto
    }

    public void reducirStock(int cantidad) { // Método para reducir el stock del producto en una cantidad dada
        if (cantidad <= stock) { // Verifica si la cantidad a reducir es menor o igual al stock disponible
            this.stock -= cantidad; // Reduce el stock en la cantidad especificada
        } else {
            System.out.println("Cantidad a reducir excede el stock disponible."); // Muestra un mensaje de advertencia si la cantidad a reducir es mayor que el stock disponible
        }
    }

    public boolean estaEnPromocion() { // Método para verificar si el producto está en promoción
        long diasParaCaducar = ChronoUnit.DAYS.between(LocalDate.now(), fechaCaducidad); // Calcula los días restantes para la caducidad del producto
        return stock > 100 || diasParaCaducar < 7; // Retorna true si el stock es mayor que 100 o si quedan menos de 7 días para la caducidad
    }

    public double getPrecioFinal() { // Método para obtener el precio final del producto (teniendo en cuenta si está en promoción)
        return estaEnPromocion() ? precioPromo : precio; // Retorna el precio promocional si el producto está en promoción, de lo contrario retorna el precio normal
    }

    @Override
    public String toString() { // Método toString para representar el objeto Producto como una cadena de texto
        return "Producto " // Retorna una cadena que representa la información del producto
                + "\n  Nombre del producto: " + nombreProducto // Agrega el nombre del producto
                + "\n  Fecha de caducidad: " + fechaCaducidad // Agrega la fecha de caducidad del producto
                + "\n  Precio: " + precio // Agrega el precio del producto
                + "\n  Precio promocional: " + precioPromo // Agrega el precio promocional del producto
                + "\n  Stock: " + stock // Agrega el stock del producto
                + "\n  Categoría: " + categoria // Agrega la categoría del producto
                + "\n"; // Agrega un salto de línea al final
    }
}
