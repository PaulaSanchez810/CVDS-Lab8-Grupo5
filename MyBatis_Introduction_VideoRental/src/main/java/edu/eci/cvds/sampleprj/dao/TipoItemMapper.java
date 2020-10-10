package edu.eci.cvds.sampleprj.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemMapper {
    
    
    public List<TipoItem> getTiposItems();
    
    public TipoItem getTipoItem(int id);
    
    public void addTipoItem(String des);

}
