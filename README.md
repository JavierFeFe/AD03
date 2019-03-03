# Tarea para AD03.
## Detalles de la tarea de esta unidad.
Se trata de hacer una aplicación en Java que acceda a una base de datos (Access) de una tienda de ropa y mediante un menú pueda realizar determinas operaciones. La base de datos vacía, sin tablas, la puedes descargar desde el siguiente enlace:  

![image](https://user-images.githubusercontent.com/44543081/53701053-6ad4e900-3df9-11e9-9d5d-109c05ecaae9.png)    
**NO HA SIDO POSIBLE REALIZAR EL EJERCICIO CON EL CONECTOR DEL TEMARIO, A PARTIR DE LA VERSIÓN 8 DE JAVA DEJÓ DE FUNCIOANAR, POR LO QUE FUE NECESARIO INCLUIR LAS LIBRERÍAS UCANACCESS BAJADAS DE LOS REPOSITORIOS DE MAVEN**
  
Base de datos de datos para la tarea(0.01 MB) 
  
**LA BASE DE DATOS FACILITADA DA ERROR CON LAS LIBRERÍAS INCLUIDAS POR LO QUE GENERÉ UNA NUEVA BASE DE DATOS VACÍA DESDE MSACCESS**
  
Puedes utilizar un JOptionPane para presentar el menú con las opciones que permita realizar el programa:

* Crear tabla Empleados. Contendrá los campos: Codigo_Empleado, Nombre_Empleado y Teléfono del mismo.
```Java
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
```
* Crear tabla Ordenes de productos. Por simplificar la aplicación, contendrá los campos: código del producto, nombre del producto y el código del empleado que realiza la venta. Puedes introducir más campos como el número de unidades que se venderían, etc., si lo deseas.
```Java
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

```
* Insertar datos en la tabla Empleados. Inserta cuatro empleados en la tabla de Empleados.
```Java
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
```
* Insertar datos en la tabla Ordenes de productos. Inserta cuatro órdenes de venta en la tabla de Ordenes.
```Java
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
```
* Recuperar datos de la tabla Empleados. Recupera los datos de la tabla Empleados y los muestra al usuario.
```Java
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
```
* Recuperar datos de la tabla Ordenes. Recupera los datos de la tabla Ordenes y los muestra al usuario.
```Java
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
```
* Actualizar en tabla Empleados. Realizar una consulta de actualización, "Update", sobre un registro previamente insertado.
```Java
try {
    String sqlCreate = "UPDATE EMPLEADOS \n"//Orden SQL de la actualización
            + "SET Telefono = '76767676' "
            + "WHERE Codigo_Empleado = '001'";
    stmt.executeUpdate(sqlCreate); //Ejecuto Statament
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error al actualizar empleados: " + ex); //Capturo errores
}
```
* Actualizar en tabla Empleados utilizando sentencias preparadas. Realizar una consulta de actualización, "Update", sobre un registro previamente insertado, utilizando sentencias preparadas.
```Java
try {
    String sqlCreate = "UPDATE EMPLEADOS SET Telefono = '848484' WHERE Codigo_Empleado = ?";
    PreparedStatement consultaEmpleados = conexion.prepareStatement(sqlCreate); //PreparedStatement para la consulta
    consultaEmpleados.setString(1, "002"); //Introduzco los parámetros para la consulta
    consultaEmpleados.execute(); //Ejecuto el Statement
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error al actualizar empleados: " + ex); //Capturo errores
}
```
* Actualizar registros de Ordenes utilizando sentencias preparadas. Realizar una consulta de actualización, "Update", en la tabla Ordenes, sobre un registro previamente insertado, utilizando sentencias preparadas.
```Java
try {
    String sqlCreate = "UPDATE ORDENES SET Nombre_Producto = 'Bufanda' WHERE Codigo_Producto = ?";
    PreparedStatement consultaProductos = conexion.prepareStatement(sqlCreate); //PreparedStatement para la consulta
    consultaProductos.setString(1, "003");//Introduzco los parámetros para la consulta
    consultaProductos.execute();//Ejecuto el Statement
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error al actualizar productos: " + ex); //Capturo errores
}
```
* Listar el nombre de los empleados que vendieron un artículo que se encuentre insertado en la tabla de Ordenes, por ejemplo: bufandas.
```Java
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
```
Elabora el programa y un documento con un procesador de texto. El documento debe ser de tipo ".doc" (Microsoft Word) o de tipo ".odt" (OpenOffice.org). El documento debe tener tamaño de página A4, estilo de letra Times New Roman, tamaño 12 e interlineado normal.

En el documento escribirás un informe sobre todas las consideraciones oportunas que se necesiten para entender cómo has realizado la tarea.
