package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {
//FUNCIONES OBLIGATORIAS QUE TIENE LA CLASE
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}