package org.dms.mocktime;

import java.lang.management.ManagementFactory;
import mockit.MockUp;

/**
 * This class provides a ticking clock implementation which does not rely on
 * {@link System#currentTimeMillis()} so that it can be used as a collaborator of {@link MockSystem}
 * <p/>
 * The intended way of changing the time is creating a {@link MockUp} to fake the initial instant.
 *
 * <pre>
 * new MockUp&lt;MillisProvider&gt;() {
 *   &#064;Mock
 *   long getInitialInstant() {
 *     return 1234567890000l;
 *   }
 * };
 * </pre>
 *
 */
public class MillisProvider {

  private static final int NANOS_IN_MS = 1000000;

  private long initialInstant;

  private long nanoTimeInitial;

  /**
   * By default the initial instant is the time at which the VM started, so the mocked clock will be
   * close to real.
   */
  public MillisProvider() {
    // need to get the start time without using System.currentTimeMillis
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
