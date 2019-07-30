package org.dms.mocktime;

import java.lang.management.ManagementFactory;

public class MillisProvider {

  private static final int NANOS_IN_MS = 1000000;

  private long initialInstant;

  private long nanoTimeInitial;

  public MillisProvider() {
    this(ManagementFactory.getRuntimeMXBean().getStartTime());
  }

  public long getInitialInstant() {
    return initialInstant;
  }

  private MillisProvider(final long initialInstant) {
    this.initialInstant = initialInstant;
    this.nanoTimeInitial = System.nanoTime() / NANOS_IN_MS;
  }

  public long currentTimeMillis() {
    long nanoOffset = (System.nanoTime() / NANOS_IN_MS) - this.nanoTimeInitial;
    return getInitialInstant() + nanoOffset;
  }

}
