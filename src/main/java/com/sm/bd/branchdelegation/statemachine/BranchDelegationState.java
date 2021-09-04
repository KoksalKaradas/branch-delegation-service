package com.sm.bd.branchdelegation.statemachine;

public enum BranchDelegationState {
    FIRST_USER,
    MANAGER,
    WAITING,
    COMPLETED,
    REJECTED
}