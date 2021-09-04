package com.sm.bd.branchdelegation.service.contract;

import org.springframework.stereotype.Service;

@Service
public interface BranchDelegationService {

    String send(long appId);

    String returnByManager(long appId);

    String approve(long appId);

    String reject(long appId);

}
