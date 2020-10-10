package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.Cliente;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public interface ClienteDAO {
    public void save(Cliente it) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;

    public Cliente load(long id) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;

    public List<Cliente> consultarClientes() throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;


}
