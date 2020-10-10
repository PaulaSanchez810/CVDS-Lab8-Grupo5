package edu.eci.cvds.sampleprj.dao.mybatis;
import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;

import java.util.Date;
import java.util.List;


public class MyBATISClienteDAO implements ClienteDAO {
    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public void save(Cliente it) throws PersistenceException{
        try {
            clienteMapper.agregarCliente(it);
        }
        catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el Cliente " + it.toString(), e);
        }
    }
    ///
    @Override
    public void saveVetado(long id, boolean estado) throws org.apache.ibatis.exceptions.PersistenceException {

    }
    ///
    @Override
    public void saveAlquiler(Date fechaInicio, int clienteId, Item itemAlquilado, int numeroDias) {

    }

    @Override
    public Cliente load(long id) throws PersistenceException {
        try{
            return clienteMapper.consultarCliente((int) id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el Cliente "+id,e);
        }
    }


    @Override
    public List<Cliente> consultarClientes() throws PersistenceException {
        try {
            return clienteMapper.consultarClientes();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los clientes ", e);
        }
    }

}
