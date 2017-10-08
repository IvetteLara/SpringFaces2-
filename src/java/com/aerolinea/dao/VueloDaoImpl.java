package com.aerolinea.dao;

import com.aerolinea.entidad.Vuelo;
import java.io.Serializable;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Component;

@Component("VueloDaoImpl")
public class VueloDaoImpl extends GenericDaoImpl<Vuelo, Integer>
        implements VueloDao, Serializable {
    
    @PreAuthorize("hasAnyRole({'ROLE_OTRO', 'ROLE_ADMIN'})")
    @PreFilter(	"hasRole('ROLE_ADMIN')	|| " +	"targetObject.idusuario	== principal.username")
    public void	deleteVuelos(List<Vuelo> vuelos) {
    //TODO
    //…
    }
    
}
