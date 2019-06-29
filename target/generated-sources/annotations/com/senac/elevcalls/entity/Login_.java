package com.senac.elevcalls.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-25T20:56:03")
@StaticMetamodel(Login.class)
public class Login_ { 

    public static volatile SingularAttribute<Login, String> nomeUsuario;
    public static volatile SingularAttribute<Login, String> emailUsuario;
    public static volatile SingularAttribute<Login, String> senhaUsuario;
    public static volatile SingularAttribute<Login, Long> id;
    public static volatile SingularAttribute<Login, String> confirmarSenha;
    public static volatile SingularAttribute<Login, String> confirmarEmail;

}