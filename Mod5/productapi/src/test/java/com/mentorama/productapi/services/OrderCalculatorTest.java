package com.mentorama.productapi.services;

import com.mentorama.productapi.models.Order;
import com.mentorama.productapi.models.OrderItem;
import com.mentorama.productapi.models.Product;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderCalculatorTest {

    private OrderCalculator orderCalculator;

    //criando so uma instancia do Order Calculator para todos os testes
    public OrderCalculatorTest() {
        System.out.println("Construtor");
        this.orderCalculator = new OrderCalculator();
    }

    //criando instancias do order calculator para cada teste
    @BeforeEach
    void setUp() {
        System.out.println("Execucao de testes antes de cada um");
//        this.orderCalculator = new OrderCalculator();
    }

    @BeforeAll //pode ser utilizado qdo utilizar BD, por exemplo.
    static void SetUpAll() {
        System.out.println("Before All");
    }

    @AfterAll //pode ser utilizado para fechar conexoes de classes estaticas, por exemplo.
    static void afterAll() {
        System.out.println("After All");
    }

    @AfterEach // por exemplo, mock de um bd e que a cada interacao essa base seja totalmente limpa
    void afterEach() {
        System.out.println("After Each");
    }

    @Test
    public void shouldCalculateTotalOrderPrice() {
        final Order order = new Order(aListOfOrderItems());
        Double result = orderCalculator.calculateOrder(order);
        assertEquals(30.0, result);
    }

    @Test
    public void shouldCalulateTotalOfMultipleOrders() {
        final List<Order> orders =
                Arrays.asList(
                        new Order(aListOfOrderItems()),
                        new Order(aListOfOrderItems())
                );
        final Double result = orderCalculator.calculateMultipleOrders(orders);
        assertEquals(60.0, result);
    }

//    private List<OrderItem> aListOfOrderItems() {
//        return Arrays.asList(
//                anOrderItem(2, 0.0, 10.0, 0.10),
//                anOrderItem(10, 0.0, 1.0, 0.10)
//        );
//    }
    private List<OrderItem> aListOfOrderItems() {
        return Arrays.asList(
                anOrderItem(20.0),
                anOrderItem(10.0)
        );
    }

//    private OrderItem anOrderItem(final Integer quantity, final Double discount, final Double price, final Double maxDiscountPercentage) {
//        Product product = new Product(1L, "Test", price, maxDiscountPercentage);
//        return new OrderItem(product, quantity, discount);
//    }

    //refatorando esse metodo pra utilizar o mockito
    private OrderItem anOrderItem(final Double expectedValue) {
        Product product = mock(Product.class);
        when(product.getPriceWithDiscount(Mockito.anyDouble())).thenReturn(expectedValue);
        OrderItem orderItem =  mock(OrderItem.class);
        when(orderItem.totalPrice()).thenReturn(expectedValue);
        return orderItem;
    }


}