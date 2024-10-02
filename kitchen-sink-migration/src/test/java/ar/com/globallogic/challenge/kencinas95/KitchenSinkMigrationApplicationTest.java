package ar.com.globallogic.challenge.kencinas95;

import ar.com.globallogic.challenge.kencinas95.configs.MockConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(MockConfiguration.class)
public class KitchenSinkMigrationApplicationTest {
    @Autowired
    private ApplicationContext context;
    @Test
    void testContexApplicationLoadsSuccess() {
        Assertions.assertNotNull(context);
    }
}
