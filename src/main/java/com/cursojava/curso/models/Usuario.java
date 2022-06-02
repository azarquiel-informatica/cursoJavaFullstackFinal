package com.cursojava.curso.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table( name = "usuarios")
@ToString @EqualsAndHashCode
public class Usuario {
//TODAS LAS VARIABLES QUE PUEDE TENER EL USUARIO

    //DECLARAMOS LAS VARIABLES COMO PRIVADAS Y CREAMOS DOS FUNCIONES: UNA PARA ACCEDER AL VALOR Y OTRA PARA MODIFICARLO
    @Getter @Setter @Column (name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    @Getter @Setter @Column (name = "nombre")
    private String nombre;

    @Getter @Setter @Column (name = "apellido")
    private String apellido;

    @Getter @Setter @Column (name = "email")
    private String email;

    @Getter @Setter @Column (name = "telefono")
    private String telefono;

    @Getter @Setter @Column (name = "password")
    private String password;



}
