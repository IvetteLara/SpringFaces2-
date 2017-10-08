/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.config;

import com.aerolinea.dao.UsuarioDaoImpl;
import com.aerolinea.entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author java
 */
@Service
public class AerolineaUserService implements UserDetailsService {
    @Autowired
    private UsuarioDaoImpl UsuarioDaoImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario u = null;
        
        try {
            u = UsuarioDaoImpl.getUsuario(username);
        } catch (Exception ex) {
            Logger.getLogger(AerolineaUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        if(u != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+u.getRol().getRol()));
            return new User(u.getIdusuario(),u.getClave(),authorities);
        }

        throw new UsernameNotFoundException("User '"+ username + "' not found.");    
    }
     
}
