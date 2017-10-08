package com.aerolinea.dao;

import com.aerolinea.entidad.Rol;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component("RolDaoImpl")
//@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RolDaoImpl extends GenericDaoImpl<Rol, Integer> 
        implements RolDao, Serializable{
}
