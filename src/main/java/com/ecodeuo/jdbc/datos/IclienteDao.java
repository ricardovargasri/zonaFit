package com.ecodeuo.jdbc.datos;

import com.ecodeuo.jdbc.dominio.Cliente;

import java.util.List;

public interface IclienteDao {

    List<Cliente> listarClientes();
    boolean buscarPorId(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
}
