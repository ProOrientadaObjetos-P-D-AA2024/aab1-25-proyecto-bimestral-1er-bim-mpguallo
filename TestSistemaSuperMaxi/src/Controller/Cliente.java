
package Controller; 

public class Cliente { // Define la clase Cliente
    private String nombre; // Declara una variable para almacenar el nombre del cliente
    private String ruc_cedula; // Declara una variable para almacenar el número de RUC o cédula del cliente
    private String direccion; // Declara una variable para almacenar la dirección del cliente
    private String telefono; // Declara una variable para almacenar el número de teléfono del cliente

    public Cliente(String nombre, String ruc_cedula, String direccion, String telefono) { // Constructor de la clase Cliente
        this.nombre = nombre; // Inicializa el nombre del cliente con el valor proporcionado
        this.ruc_cedula = ruc_cedula; // Inicializa el número de RUC o cédula del cliente con el valor proporcionado
        this.direccion = direccion; // Inicializa la dirección del cliente con el valor proporcionado
        this.telefono = telefono; // Inicializa el número de teléfono del cliente con el valor proporcionado
    }

    public String getNombre() { // Método para obtener el nombre del cliente
        return nombre; // Devuelve el nombre del cliente
    }

    public String getRuc_cedula() { // Método para obtener el número de RUC o cédula del cliente
        return ruc_cedula; // Devuelve el número de RUC o cédula del cliente
    }

    public String getDireccion() { // Método para obtener la dirección del cliente
        return direccion; // Devuelve la dirección del cliente
    }

    public String getTelefono() { // Método para obtener el número de teléfono del cliente
        return telefono; // Devuelve el número de teléfono del cliente
    }

    @Override
    public String toString() { // Método para representar el objeto como una cadena de texto
        return "\n|-Nombre=" + nombre + ", \n|-Cedula=" + ruc_cedula + ", \n|-Direccion=" + direccion + ", \n|-Telefono=" + telefono; // Devuelve una cadena formateada con la información del cliente
    }
}