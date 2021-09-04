package com.sm.bd.branchdelegation.controller.contract;

import com.sm.bd.branchdelegation.models.BranchDelegationRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/bd")
public interface BranchDelegationController {
	
	@PostMapping(value = "/send")
	@ResponseStatus(HttpStatus.OK)
	String send(@RequestBody BranchDelegationRequestModel requestModel);

	@PostMapping(value = "/return")
	@ResponseStatus(HttpStatus.OK)
	String returnByManager(@RequestBody BranchDelegationRequestModel requestModel);

	@PostMapping(value = "/approve")
	@ResponseStatus(HttpStatus.OK)
	String approve(@RequestBody BranchDelegationRequestModel requestModel);

	@PostMapping(value = "/reject")
	@ResponseStatus(HttpStatus.OK)
	String reject(@RequestBody BranchDelegationRequestModel requestModel);

}
