package com.aerolinea.dao;

import com.aerolinea.entidad.Rol;
import com.aerolinea.entidad.Usuario;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component("UsuarioDaoImpl")
//@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, String> 
        implements UsuarioDao, Serializable{
    @Override
    public List<Usuario> findAll() throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select u from Usuario u "
                    + " join fetch u.pais"
                    + " join fetch u.rol");
            List<Usuario> entities = query.list();
            session.getTransaction().commit();
            return entities;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
//                LOGGER.log(Level.WARNING,"Falló al hacer un rollback", exc);
            }
            throw new RuntimeException(ex);
        }finally{session.close();}
    }  
    
    public Usuario getUsuario(String username) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select u from Usuario u "
                    + " join fetch u.pais"
                    + " join fetch u.rol where u.idusuario= :idusuario");
            
            query.setParameter("idusuario", username);
            Usuario u = (Usuario) query.uniqueResult();
            session.getTransaction().commit();
            return u;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
//                LOGGER.log(Level.WARNING,"Falló al hacer un rollback", exc);
            }
            throw new RuntimeException(ex);
        }finally{session.close();}
    }      
}
