package com.sm.bd.branchdelegation.controller.impl;

import com.sm.bd.branchdelegation.controller.contract.BranchDelegationController;
import com.sm.bd.branchdelegation.models.BranchDelegationRequestModel;
import com.sm.bd.branchdelegation.service.contract.BranchDelegationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BranchDelegationControllerImpl implements BranchDelegationController {

	private final BranchDelegationService branchDelegationService;

	@Override
	public String send(BranchDelegationRequestModel requestModel) {
		return branchDelegationService.send(requestModel.getAppId());
	}

	@Override
	public String returnByManager(BranchDelegationRequestModel requestModel) {
		return branchDelegationService.returnByManager(requestModel.getAppId());
	}

	@Override
	public String approve(BranchDelegationRequestModel requestModel) {
		return branchDelegationService.approve(requestModel.getAppId());
	}

	@Override
	public String reject(BranchDelegationRequestModel requestModel) {
		return branchDelegationService.reject(requestModel.getAppId());
	}

}
