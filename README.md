# E-Commerce System – Fawry Quantum Internship Challenge

This Java application simulates a basic e-commerce system fulfilling the specifications provided in Fawry's Full Stack Development Internship Challenge.

## Features
- Product definition (name, price, quantity, expiry, shipping)
- Add products to cart with quantity limits
- Checkout process includes:
  - Subtotal calculation
  - Shipping fees
  - Total amount
  - Customer balance update
- Error handling:
  - Empty cart
  - Insufficient balance
  - Expired products
  - Out-of-stock products
- Shipment notice based on shippable products

## Example Usage

```java
cart.add(cheese, 2);
cart.add(tv, 3);
cart.add(scratchCard, 1);
checkout(customer, cart);
