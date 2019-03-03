# Tarea para AD03.
## Detalles de la tarea de esta unidad.
Se trata de hacer una aplicación en Java que acceda a una base de datos (Access) de una tienda de ropa y mediante un menú pueda realizar determinas operaciones. La base de datos vacía, sin tablas, la puedes descargar desde el siguiente enlace:

Base de datos de datos para la tarea(0.01 MB)
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

```
* Insertar datos en la tabla Empleados. Inserta cuatro empleados en la tabla de Empleados.
```Java

```
* Insertar datos en la tabla Ordenes de productos. Inserta cuatro órdenes de venta en la tabla de Ordenes.
```Java

```
* Recuperar datos de la tabla Empleados. Recupera los datos de la tabla Empleados y los muestra al usuario.
```Java

```
* Recuperar datos de la tabla Ordenes. Recupera los datos de la tabla Ordenes y los muestra al usuario.
```Java

```
* Actualizar en tabla Empleados. Realizar una consulta de actualización, "Update", sobre un registro previamente insertado.
```Java

```
* Actualizar en tabla Empleados utilizando sentencias preparadas. Realizar una consulta de actualización, "Update", sobre un registro previamente insertado, utilizando sentencias preparadas.
```Java

```
* Actualizar registros de Ordenes utilizando sentencias preparadas. Realizar una consulta de actualización, "Update", en la tabla Ordenes, sobre un registro previamente insertado, utilizando sentencias preparadas.
```Java

```
* Listar el nombre de los empleados que vendieron un artículo que se encuentre insertado en la tabla de Ordenes, por ejemplo: bufandas.
  
Elabora el programa y un documento con un procesador de texto. El documento debe ser de tipo ".doc" (Microsoft Word) o de tipo ".odt" (OpenOffice.org). El documento debe tener tamaño de página A4, estilo de letra Times New Roman, tamaño 12 e interlineado normal.

En el documento escribirás un informe sobre todas las consideraciones oportunas que se necesiten para entender cómo has realizado la tarea.
