package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.exceptions.PersistenceException;
import java.util.List;

public interface ItemDAO {

    public void save(Item it) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
    public Item load(int id) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
    public void saveTarifaItem(int itemId,long nuevaTarifa) throws PersistenceException;
    List<Item> consultarItemsDisponibles()throws edu.eci.cvds.sampleprj.dao.PersistenceException;


}
