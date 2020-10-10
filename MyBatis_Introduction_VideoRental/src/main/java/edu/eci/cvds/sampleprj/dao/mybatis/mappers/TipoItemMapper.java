package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.TipoItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TipoItemMapper {
    public List<TipoItem> consultarTiposItems();

    public TipoItem getTipoItem(@Param("idtitem") int id);

    public void addTipoItem(@Param("titem") TipoItem tipoItem);
}
