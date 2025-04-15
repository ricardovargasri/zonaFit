package com.ecodeuo.jdbc.datos;

import com.ecodeuo.jdbc.conexion.Conexion;
import com.ecodeuo.jdbc.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.ecodeuo.jdbc.conexion.Conexion.getConnection;

public class ClienteDao implements IclienteDao{
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConnection();
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();// se cra lista de clientes
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        var sql = "SELECT * FROM clientes ORDER BY id";

        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e){
            System.out.println("error de tipo: "+e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("error: "+e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarPorId(Cliente cliente) {

        String sql = "SELECT * FROM clientes WHERE id = ?";

        Connection con = getConnection();

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        }catch (Exception e){
            System.out.println("error de tipo "+e.getMessage());
            return false;
        }
        finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                con.close();
            }catch (Exception e){
                System.out.println("no se pudo cerrar la conexion: "+e.getMessage());
            }
        }
return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {

        String sql = "INSERT INTO clientes (nombre, apellido, membresia) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        }catch (Exception e){
            System.out.println("error de tipo: " + e.getMessage());
        }
        finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                con.close();
            }catch (Exception e){
                System.out.println("no se pudo cerrar la conexion: "+e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {

        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, membresia = ? WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());


            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
        }
        finally {
            try {
                if (ps != null){
                    ps.close();
                }
                if (con != null){
                    con.close();
                };

            }catch (Exception e){
                System.out.println("no se pudo cerrar la conexion: "+e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }

    public static void main(String[] args) {
        var clienteDao = new ClienteDao();
        /*var listado = clienteDao.listarClientes();
        listado.forEach(System.out::println);*/

       /* var clientaso =  new Cliente(3);
        var encontrado = clienteDao.buscarPorId(clientaso);
        if(encontrado){
            System.out.println("se encontro el cliente: " + clientaso);
        }else
            System.out.println("paila ñero");*/
        //agregar un cliente
        /*Cliente clienteSuper = new Cliente();
        clienteSuper.setNombre("mariana");
        clienteSuper.setApellido("macias");
        clienteSuper.setMembresia(1000);

        clienteDao.agregarCliente(clienteSuper);*/

        //modificar cliente
        /*var clienteEnsayo = new Cliente(1,"pablu","meriol", 365);
        System.out.println(clienteDao.modificarCliente(clienteEnsayo));
        clienteDao.modificarCliente(clienteEnsayo);*/

        // NUEVA PRUEBA AGREGAR CLIENTE
        Cliente clienteX = new Cliente();
        clienteX.setNombre("vespusio");
        clienteX.setApellido("americolino");
        clienteX.setMembresia(852);

        clienteDao.agregarCliente(clienteX);

    }
}
