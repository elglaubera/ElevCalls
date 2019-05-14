/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.elevcalls.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Glauber
 */
@Entity
public class Chamadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(optional = false)
    private Condominios condominios;
    
    @OneToOne(optional = false)
    private Funcionarios funcionarios;
    
    @OneToOne(optional = false)
    private Login login;

    public Condominios getCondominios() {
        return condominios;
    }

    public void setCondominios(Condominios condominios) {
        this.condominios = condominios;
    }

    public Funcionarios getFuncionarios() {
        return funcionarios;
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

    public String getHoraChamada() {
        return horaChamada;
    }

    public void setHoraChamada(String horaChamada) {
        this.horaChamada = horaChamada;
    }

    public String getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(String horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

    public String getDescricaoDefeito() {
        return descricaoDefeito;
    }

    public void setDescricaoDefeito(String descricaoDefeito) {
        this.descricaoDefeito = descricaoDefeito;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public int getTelSolicitante() {
        return telSolicitante;
    }

    public void setTelSolicitante(int telSolicitante) {
        this.telSolicitante = telSolicitante;
    }
    
    private String horaChamada;
    private String horaEnvio;
    private String descricaoDefeito;
    private String nomeSolicitante;
    private int telSolicitante;

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
        if (!(object instanceof Chamadas)) {
            return false;
        }
        Chamadas other = (Chamadas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.senac.elevcalls.entity.Chamadas[ id=" + id + " ]";
    }
    
}
