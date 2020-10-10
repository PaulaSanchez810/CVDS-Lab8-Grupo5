package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.Date;
import java.util.List;

public interface ClienteDAO {
    public void save(Cliente it) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
    public void saveVetado(long id, boolean estado) throws PersistenceException;
    public void saveAlquiler(Date fechaInicio,int clienteId, Item itemAlquilado, int numeroDias);
    public Cliente load(long id) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
    public List<Cliente> consultarClientes() throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;


}
