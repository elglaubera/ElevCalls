package com.senac.elevcalls.entity;

import com.senac.elevcalls.entity.Condominios;
import com.senac.elevcalls.entity.Funcionarios;
import com.senac.elevcalls.entity.Login;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-25T20:56:03")
@StaticMetamodel(Chamadas.class)
public class Chamadas_ { 

    public static volatile SingularAttribute<Chamadas, String> horaEnvio;
    public static volatile SingularAttribute<Chamadas, String> horaChamada;
    public static volatile SingularAttribute<Chamadas, Integer> telSolicitante;
    public static volatile SingularAttribute<Chamadas, String> nomeSolicitante;
    public static volatile SingularAttribute<Chamadas, Long> id;
    public static volatile SingularAttribute<Chamadas, Funcionarios> funcionarios;
    public static volatile SingularAttribute<Chamadas, Login> login;
    public static volatile SingularAttribute<Chamadas, Condominios> condominios;
    public static volatile SingularAttribute<Chamadas, String> descricaoDefeito;

}