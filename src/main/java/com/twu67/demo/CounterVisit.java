package com.twu67.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CounterVisit {
  private final Counter counter;

  public CounterVisit(MeterRegistry registry) {
    counter = Counter.builder("countHomePageVisitedTimes.total").description("the counter of home page").register(registry);
  }

  public void Increment() {
    counter.increment();
  }
}
