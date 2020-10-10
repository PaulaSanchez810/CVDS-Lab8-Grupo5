package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Cliente;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ClienteMapper {
    public Cliente consultarCliente(@Param("idcli") long id);
    public void agregarItemRentadoACliente(@Param("idcli") long idCliente, @Param("idit") int idItem, @Param("fechai") Date fechainicio, @Param("fechaf") Date fechafin);


}
