package com.sm.bd.branchdelegation.configuration;

import com.sm.bd.branchdelegation.statemachine.BranchDelegationEvent;
import com.sm.bd.branchdelegation.statemachine.BranchDelegationState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class BranchDelegationStateMachineConfiguration extends StateMachineConfigurerAdapter<BranchDelegationState, BranchDelegationEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<BranchDelegationState, BranchDelegationEvent> states) throws Exception {
        states.withStates()
                .initial(BranchDelegationState.FIRST_USER)
                .states(EnumSet.allOf(BranchDelegationState.class))
                .end(BranchDelegationState.COMPLETED)
                .end(BranchDelegationState.REJECTED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BranchDelegationState, BranchDelegationEvent> transitions) throws Exception {
        transitions
                .withExternal().source(BranchDelegationState.FIRST_USER) //
                .event(BranchDelegationEvent.SEND)
                .target(BranchDelegationState.MANAGER)
                .and()
                .withExternal().source(BranchDelegationState.MANAGER)
                .event(BranchDelegationEvent.RETURN)
                .target(BranchDelegationState.WAITING)
                .and()
                .withExternal().source(BranchDelegationState.WAITING)
                .event(BranchDelegationEvent.SEND)
                .target(BranchDelegationState.MANAGER)
                .and()
                .withExternal().source(BranchDelegationState.MANAGER)
                .event(BranchDelegationEvent.APPROVE)
                .target(BranchDelegationState.COMPLETED)
                .and()
                .withExternal().source(BranchDelegationState.MANAGER)
                .event(BranchDelegationEvent.REJECT)
                .target(BranchDelegationState.REJECTED);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<BranchDelegationState, BranchDelegationEvent> config) throws Exception {

        StateMachineListener<BranchDelegationState, BranchDelegationEvent> listener = new StateMachineListenerAdapter<BranchDelegationState, BranchDelegationEvent>() {

            @Override
            public void stateChanged(State<BranchDelegationState, BranchDelegationEvent> from, State<BranchDelegationState, BranchDelegationEvent> to) {
                System.out.println("state changed from " + from + "to " + to);
            }
        };

        config.withConfiguration().listener(listener);

    }

}
