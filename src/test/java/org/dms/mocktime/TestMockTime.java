package org.dms.mocktime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import mockit.Mock;
import mockit.MockUp;

public class TestMockTime {
  private static final Logger L = LoggerFactory.getLogger(TestMockTime.class);

  @Before
  public void dateBefore() {
    L.info("Date before test {}", new Date());
  }

  @Test
  public void testTicking() throws Exception {
    new MockUp<MillisProvider>() {
      @Mock
      long getInitialInstant() {
        return 1234567890000l;
      }

    };
    L.info("Date in test {}", new Date());
    Thread.sleep(1000);
    L.info("Date in test {}", new Date());
    Thread.sleep(1000);
    L.info("Date in test {}", new Date());
  }

  @Test
  public void testNonTicking() throws Exception {
    new MockUp<System>() {
      @Mock
      long currentTimeMillis() {
        return 1234567890000l;
      }
    };
    L.info("Date in test {}", new Date());
    Thread.sleep(1000);
    L.info("Date in test {}", new Date());
    Thread.sleep(1000);
    L.info("Date in test {}", new Date());
  }

  @After
  public void dateAfter() {
    L.info("Date after test {}", new Date());
  }

}
