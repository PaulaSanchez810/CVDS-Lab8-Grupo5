package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.TipoItem;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public interface TipoItemDAO {

    public void save(TipoItem it) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
    public TipoItem load (int id) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
    public List<TipoItem> consultarTiposItem() throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
}
