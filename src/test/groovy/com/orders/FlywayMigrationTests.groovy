package com.orders

import com.orders.models.Customer
import com.orders.models.Item
import com.orders.models.Order
import com.orders.models.Payment
import com.orders.repositories.OrderRepository
import org.flywaydb.test.annotation.FlywayTest
import org.flywaydb.test.junit.FlywayTestExecutionListener
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.hamcrest.core.Is.is
import static org.hamcrest.number.IsCloseTo.closeTo

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrdersApplication.class)
@Rollback
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class])
@FlywayTest
class FlywayMigrationTests {

    @Autowired
    JdbcTemplate template

    @Autowired
    OrderRepository orderRepository

    @FlywayTest(invokeCleanDB = true)
    @Test
    void testRunsTestMigration() {
        assertThat(template.queryForObject(
                "SELECT name FROM customers WHERE last_name = 'TestLastName'", String.class),
                is("TestName"))
    }

    @Test
    void testRepositoryFindAll() {
        assertThat(
                orderRepository.findAll(),
                hasItem(hasProperty(
                        "totalAmount",
                        closeTo(123.32, 0.01))))
    }

    @Test
    void testRepositoryInsert() {
        orderRepository.save(new Order(
                customer: new Customer(),
                itemsPurchased: [
                        new Item(
                                name: "book",
                                amount: 40.0
                        ),
                        new Item(
                                name: "rechargeable batteries",
                                amount: 60.0
                        )
                ],
                paymentsReceived: [new Payment(amount: 100)],
                totalAmount: 9867.12
        ))

        assertThat(
                orderRepository.findAll(),
                hasItem(allOf(
                        hasProperty(
                                "totalAmount",
                                closeTo(9867.12, 0.01)),
                        hasProperty(
                                "itemsPurchased",
                                hasSize(2))
                )))
    }
}
