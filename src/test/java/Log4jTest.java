import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4jTest {

    private static final Logger log = LogManager.getLogger(Log4jTest.class);

    @Test
    public void test01(){
        log.traceEntry("====Test Info Start====");
        for (int i = 0; i < 1000000; i++) {
            log.info("Test Info by "+i);
        }
        log.traceEntry("=====Test Info end=====");
    }

}