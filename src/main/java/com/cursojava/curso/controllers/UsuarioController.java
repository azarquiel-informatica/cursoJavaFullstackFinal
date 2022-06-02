package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    // Hace que la clase UsuarioDaoImp crea un objeto y lo guarda en esta variable
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET) // Que url queremos devolver
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) { //GUARDAMOS EL TOKEN
        if (!validarToken(token)) { return null; }

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) { //VALIDAMOS TOKEN
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword()); //Convertimos contraseña
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }
    //Si entran por esta URL y por el metodo DELETE va a entrar por esta función
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token,
                         @PathVariable Long id) {
        if (!validarToken(token)) { return; }
        usuarioDao.eliminar(id);
    }

}

