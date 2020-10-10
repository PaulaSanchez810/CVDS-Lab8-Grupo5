package edu.eci.cvds.samples.services.impl;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.*;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.exeptions.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquier;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquier{

    @Inject
    private ItemDAO itemDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private TipoItemDAO tipoitemDAO;

    @Inject
    private ItemRentadoDAO itemrentadoDAO;

    @Override
    public int valorMultaRetrasoxDia(int itemId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try{
            Cliente cliente = null;
            cliente = clienteDAO.load(docu);
            if(cliente == null){
                throw new ExcepcionServiciosAlquiler("El cliente con documento: " + docu + " no existe.");
            }
            else {
                return cliente;
            }
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al consultar el cliete de documento: " + docu);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try{
            return clienteDAO.load(idcliente).getRentados();
        }catch(PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("No existe cliente con el id: "+idcliente,ex);
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return clienteDAO.consultarClientes();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error En la Consulta De Clientes");

        }
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
        try {
            return itemDAO.consultarItemsDisponibles();
        }catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("No existen items",ex);
        }
    }
    ///pendiente
    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return (TipoItem) tipoitemDAO.consultarTiposItem();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el tipo item", ex);
        }
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        return null;
    }

    @Transactional
    public TipoItem consultarTiposItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return tipoitemDAO.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el tipo items");
        }
    }
    @Transactional
    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Transactional
    @Override
    public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
        try{
            clienteDAO.save(c);
        }catch (PersistenceException e){
            throw new ExcepcionServiciosAlquiler("Error al registrar el cliente con documento: " + c.getDocumento());
        }
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Transactional
    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try{
            itemDAO.save(i);
        }catch (PersistenceException e){
            throw new ExcepcionServiciosAlquiler("Error al registrar el item: " + i.getId());
        }
    }

    @Override
    @Transactional
    public void registrarTipoItem(TipoItem tipoItem) throws ExcepcionServiciosAlquiler {
        try {
            tipoitemDAO.save(tipoItem);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al insertar el tipo de Item " + tipoItem.getID(), e);
        }
    }


    @Transactional
    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        clienteDAO.saveVetado(docu,estado);
    }
}
