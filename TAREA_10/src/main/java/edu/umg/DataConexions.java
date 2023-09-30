//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.umg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DataConexions {
    private Connection conn = null;

    public DataConexions() {
    }

    private void conexion() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tarea10", "root", "1234");
            System.out.println("Conexion Exitosa");
        } catch (SQLException var2) {
            System.out.println("mataron gojo Lois: " + var2.getMessage());
        }

    }

    public void leerDatos() throws SQLException {
        this.conexion();
        String query = "SELECT * FROM personas";
        PreparedStatement ps = null;

        try {
            ps = this.conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombreApellido = rs.getString("nombre_apellido");
                String fechaRegistro = rs.getString("fecha_registro");
                double sueldoBase = rs.getDouble("sueldo_base");
                String sexo = rs.getString("sexo");
                int edad = rs.getInt("edad");
                double longitud = rs.getDouble("longitud");
                double latitud = rs.getDouble("latitud");
                String comentarios = rs.getString("comentarios");
                System.out.println("Código: " + codigo);
                System.out.println("Nombre y Apellido: " + nombreApellido);
                System.out.println("Fecha de Registro: " + fechaRegistro);
                System.out.println("Sueldo Base: " + sueldoBase);
                System.out.println("Sexo: " + sexo);
                System.out.println("Edad: " + edad);
                System.out.println("Longitud: " + longitud);
                System.out.println("Latitud: " + latitud);
                System.out.println("Comentarios: " + comentarios);
                System.out.println();
            }
        } catch (SQLException var19) {
            var19.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }

        }

    }

    public void insertarDatos() throws SQLException {
        this.conexion();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un nombre:");
        String nombre = sc.nextLine();
        System.out.println("Ingresa un apellido:");
        String apellido = sc.nextLine();
        System.out.println("Ingresa la fecha de registro (yyyy-MM-dd):");
        String fechaRegistro = sc.nextLine();
        System.out.println("Ingresa el sueldo base:");
        double sueldoBase = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa el sexo (M/F):");
        String sexo = sc.nextLine();
        System.out.println("Ingresa la edad:");
        int edad = sc.nextInt();
        System.out.println("Ingresa la longitud de su casa:");
        double longitud = sc.nextDouble();
        System.out.println("Ingresa la latitud de su casa:");
        double latitud = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa comentarios:");
        String comentarios = sc.nextLine();
        String query = "INSERT INTO personas (nombre_apellido, fecha_registro, sueldo_base, sexo, edad, longitud, latitud, comentarios) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = this.conn.prepareStatement(query);
            ps.setString(1, nombre + " " + apellido);
            ps.setString(2, fechaRegistro);
            ps.setDouble(3, sueldoBase);
            ps.setString(4, sexo);
            ps.setInt(5, edad);
            ps.setDouble(6, longitud);
            ps.setDouble(7, latitud);
            ps.setString(8, comentarios);
            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Datos insertados correctamente.");
            } else {
                System.out.println("No se pudo insertar ningún dato.");
            }
        } catch (SQLException var20) {
            var20.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }

        }

    }

    public void actualizarDatos() throws SQLException {
        this.conexion();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un código de persona para actualizar:");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingresa un nuevo nombre:");
        String nuevoNombre = sc.nextLine();
        System.out.println("Ingresa un nuevo apellido:");
        String nuevoApellido = sc.nextLine();
        System.out.println("Ingresa una nueva fecha de registro (yyyy-MM-dd):");
        String nuevaFechaRegistro = sc.nextLine();
        System.out.println("Ingresa un nuevo sueldo base:");
        double nuevoSueldoBase = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa un nuevo sexo (M/F):");
        String nuevoSexo = sc.nextLine();
        System.out.println("Ingresa una nueva edad:");
        int nuevaEdad = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingresa una nueva longitud de casa:");
        double nuevaLongitud = sc.nextDouble();
        System.out.println("Ingresa una nueva latitud de casa:");
        double nuevaLatitud = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa nuevos comentarios:");
        String nuevosComentarios = sc.nextLine();
        String query = "UPDATE personas SET nombre_apellido = ?, fecha_registro = ?, sueldo_base = ?, sexo = ?, edad = ?, longitud = ?, latitud = ?, comentarios = ? WHERE codigo = ?";
        PreparedStatement ps = null;

        try {
            ps = this.conn.prepareStatement(query);
            ps.setString(1, nuevoNombre + " " + nuevoApellido);
            ps.setString(2, nuevaFechaRegistro);
            ps.setDouble(3, nuevoSueldoBase);
            ps.setString(4, nuevoSexo);
            ps.setInt(5, nuevaEdad);
            ps.setDouble(6, nuevaLongitud);
            ps.setDouble(7, nuevaLatitud);
            ps.setString(8, nuevosComentarios);
            ps.setInt(9, codigo);
            int rowCount = ps.executeUpdate();
            if (rowCount > 0) {
                System.out.println("Datos actualizados correctamente.");
            } else {
                System.out.println("No se encontró ningún registro con el código proporcionado.");
            }
        } catch (SQLException var21) {
            var21.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }

        }

    }

    public void eliminarDatos() throws SQLException {
        this.conexion();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un código de persona para eliminar:");
        int codigo = sc.nextInt();
        String query = "DELETE FROM personas WHERE codigo = ?";
        PreparedStatement ps = null;

        try {
            ps = this.conn.prepareStatement(query);
            ps.setInt(1, codigo);
            int rowCount = ps.executeUpdate();
            if (rowCount > 0) {
                System.out.println("Datos eliminados correctamente.");
            } else {
                System.out.println("No se encontró ningún registro con el código proporcionado.");
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }

        }

    }
}
