package com.orders

import org.springframework.jdbc.core.JdbcTemplate
import org.flywaydb.test.annotation.FlywayTest
import org.flywaydb.test.junit.FlywayTestExecutionListener
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener


import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.core.Is.is

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrdersApplication.class)
@Rollback
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class])
@FlywayTest
class FlywayMigrationTests {

    @Autowired
    JdbcTemplate template

    @FlywayTest(invokeCleanDB = true)
    @Test
    void testRunsTestMigration() {
        assertThat(template.queryForObject(
                "SELECT name FROM customers WHERE last_name = 'TestLastName'", String.class),
                is("TestName"))
    }
}
