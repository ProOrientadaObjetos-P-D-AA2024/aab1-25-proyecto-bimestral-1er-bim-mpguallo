
package View;

// Importa las clases y paquetes necesarios del controlador y modelo
import Controller.*;
import Model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define la clase Program_Sistem_Super
public class Program_Sistem_Super {

    // Declara una instancia de GestorProducto y EstadisticasVentas
    private static GestorProducto gestorProducto = new GestorProducto();
    private static EstadisticasVentas estadisticasVentas = new EstadisticasVentas();

    // Método principal del programa
    public static void main(String[] args) {
        Scanner tc = new Scanner(System.in);
        // Imprime el título del programa
        System.out.println("    =======================================================");
        System.out.println("                 'SISTEMA SUPERMAXI LOJA'");
        System.out.println("    =======================================================\n");

        // Bucle principal del menú
        OUTER:
        while (true) {
            // Imprime el menú principal
            System.out.println("\n======================== MENÚ PRINCIPAL ============================");
            System.out.println("|   Opción   |                     Descripción                     |");
            System.out.println("|------------|-----------------------------------------------------|");
            System.out.println("|     1      |        Agregar nuevo producto                       |");
            System.out.println("|     2      |        Mostrar todos los productos                  |");
            System.out.println("|     3      |        Crear nueva factura                          |");
            System.out.println("|     4      |        Mostrar estadísticas de ventas               |");
            System.out.println("|     5      |        Salir                                        |");
            System.out.println("====================================================================\n");
            System.out.print("- Seleccione una opción: ");
            // Lee la opción seleccionada por el usuario
            int opcion = tc.nextInt();
            tc.nextLine(); // Consumir el salto de línea

            // Switch para manejar las diferentes opciones del menú
            switch (opcion) {
                case 1:
                    // Opción para agregar un nuevo producto
                    System.out.println("\n========== AGREGAR PRODUCTO ==========");
                    // Solicita los detalles del nuevo producto al usuario
                    System.out.print("Nombre del producto: ");
                    String nombre = tc.nextLine();
                    System.out.print("Precio normal: ");
                    double precioNormal = tc.nextDouble();
                    System.out.print("Precio promocional: ");
                    double precioPromocional = tc.nextDouble();
                    System.out.print("Cantidad en stock: ");
                    int cantidadEnStock = tc.nextInt();
                    tc.nextLine(); // Consumir el salto de línea
                    System.out.print("Fecha de caducidad (yyyy-MM-dd): ");
                    String fechaCaducidadStr = tc.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fechaCaducidad = LocalDate.parse(fechaCaducidadStr, formatter);
                    System.out.println("Categoría (1: Vivienda, 2: Educación, 3: Alimentación, 4: Vestimenta, 5: Salud): ");
                    int categoriaInt = tc.nextInt();
                    tc.nextLine(); // Consumir el salto de línea
                    CategoriaProducto categoria = CategoriaProducto.values()[categoriaInt - 1];
                    // Crea un nuevo producto y lo agrega al gestor de productos
                    Producto producto = new Producto(nombre, fechaCaducidad, precioNormal, precioPromocional, cantidadEnStock, categoria);
                    gestorProducto.agregarProducto(producto);
                    System.out.println("\nProducto agregado con éxito.");
                    break;
                case 2:
                    // Opción para mostrar todos los productos disponibles
                    System.out.println("\n========== LISTA DE PRODUCTOS ==========");
                    if (gestorProducto.getProductos().isEmpty()) {
                        System.out.println("No hay productos disponibles.");
                    } else {
                        // Muestra los detalles de cada producto en el gestor de productos
                        for (Producto prod : gestorProducto.getProductos()) {
                            System.out.println(prod);
                        }
                    }
                    break;
                case 3:
                    // Opción para crear una nueva factura
                    System.out.println("\n=================== CREAR NUEVA FACTURA ====================");
                    // Solicita los detalles del cliente al usuario
                    System.out.print("| Nombre del cliente: ");
                    String nombreUsuario = tc.nextLine();
                    System.out.print("| Cédula: ");
                    String ruc_cedula = tc.nextLine();
                    System.out.print("| Dirección: ");
                    String direccion = tc.nextLine();
                    System.out.print("| Teléfono: ");
                    String telefono = tc.nextLine();
                    // Crea un nuevo cliente y factura con los detalles ingresados
                    Cliente cliente = new Cliente(nombreUsuario, ruc_cedula, direccion, telefono);
                    Factura factura = new Factura(cliente);
                    // Bucle para agregar productos a la factura
                    while (true) {
                        // Muestra los productos disponibles y solicita al usuario que seleccione uno
                        System.out.println("\nProductos disponibles:");
                        // Muestra una lista numerada de los productos disponibles
                        System.out.println("|----------------------------------------------------------|");
                        System.out.println("|   N°   |       Producto            |    Precio (Stock)   |");
                        System.out.println("|----------------------------------------------------------|");
                        for (int i = 0; i < gestorProducto.getProductos().size(); i++) {
                            Producto prod = gestorProducto.getProductos().get(i);
                            System.out.printf("|   %-3d |  %-25s |   %.2f (%-3d)        |\n", (i + 1), prod.getNombreProducto(), prod.getPrecioFinal(), prod.getStock());
                        }
                        System.out.println("|----------------------------------------------------------|");
                        System.out.print("Seleccione un producto por número o 0 para terminar: ");
                        int productoSeleccionado = tc.nextInt();
                        tc.nextLine();
                        // Si el usuario selecciona 0, termina la selección de productos
                        if (productoSeleccionado == 0) {
                            break;
                        }
                        // Si el usuario selecciona un número válido, agrega el producto a la factura
                        Producto productoSeleccionadoObj = gestorProducto.getProductos().get(productoSeleccionado - 1);
                        if (productoSeleccionadoObj.getStock() > 0) {
                            productoSeleccionadoObj.reducirStock(1);
                            if (productoSeleccionadoObj.getStock() == 0) {
                                gestorProducto.eliminarProducto(productoSeleccionadoObj);
                            }
                            factura.agregarProducto(productoSeleccionadoObj);
                            estadisticasVentas.registrarVenta(productoSeleccionadoObj);
                            System.out.println("Producto agregado al carrito.");
                        } else {
                            System.out.println("Stock insuficiente para el producto seleccionado.");
                        }
                    }
                    // Imprime la factura generada
                    System.out.println(factura);
                    break;
                case 4:
                    // Opción para mostrar estadísticas de ventas
                    System.out.println("\n           ========== ESTADÍSTICAS DE VENTAS ==========");
                    estadisticasVentas.mostrarEstadisticas();
                    break;
                case 5:
                    // Opción para salir del programa
                    System.out.println("\nGracias por usar el sistema SuperMaxi. ¡Que tengas un excelente dia :)!");
                    tc.close();
                    System.exit(0);
                default:
                    // Mensaje de error si se ingresa una opción inválida
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }
}
