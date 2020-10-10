package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.annotations.Param;

public interface ItemRentadoMapper {
    public Item consultarItem(@Param("idit") int id);

    public void insertarItem(@Param("item") Item it);

    public void actualizarTarifaItem(@Param("Iditem") int id, @Param("itemTarifa") long tarifa);
}
