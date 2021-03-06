package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper {

    public Item consultarItem(@Param("idit") int id);

    public void insertarItem(@Param("item") Item it);
}
