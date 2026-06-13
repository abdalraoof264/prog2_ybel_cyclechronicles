package cyclechronicles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class ShopAcceptTest {

  private Order createOrder(Type type, String customer) {
    Order order = mock(Order.class);

    when(order.getBicycleType()).thenReturn(type);
    when(order.getCustomer()).thenReturn(customer);

    return order;
  }

  @Test
  void acceptRaceBike() {
    Shop shop = new Shop();
    Order order = createOrder(Type.RACE, "Ali");

    boolean result = shop.accept(order);

    assertTrue(result);
  }

  @Test
  void acceptSingleSpeedBike() {
    Shop shop = new Shop();
    Order order = createOrder(Type.SINGLE_SPEED, "Ali");

    boolean result = shop.accept(order);

    assertTrue(result);
  }

  @Test
  void acceptFixieBike() {
    Shop shop = new Shop();
    Order order = createOrder(Type.FIXIE, "Ali");

    boolean result = shop.accept(order);

    assertTrue(result);
  }

  @Test
  void rejectGravelBike() {
    Shop shop = new Shop();
    Order order = createOrder(Type.GRAVEL, "Ali");

    boolean result = shop.accept(order);

    assertFalse(result);
  }

  @Test
  void rejectEBike() {
    Shop shop = new Shop();
    Order order = createOrder(Type.EBIKE, "Ali");

    boolean result = shop.accept(order);

    assertFalse(result);
  }

  @Test
  void rejectOrderWhenCustomerAlreadyHasOpenOrder() {
    Shop shop = new Shop();

    Order firstOrder = createOrder(Type.RACE, "Ali");
    Order secondOrder = createOrder(Type.FIXIE, "Ali");

    boolean firstResult = shop.accept(firstOrder);
    boolean secondResult = shop.accept(secondOrder);

    assertTrue(firstResult);
    assertFalse(secondResult);
  }

  @Test
  void acceptOrderWhenFourOrdersAreAlreadyOpen() {
    Shop shop = new Shop();

    shop.accept(createOrder(Type.RACE, "A"));
    shop.accept(createOrder(Type.RACE, "B"));
    shop.accept(createOrder(Type.RACE, "C"));
    shop.accept(createOrder(Type.RACE, "D"));

    boolean result = shop.accept(createOrder(Type.RACE, "E"));

    assertTrue(result);
  }

  @Test
  void rejectOrderWhenFiveOrdersAreAlreadyOpen() {
    Shop shop = new Shop();

    shop.accept(createOrder(Type.RACE, "A"));
    shop.accept(createOrder(Type.RACE, "B"));
    shop.accept(createOrder(Type.RACE, "C"));
    shop.accept(createOrder(Type.RACE, "D"));
    shop.accept(createOrder(Type.RACE, "E"));

    boolean result = shop.accept(createOrder(Type.RACE, "F"));

    assertFalse(result);
  }
}
