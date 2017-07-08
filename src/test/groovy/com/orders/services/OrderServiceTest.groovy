package com.orders.services

import com.orders.OrdersApplication
import com.orders.models.Order
import com.orders.repositories.OrderRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static org.hamcrest.core.IsNot.not
import static org.hamcrest.core.IsSame.sameInstance
import static org.mockito.Matchers.any
import static org.mockito.Mockito.when
import static org.hamcrest.MatcherAssert.assertThat

@ActiveProfiles("testProfile")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OrdersApplication.class)
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService

    @Autowired
    OrderRepository orderRepository

    @Before
    void setup() {
        when(orderRepository.findAll()).thenAnswer({ invocation -> [new Order()] })
        when(orderRepository.save(any())).thenReturn(new Order())
    }

    @Test
    void testCache() {
        def firstOrder = orderService.findAll().first()
        def secondOrder = orderService.findAll().first()

        assertThat(firstOrder, sameInstance(secondOrder))
    }

    @Test
    void testEvictCache() {
        def firstOrder = orderService.findAll().first()
        orderService.save(new Order())
        def secondOrder = orderService.findAll().first()

        assertThat(firstOrder, not(sameInstance(secondOrder)))
    }
}
