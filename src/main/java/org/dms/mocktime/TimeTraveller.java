package org.dms.mocktime;

import mockit.Mock;
import mockit.MockUp;

public abstract class TimeTraveller {

  public static void timeTravel(final long initialInstant) {
    new MockUp<MillisProvider>() {
      @Mock
      long getInitialInstant() {
        return initialInstant;
      }
    };
  }

}
