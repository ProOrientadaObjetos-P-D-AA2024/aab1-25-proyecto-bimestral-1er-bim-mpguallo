package Controller;

import java.util.HashMap; /* Esta clase proporciona una implementación de la 
interfaz Map basada en una tabla hash. La tabla hash almacena los elementos en 
pares de clave-valor, y permite la inserción, búsqueda y eliminación de elementos en tiempo constante en promedio.*/
import java.util.Map; /* Esta es una interfaz que representa un mapeo entre una 
clave y un valor. Define los métodos que una clase que implementa esta interfaz debe proporcionar.*/

public class EstadisticasVentas { // Define la clase EstadisticasVentas

    private Map<String, Integer> ventasPorProducto; // Declara un mapa para almacenar las ventas por producto
    private Map<CategoriaProducto, Integer> ventasPorCategoria; // Declara un mapa para almacenar las ventas por categoría
    private Map<CategoriaProducto, Double> valorVentasPorCategoria; // Declara un mapa para almacenar el valor total de las ventas por categoría
    private double ventasTotales; // Almacena el total de las ventas

    public EstadisticasVentas() { // Constructor de la clase EstadisticasVentas
        ventasPorProducto = new HashMap<>(); // Inicializa el mapa de ventas por producto como un HashMap vacío
        ventasPorCategoria = new HashMap<>(); // Inicializa el mapa de ventas por categoría como un HashMap vacío
        valorVentasPorCategoria = new HashMap<>(); // Inicializa el mapa de valor de ventas por categoría como un HashMap vacío
        ventasTotales = 0.0; // Inicializa el total de las ventas como 0.0
    }

    public void registrarVenta(Producto producto) { // Método para registrar una venta de un producto
        ventasPorProducto.put(producto.getNombreProducto(), ventasPorProducto.getOrDefault(producto.getNombreProducto(), 0) + 1); // Registra la venta del producto y aumenta su contador
        ventasPorCategoria.put(producto.getCategoria(), ventasPorCategoria.getOrDefault(producto.getCategoria(), 0) + 1); // Registra la venta en la categoría correspondiente y aumenta su contador
        valorVentasPorCategoria.put(producto.getCategoria(), valorVentasPorCategoria.getOrDefault(producto.getCategoria(), 0.0) + producto.getPrecioFinal()); // Registra el valor de la venta en la categoría correspondiente
        ventasTotales += producto.getPrecioFinal(); // Aumenta el total de las ventas
    }

    public void mostrarEstadisticas() {
        // Imprime el encabezado de las estadísticas de ventas
        System.out.println("=======================================");
        System.out.println("         Estadísticas de Ventas        ");
        System.out.println("=======================================");

        // Imprime el total de las ventas
        System.out.println("Ventas Totales: $" + String.format("%.2f", ventasTotales));
        System.out.println("---------------------------------------");

        // Imprime las ventas por producto
        System.out.println("Ventas por Producto:");
        System.out.println("---------------------------------------");
        for (Map.Entry<String, Integer> entry : ventasPorProducto.entrySet()) {
            // Imprime el nombre del producto y la cantidad vendida
            System.out.println(String.format("%-20s: %d unidades", entry.getKey(), entry.getValue()));
        }
        System.out.println("---------------------------------------");

        // Imprime las ventas por categoría
        System.out.println("Ventas por Categoría:");
        System.out.println("---------------------------------------");
        for (Map.Entry<CategoriaProducto, Integer> entry : ventasPorCategoria.entrySet()) {
            // Imprime la categoría y la cantidad de ventas
            System.out.println(entry.getKey() + ": " + entry.getValue() + " ventas");
            System.out.println("  Valor total: $" + String.format("%.2f", valorVentasPorCategoria.get(entry.getKey())));
            System.out.println("---------------------------------------");
        }
    }
}
