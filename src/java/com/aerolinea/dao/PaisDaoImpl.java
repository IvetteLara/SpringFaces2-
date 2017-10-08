package com.aerolinea.dao;

import com.aerolinea.entidad.Pais;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component("PaisDaoImpl")
//@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PaisDaoImpl extends GenericDaoImpl<Pais, Integer>  
        implements PaisDao, Serializable{
}
