package com.sm.bd.branchdelegation.statemachine;

import com.sm.bd.branchdelegation.models.BranchDelegationEntity;
import com.sm.bd.branchdelegation.repositories.BranchDelegationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

@RequiredArgsConstructor
public class BranchDelegationStateChangeInterceptor extends StateMachineInterceptorAdapter<BranchDelegationState, BranchDelegationEvent> {

    private final BranchDelegationRepository branchDelegationRepository;

    @Override
    public void postStateChange(State<BranchDelegationState, BranchDelegationEvent> state, Message<BranchDelegationEvent> message, Transition<BranchDelegationState, BranchDelegationEvent> transition, StateMachine<BranchDelegationState, BranchDelegationEvent> stateMachine, StateMachine<BranchDelegationState, BranchDelegationEvent> rootStateMachine) {

        String appId = String.valueOf(message.getHeaders().getId());

        Optional<BranchDelegationEntity> branchDelegation = branchDelegationRepository.findById(Long.valueOf(appId));

        if (branchDelegation.isPresent()) {
            BranchDelegationEntity branchDelegationEntity = branchDelegation.get();
            branchDelegationEntity.setState(state.getId().name());
            branchDelegationRepository.flush();
        }

    }
}
