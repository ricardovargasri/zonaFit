package com.ecodeuo.jdbc.presentacion;

import com.ecodeuo.jdbc.datos.ClienteDao;
import com.ecodeuo.jdbc.datos.IclienteDao;
import com.ecodeuo.jdbc.dominio.Cliente;

import java.io.Console;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ZonaFittApp {
    public static void main(String[] args) {
        zonaFittApp();
    }

    private static void zonaFittApp() {
        var salir = false;
        var consola = new Scanner(System.in);

        while (!salir){

            try {
                var opcion = desplegarMenu(consola);
                IclienteDao clienteDAO = new ClienteDao();
                salir = ejecutarOpciones(consola, opcion, clienteDAO);
            }catch (Exception e){
                System.out.println("error de tipo "+ e.getMessage());
            }

        }
    }

    private static int desplegarMenu(Scanner consola) {
        System.out.println("""
                1. mostrar clientes.
                2. buscar clientes.
                3. agregar clientes.
                4. modificar clientes.
                5. eliminar clientes.
                6. salir
                Elije una opcion: \s""");
        return Integer.parseInt(consola.nextLine());
    }
    private static Boolean ejecutarOpciones(Scanner consola, int opcion, IclienteDao clienteDao){

        var clienteY = new Cliente();
        var salir = false;
        switch (opcion){
            case 1 -> {
                System.out.println("--- LISTADO DE CLIENTES ---");
                var clientes = clienteDao.listarClientes();
                clientes.forEach(System.out::println);
                salir = true;
            }
            case 2 ->{

                System.out.println("introduzca el id que desea encontrar: ");
                //idSolicitado();
                var clienteX = new Cliente(idSolicitado());
                var cliente = clienteDao.buscarPorId(clienteX);
                if (cliente){
                    System.out.println("el cliente que busca es: "+clienteX.getNombre() + " " + clienteX.getApellido());
                };
                salir = cliente;
            }
            case 3 ->{
                var clienteX = new Cliente();

                System.out.print("nombre para el cliente: ");
                var nombre = consola.nextLine();
                clienteX.setNombre(nombre);
                System.out.print("apellido para el cliente: ");
                var apellido = consola.nextLine();
                clienteX.setApellido(apellido);
                System.out.print("membresia para el cliente: ");
                var mebresia = consola.nextInt();
                clienteX.setMembresia(mebresia);
                var cliente = clienteDao.agregarCliente(clienteX);

                salir = cliente;
            }
            case 4 ->{
                System.out.println("--- MODIFICAR CLIENTE: se buscara por id ---");
                clienteY.setId(idSolicitado());
                var buscado = clienteDao.buscarPorId(clienteY);
                if(buscado){
                    System.out.print("nombre para el cliente: ");
                    var nombre = consola.nextLine();
                    clienteY.setNombre(nombre);
                    System.out.print("apellido para el cliente: ");
                    var apellido = consola.nextLine();
                    clienteY.setApellido(apellido);
                    System.out.print("membresia para el cliente: ");
                    var mebresia = consola.nextInt();
                    clienteY.setMembresia(mebresia);


                    clienteDao.modificarCliente(clienteY);
                    System.out.println("cliente despues de la modificacion: "+ clienteY);

                    salir = true;

                }else
                    System.out.println("cliente no encontrado");
            }
            case 5 ->{
                System.out.println("--- ELIMINADOR DE CLIENTES ---");
                clienteY.setId(idSolicitado());
                var buscado = clienteDao.buscarPorId(clienteY);
                if (buscado){
                    System.out.println("se eliminara el cliente "+ clienteY.getNombre());
                    clienteDao.eliminarCliente(clienteY);
                    return buscado;
                }else
                    System.out.println("cliente con id "+ clienteY.getId()+" no existe");
            }
        }
        return salir;

    }
    private static int idSolicitado() {
        System.out.print("agregue el id que busca: ");
        var consola = new Scanner(System.in);
        return Integer.parseInt(consola.nextLine());
    }

}
