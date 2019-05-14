/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.elevcalls.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Glauber
 */
@Entity
public class Pendencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Chamadas chamadas;

    @OneToOne
    private PecasPendecias pecasPendencias;

    @OneToOne(optional = false)
    private Funcionarios funcionarios;

    @OneToOne(optional = false)
    private Login login;

    private String descricaoServico;
    private Date dataSolicitacao;
    private int qtdePecas;

    public Chamadas getChamadas() {
        return chamadas;
    }

    public void setChamadas(Chamadas chamadas) {
        this.chamadas = chamadas;
    }

    public Funcionarios getFuncionarios() {
        return funcionarios;
    }

    public PecasPendecias getPecasPendencias() {
        return pecasPendencias;
    }

    public void setPecasPendencias(PecasPendecias pecasPendencias) {
        this.pecasPendencias = pecasPendencias;
    }

    public void setFuncionarios(Funcionarios funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public int getQtdePecas() {
        return qtdePecas;
    }

    public void setQtdePecas(int qtdePecas) {
        this.qtdePecas = qtdePecas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pendencias)) {
            return false;
        }
        Pendencias other = (Pendencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.senac.elevcalls.entity.Pendencias[ id=" + id + " ]";
    }

    public void add(Pendencias pendencias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void remove(Pendencias pendencias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
