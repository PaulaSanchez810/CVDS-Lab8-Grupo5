package edu.eci.cvds.sampleprj.dao.mybatis;
import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {
    @Inject
    private ItemRentadoMapper  itemRentadoMapper;

    @Override
    public void save(ItemRentado it) throws PersistenceException{
    }

    @Override
    public ItemRentado load(int id) throws PersistenceException{
        return null;
    }
}
