/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ad03;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author duche
 */
public class Main {

    private static Connection conexion = null;

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {

        try {

            JFrame frame = new JFrame("CORTEFIES"); //Creo un frame donde pondré todos los botones

            //Agrego un botón para cada función
            JButton btn1 = new JButton("Crear tabla Empleados");
            JButton btn2 = new JButton("Crear tabla Ordenes de productos");
            JButton btn3 = new JButton("Insertar datos en la tabla Empleados");
            JButton btn4 = new JButton("Insertar datos en la tabla Ordenes de productos");
            JButton btn5 = new JButton("Recuperar datos de la tabla Empleados");
            JButton btn6 = new JButton("Recuperar datos de la tabla Ordenes");
            JButton btn7 = new JButton("Actualizar en tabla Empleados");
            JButton btn8 = new JButton("Actualizar en tabla Empleados con Sentencia Preparada");
            JButton btn9 = new JButton("Actualizar registros de Ordenes con Sentencia Preparada (Bufandas)");
            JButton btn10 = new JButton("Listar el nombre de los empleados que vendieron Bufandas");
            JPanel panel = new JPanel(new GridLayout(10, 5)); //Creo un GridLayout para añadir los botones
            JButton[] botones = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10}; //Creo un array de botones para simplificar el código
            conexion = DriverManager.getConnection("jdbc:ucanaccess://cortefies.accdb", "", ""); //Creo una conexión a la base de datos (debe estar la carpeta raiz del proyecto)
            Statement stmt = (Statement) conexion.createStatement(); //Creo un Statement para realizar consultas
            for (int i = 0; i < 10; i++) { //Recorro el bucle de botones
                panel.add(botones[i]);
                switch (i) { //Con esto asigno un actionPerformed a cada botón en función de su número
                    case 0: {
                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                String sqlCreate = "CREATE TABLE EMPLEADOS" //Orden SQL para crear la table empleados
                                        + " (Codigo_Empleado CHAR(10),"
                                        + " Nombre_Empleado CHAR(50),"
                                        + " Telefono CHAR(15));";
                                stmt.execute(sqlCreate); //Ejecuto el Statement
                            } catch (SQLException ex) {
                                if (ex.toString().toLowerCase().contains("ya existe")) { //Capturo la excepción para detectar si la tabla existe
                                    JOptionPane.showMessageDialog(null, "La tabla EMPLEADOS ya existe");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error al crear base de datos: " + ex);
                                }
                            }
                        });
                    }
                    break;
                    case 1: {
                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                String sqlCreate = "CREATE TABLE ORDENES" //Orden SQL para crear la table productos
                                        + "  (Codigo_Producto CHAR(10),"
                                        + "   Nombre_Producto CHAR(50),"
                                        + "   Empleado CHAR(15))";
                                stmt.execute(sqlCreate); //Ejecuto el Statement
                            } catch (SQLException ex) {
                                if (ex.toString().toLowerCase().contains("ya existe")) {
                                    JOptionPane.showMessageDialog(null, "La tabla ORDENES ya existe"); //Capturo la excepción para detectar si la tabla existe
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error al crear base de datos: " + ex);
                                }
                            }
                        });
                    }
                    break;
                    case 2: {
                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                for (int z = 0; z < 4; z++) { //Bucle para insertar 4 empleados
                                    String sqlCreate = "INSERT INTO EMPLEADOS (Codigo_Empleado, Nombre_Empleado, Telefono) " //SQL para insertar empleado
                                            + "VALUES "
                                            + "('00" + z + "' ,"
                                            + "'Empleado " + z + " ',"
                                            + "'69696969')";
                                    stmt.execute(sqlCreate); //Ejecuto el Statement
                                }
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error al insertar empleados: " + ex); //Capturo errores
                            }
                        });
                    }
                    break;
                    case 3: {
                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                for (int z = 0; z < 4; z++) { //Bucle para insertar 4 ordenes
                                    String sqlCreate = "INSERT INTO ORDENES (Codigo_Producto, Nombre_Producto, Empleado) " //SQL para insertar Ordenes
                                            + "VALUES "
                                            + "('00" + z + "' ,"
                                            + "'Articulo" + z + " ',"
                                            + "'00" + z + "')";
                                    stmt.execute(sqlCreate); //Ejecuto el Statement
                                }
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error al insertar productos: " + ex); //Capturo errores
                            }
                        });
                    }
                    break;
                    case 4: {
                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                JTextArea area = new JTextArea(""); //Creo un JTextArea donde mostraré los resultados
                                JScrollPane scroll = new JScrollPane(area);
                                String sqlCreate = "SELECT * FROM EMPLEADOS";//Orden SQL de la consulta
                                ResultSet resultado = stmt.executeQuery(sqlCreate); //Ejecuto Statament
                                while (resultado.next()) { //Recorro los resultados
                                    area.append(resultado.getString(1) + " - " //Cadena de texto con datos de interés
                                            + resultado.getString(2) + " - "
                                            + resultado.getString(3) + "\n");
                                }

                                JOptionPane.showMessageDialog(null, scroll); //Muestro el listado
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error al consultar empleados: " + ex); //Capturo errores
                            }
                        });
                    }
                    break;
                    case 5: {
                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                JTextArea area = new JTextArea(""); //Creo un JTextArea donde mostraré los resultados
                                JScrollPane scroll = new JScrollPane(area);
                                String sqlCreate = "SELECT * FROM ORDENES";//Orden SQL de la consulta
                                ResultSet resultado = stmt.executeQuery(sqlCreate); //Ejecuto Statament
                                while (resultado.next()) { //Recorro los resultados
                                    area.append(resultado.getString(1) + " - " //Cadena de texto con datos de interés
                                            + resultado.getString(2) + " - "
                                            + resultado.getString(3) + "\n");
                                }
                                JOptionPane.showMessageDialog(null, scroll); //Muestro el listado
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error al consultar productos: " + ex); //Capturo errores
                            }
                        });
                    }
                    break;
                    case 6: {
                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                String sqlCreate = "UPDATE EMPLEADOS \n"//Orden SQL de la actualización
                                        + "SET Telefono = '76767676' "
                                        + "WHERE Codigo_Empleado = '001'";
                                stmt.executeUpdate(sqlCreate); //Ejecuto Statament
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error al actualizar empleados: " + ex); //Capturo errores
                            }
                        });
                    }
                    break;
                    case 7: {

                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                String sqlCreate = "UPDATE EMPLEADOS SET Telefono = '848484' WHERE Codigo_Empleado = ?";
                                PreparedStatement consultaEmpleados = conexion.prepareStatement(sqlCreate); //PreparedStatement para la consulta
                                consultaEmpleados.setString(1, "002"); //Introduzco los parámetros para la consulta
                                consultaEmpleados.execute(); //Ejecuto el Statement
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error al actualizar empleados: " + ex); //Capturo errores
                            }
                        });
                    }
                    break;
                    case 8: {

                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                String sqlCreate = "UPDATE ORDENES SET Nombre_Producto = 'Bufanda' WHERE Codigo_Producto = ?";
                                PreparedStatement consultaProductos = conexion.prepareStatement(sqlCreate); //PreparedStatement para la consulta
                                consultaProductos.setString(1, "003");//Introduzco los parámetros para la consulta
                                consultaProductos.execute();//Ejecuto el Statement
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error al actualizar productos: " + ex); //Capturo errores
                            }
                        });
                    }
                    break;
                    case 9: {
                        botones[i].addActionListener((ActionEvent e) -> {
                            try {
                                JTextArea area = new JTextArea(""); //Creo un JTextArea donde mostraré los resultados
                                JScrollPane scroll = new JScrollPane(area);
                                String sqlCreate = "SELECT DISTINCTROW Nombre_Empleado FROM ORDENES " //Orden SQL de la consulta
                                        + "INNER JOIN EMPLEADOS ON EMPLEADOS.CODIGO_EMPLEADO=ORDENES.Empleado "
                                        + "WHERE Nombre_Producto='Bufanda'";
                                ResultSet resultado = stmt.executeQuery(sqlCreate);//Ejecuto el Statement
                                while (resultado.next()) { //Recorro los resultados
                                    area.append(resultado.getString("Nombre_Empleado") + "\n"); //Cadena de texto con datos de interés
                                }
                                JOptionPane.showMessageDialog(null, scroll); //Muestro el listado
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error al consultar productos: " + ex);//Capturo errores
                            }
                        });
                    }
                    break;
                }
            }
            //Parámetros de configuración del frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            frame.setSize(400, 500);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(panel);
            frame.setVisible(true);
            
            frame.addWindowListener(new WindowAdapter() { //Capturo el cierre del frame para cerrar la conexión
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Cerrando Conexion...");
                try {
                    if (conexion != null){
                        conexion.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    System.exit(0);
                }
            }
        });
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al realizar la conexion " + ex); //Capturo errores de conexión
        } 
    }
}
