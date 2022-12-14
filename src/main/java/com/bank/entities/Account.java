package com.bank.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Account {

    @EmbeddedId
    private AccountId id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "id_client", referencedColumnName = "id_client", nullable = false),
            @JoinColumn(name = "id_contract", referencedColumnName = "id", nullable = false)
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Contract contract;

    @Column(name = "number", nullable = false)
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public AccountId getId() {
        return id;
    }

    public void setId(AccountId id) {
        this.id = id;
    }
}
