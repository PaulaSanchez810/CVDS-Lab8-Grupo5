package edu.eci.cvds.sampleprj.dao;


import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;

public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(int id);
    
    public void insertarItem(Item it);

        
}
