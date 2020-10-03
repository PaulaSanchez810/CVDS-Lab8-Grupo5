/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {

    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";

            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);


            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));

            List<String> prodsPedido=nombresProductosPedido(con, 1);


            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");


            int suCodigoECI=2141130;
            registrarNuevoProducto(con, suCodigoECI, "SU_NOMBRE", 99999999);
            con.commit();

            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        //Crear prepared statement
        PreparedStatement registroNuevoProducto = null;
        //Hacer consulta
        String registro = "INSERT INTO ORD_PRODUCTOS(codigo,nombre,precio) VALUES (?,?,?);";
        try {
            //Pasar la consulta al PreparedStatament
            registroNuevoProducto = con.prepareStatement(registro);
            //Asignar parámetros
            registroNuevoProducto.setInt(1,codigo);
            registroNuevoProducto.setString(2,nombre);
            registroNuevoProducto.setInt(3,precio);
            //usar execute
            registroNuevoProducto.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        con.commit();
    }

    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido){
        List<String> np=new LinkedList<>();
        //Crear prepared statement
        PreparedStatement consultarProductos = null;
        //asignar parámetros
        String nombresProductos = "SELECT nombre FROM ORD_PRODUCTOS WHERE codigo in (SELECT producto_fk FROM ORD_DETALLE_PEDIDO WHERE pedido_fk = ?);";
        try{
            //Pasar la consulta al PreparedStatament
            consultarProductos = con.prepareStatement(nombresProductos);
            //Remplazar el primer ? con el codigo necesitado
            consultarProductos.setInt(1,codigoPedido);
            //usar executeQuery
            ResultSet resultadoConsulta = consultarProductos.executeQuery();
            //Sacar resultados del ResultSet
            while(resultadoConsulta.next()) {
                //Llenar la lista y retornarla
                np.add(resultadoConsulta.getString("nombre"));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return np;
    }

    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido){
        int valorTotalDelPedido=0;
        //Crear prepared statement
        PreparedStatement valorTotal = null;
        //Asignar parámetros
        String valorPedido = "SELECT SUM((SELECT precio from ORD_PRODUCTOS WHERE codigo=producto_fk)*cantidad) AS valorTotal FROM ORD_DETALLE_PEDIDO WHERE pedido_fk=?;";
        try {
            //Pasar la consulta al PreparedStatament
            valorTotal = con.prepareStatement(valorPedido);
            //Poner el parámetro a la consulta
            valorTotal.setInt(1,codigoPedido);
            //usar executeQuery
            ResultSet resultadoConsulta = valorTotal.executeQuery();
            resultadoConsulta.next();
            //Asignar el resultado de la consulta
            valorTotalDelPedido=resultadoConsulta.getInt("valorTotal");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return valorTotalDelPedido;
    }
}