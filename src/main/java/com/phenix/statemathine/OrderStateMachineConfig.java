package com.phenix.statemathine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

import static com.phenix.statemathine.OrderEvents.PAY;
import static com.phenix.statemathine.OrderStates.UNPAID;
import static com.phenix.statemathine.OrderStates.WAITING_FOR_RECEIVE;

@Configuration
@EnableStateMachine
@Slf4j
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStates, OrderEvents> {
    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
        states.withStates()
                .initial(UNPAID)
                .states(EnumSet.allOf(OrderStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
        transitions.withExternal()
                .source(UNPAID).target(WAITING_FOR_RECEIVE)
                .event(PAY)
                .and()
                .withExternal()
                .source(WAITING_FOR_RECEIVE).target(OrderStates.DONE)
                .event(OrderEvents.RECEIVE);
    }

//    @Override
//    public void configure(StateMachineConfigurationConfigurer<OrderStates, OrderEvents> config) throws Exception {
//        config.withConfiguration().listener(listener());
//    }
//
//    @Bean
//    public StateMachineListener<OrderStates, OrderEvents> listener() {
//        return new StateMachineListenerAdapter<OrderStates, OrderEvents>() {
//            @Override
//            public void transition(Transition<OrderStates, OrderEvents> transition) {
//                if (transition.getTarget().getId() == UNPAID) {
//                    log.info("订单创建，待支付");
//                    return;
//                }
//
//                if (transition.getSource().getId() == UNPAID
//                        && transition.getTarget().getId() == WAITING_FOR_RECEIVE) {
//                    log.info("用户完成支付，待收货");
//                    return;
//                }
//
//                if (transition.getSource().getId() == WAITING_FOR_RECEIVE
//                        && transition.getTarget().getId() == OrderStates.DONE) {
//                    log.info("用户已收货，订单完成");
//                    return;
//                }
//            }
//        };
//    }
}
