package njackson.com.android;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UnitTestApplication {
    @Test
    public void TestOne(){
        assertThat("10", is("10"));

    }
}