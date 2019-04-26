package test.pivotal.pal.calc;

import io.pivotal.pal.calc.WelcomeController;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WelcomeControllerTest {

    @Test
    public void itSaysHello() throws Exception {
        WelcomeController controller = new WelcomeController("A welcome message");

        assertThat(controller.sayHello()).isEqualTo("A welcome message");
    }
}