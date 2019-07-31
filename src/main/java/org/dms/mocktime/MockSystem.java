package org.dms.mocktime;

import mockit.Mock;
import mockit.MockUp;

/**
 * This jmockit mock class allows a test to execute starting at a given instant of time in the past
 * or in the future.
 * <p/>
 * From jmockit 1.43 and for compatibility with java 9 and greater, the only way to make this work
 * is to apply the mockup ("fake") for the whole test run, by setting the fakes system property
 * (-Dfakes=your.fully.qualified.name.SystemMock) in the command line or Maven/Gradle test execution
 * configuration.
 * <p/>
 * To be able to change the initial time several times during the runtime, the behavior of setting
 * the initial time is moved to the collaborator class {@link MillisProvider}.
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
