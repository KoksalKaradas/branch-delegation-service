package com.sm.bd.branchdelegation.controller.impl;

import com.sm.bd.branchdelegation.controller.contract.BranchDelegationController;
import com.sm.bd.branchdelegation.service.contract.BranchDelegationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BranchDelegationControllerImpl implements BranchDelegationController {

	private final BranchDelegationService branchDelegationService;

	@Override
	public String send(long appId) {
		return branchDelegationService.send(appId);
	}

	@Override
	public String returnByManager(long appId) {
		return branchDelegationService.returnByManager(appId);
	}

	@Override
	public String approve(long appId) {
		return branchDelegationService.approve(appId);
	}

	@Override
	public String reject(long appId) {
		return branchDelegationService.reject(appId);
	}

}
