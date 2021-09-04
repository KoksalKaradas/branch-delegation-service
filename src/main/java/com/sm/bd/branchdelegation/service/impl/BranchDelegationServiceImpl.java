package com.sm.bd.branchdelegation.service.impl;

import com.sm.bd.branchdelegation.models.BranchDelegationEntity;
import com.sm.bd.branchdelegation.repositories.BranchDelegationRepository;
import com.sm.bd.branchdelegation.service.contract.BranchDelegationService;
import com.sm.bd.branchdelegation.statemachine.BranchDelegationEvent;
import com.sm.bd.branchdelegation.statemachine.BranchDelegationState;
import com.sm.bd.branchdelegation.statemachine.BranchDelegationStateChangeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchDelegationServiceImpl implements BranchDelegationService {

    private final StateMachineFactory<BranchDelegationState, BranchDelegationEvent> stateMachineFactory;
    private final BranchDelegationStateChangeInterceptor stateChangeInterceptor;
    private final BranchDelegationRepository branchDelegationRepository;

    @Override
    public String send(long appId) {
        return stateChange(appId, BranchDelegationEvent.SEND);
    }

    @Override
    public String returnByManager(long appId) {
        return stateChange(appId, BranchDelegationEvent.RETURN);
    }

    @Override
    public String approve(long appId) {
        return stateChange(appId, BranchDelegationEvent.APPROVE);
    }

    @Override
    public String reject(long appId) {
        return stateChange(appId, BranchDelegationEvent.REJECT);
    }

    private String stateChange(long appId, BranchDelegationEvent event) {

        Optional<BranchDelegationEntity> branchDelegation = branchDelegationRepository.findById(appId);

        if (branchDelegation.isPresent()) {

            BranchDelegationEntity branchDelegationEntity = branchDelegation.get();

            StateMachine<BranchDelegationState, BranchDelegationEvent> stateMachine = build(appId, BranchDelegationState.valueOf(branchDelegationEntity.getState()));

            sendEvent(appId, stateMachine, event);

            return "Success";

        }

        return "Failure";

    }

    private StateMachine<BranchDelegationState, BranchDelegationEvent> build(long appId, BranchDelegationState state) {

        StateMachine<BranchDelegationState, BranchDelegationEvent> stateMachine = stateMachineFactory.getStateMachine(String.valueOf(appId));

        stateMachine.stopReactively();

        stateMachine.getStateMachineAccessor().doWithAllRegions(sma -> {
            sma.addStateMachineInterceptor(stateChangeInterceptor);
            sma.resetStateMachineReactively(new DefaultStateMachineContext<BranchDelegationState, BranchDelegationEvent>(
                    state, null, null, null
            ));
        });

        stateMachine.startReactively();

        return stateMachine;

    }

    private void sendEvent(long appId, StateMachine<BranchDelegationState, BranchDelegationEvent> stateMachine, BranchDelegationEvent event) {

        Message<BranchDelegationEvent> eventMessage = MessageBuilder.withPayload(event)
                .setHeader("appId", appId).build();

        stateMachine.sendEvent(eventMessage);

    }

}
