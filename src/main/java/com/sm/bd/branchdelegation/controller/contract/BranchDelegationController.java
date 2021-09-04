package com.sm.bd.branchdelegation.controller.contract;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bd")
public interface BranchDelegationController {
	
	@PostMapping(value = "/send")
	@ResponseStatus(HttpStatus.OK)
	String send(@RequestBody long appId);

	@PostMapping(value = "/return")
	@ResponseStatus(HttpStatus.OK)
	String returnByManager(@RequestBody long appId);

	@PostMapping(value = "/approve")
	@ResponseStatus(HttpStatus.OK)
	String approve(@RequestBody long appId);

	@PostMapping(value = "/reject")
	@ResponseStatus(HttpStatus.OK)
	String reject(@RequestBody long appId);

}
