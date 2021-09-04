package com.sm.bd.branchdelegation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BRANCH_DELEGATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class BranchDelegationEntity {

    @Id
    @Column(name="APP_ID")
    private Long appId;

    @Column(name="STATE")
    private String state;

}
