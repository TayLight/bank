package com.bank.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountId implements Serializable {
    private static final long serialVersionUID = -215841970190933671L;
    @Column(name = "id_contract", nullable = false)
    private Integer idContract;

    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_client", nullable = false)
    private Integer idClient;

    public Integer getIdContract() {
        return idContract;
    }

    public void setIdContract(Integer idContract) {
        this.idContract = idContract;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccountId entity = (AccountId) o;
        return Objects.equals(this.idClient, entity.idClient) &&
                Objects.equals(this.idContract, entity.idContract) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, idContract, id);
    }

}