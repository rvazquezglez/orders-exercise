package com.orders.controllers

import com.orders.OrdersApplication
import com.orders.models.Order
import com.orders.repositories.OrderRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static org.mockito.Matchers.any
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("testProfile")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OrdersApplication.class)
@SpringBootTest
class OrderControllerTest {

    MockMvc mockMvc

    @Autowired
    WebApplicationContext webApplicationContext

    @Autowired
    OrderRepository orderRepository

    @Before
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    void testAllOrders() {
        when(orderRepository.findAll()).thenReturn([new Order()])

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(1)))
                .andDo(print())
    }

    @Test
    void testSaveOrder() {
        when(orderRepository.save(any())).thenReturn(new Order(id: 123))

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/order/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content('{"id":null,"customer":null,"totalAmount":100.0,"date":null,"itemsPurchased":[{"id":1,"name":"book","amount":100.0}],"paymentsReceived":null}')
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id').value(123))
                .andDo(print())
    }

    @Test
    void testInvalidOrder() throws Exception {
        when(orderRepository.save(any())).thenReturn(new Order(id: 123))

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/order/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content('{"id":null,"customer":null,"totalAmount":0.0,"date":null,"itemsPurchased":null,"paymentsReceived":null}')
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print())
    }
}