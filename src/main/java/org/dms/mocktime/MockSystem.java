package org.dms.mocktime;

import mockit.Mock;
import mockit.MockUp;

/**
 * This mock class allows a test to execute starting at a given instant of time in the past or in
 * the future. As we cannot call the same method we are mocking if that method is native, we make
 * use of {@link System#nanoTime()} to calculate the offset. Note it is a much more costly operation
 * than {@link System#currentTimeMillis()}.
 */
public class MockSystem extends MockUp<System> {

  private MillisProvider millisProvider;

  private MockSystem() {
    this.millisProvider = new MillisProvider();
  }

  @Mock
  public long currentTimeMillis() {
    return millisProvider.currentTimeMillis();
  }

}
