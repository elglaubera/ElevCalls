package com.senac.elevcalls.entity;

import com.senac.elevcalls.entity.Chamadas;
import com.senac.elevcalls.entity.Funcionarios;
import com.senac.elevcalls.entity.Login;
import com.senac.elevcalls.entity.PecasPendecias;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-25T20:56:03")
@StaticMetamodel(Pendencias.class)
public class Pendencias_ { 

    public static volatile SingularAttribute<Pendencias, Date> dataSolicitacao;
    public static volatile SingularAttribute<Pendencias, Integer> qtdePecas;
    public static volatile SingularAttribute<Pendencias, String> descricaoServico;
    public static volatile SingularAttribute<Pendencias, Chamadas> chamadas;
    public static volatile SingularAttribute<Pendencias, Long> id;
    public static volatile SingularAttribute<Pendencias, Funcionarios> funcionarios;
    public static volatile SingularAttribute<Pendencias, PecasPendecias> pecasPendencias;
    public static volatile SingularAttribute<Pendencias, Login> login;

}