package com.bank.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "id_client", referencedColumnName = "id_client"),
            @JoinColumn(name = "id_contract", referencedColumnName = "id_contract"),
            @JoinColumn(name = "id_account", referencedColumnName = "id")
    })
    private Account account;

    @Column(name = "id_operation")
    private Integer idOperation;

    public Integer getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
