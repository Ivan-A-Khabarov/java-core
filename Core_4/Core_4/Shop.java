package Core_4;

import java.util.Arrays;

public class Shop {
    public enum Gender {
        MALE,
        FEMALE,
        UNSPECIFIED
    }

    public enum Holiday {
        NONE,
        NEW_YEAR,
        WOMENS_DAY,
        MEN_DAY
    }

    public static class ShopException extends RuntimeException {
        ShopException(String message) {
            super(message);
        }
    }

    public static class CustomerException extends ShopException {
        CustomerException(Customer customer) {
            super("Unknown customer: " + customer);
        }
    }

    public static class ProductException extends ShopException {
        ProductException(Item item) {
            super("Unknown item: " + item);
        }
    }

    public static class AmountException extends ShopException {
        AmountException(int amount) {
            super("Invalid amount: " + amount + " (must be 1-100)");
        }
    }

    static class Customer {
        private String name;
        private int age;
        private String phone;
        private Gender gender;

        public Customer(String name, int age, String phone, Gender gender) {
            this.name = name;
            this.age = age;
            this.phone = phone;
            this.gender = gender;
        }

        // Геттеры и сеттеры
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return String.format("%s (%d, %s, %s)", name, age, phone, gender);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Customer customer = (Customer) obj;
            return age == customer.age &&
                    name.equals(customer.name) &&
                    phone.equals(customer.phone) &&
                    gender == customer.gender;
        }
    }

    static class Item {
        private String name;
        private int cost;

        public Item(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("%s ($%d)", name, cost);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Item item = (Item) obj;
            return cost == item.cost && name.equals(item.name);
        }
    }

    static class Order {
        private Customer customer;
        private Item item;
        private int amount;

        public Order(Customer customer, Item item, int amount) {
            this.customer = customer;
            this.item = item;
            this.amount = amount;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return String.format("%s bought %d of %s", customer, amount, item);
        }
    }

    private static final Customer[] CUSTOMERS = {
            new Customer("Ivan", 20, "+1-222-333-44-55", Gender.MALE),
            new Customer("Petr", 30, "+2-333-444-55-66", Gender.MALE),
            new Customer("Anna", 25, "+3-444-555-66-77", Gender.FEMALE),
            new Customer("Maria", 35, "+4-555-666-77-88", Gender.FEMALE)
    };

    private static final Item[] ITEMS = {
            new Item("Ball", 100),
            new Item("Sandwich", 1000),
            new Item("Table", 10000),
            new Item("Car", 100000),
            new Item("Rocket", 10000000)
    };

    private static Order[] orders = new Order[5];

    private static boolean contains(Object[] array, Object obj) {
        for (Object item : array) {
            if (item.equals(obj)) return true;
        }
        return false;
    }

    public static Order makePurchase(Customer customer, Item item, int amount) {
        if (!contains(CUSTOMERS, customer)) {
            throw new CustomerException(customer);
        }
        if (!contains(ITEMS, item)) {
            throw new ProductException(item);
        }
        if (amount < 1 || amount > 100) {
            throw new AmountException(amount);
        }
        return new Order(customer, item, amount);
    }

    public static void congratulateCustomers(Customer[] customers, Holiday holiday) {
        System.out.println("\n--- Праздничные поздравления ---");
        for (Customer customer : customers) {
            switch (holiday) {
                case NEW_YEAR:
                    System.out.printf("Уважаемый(ая) %s! Поздравляем вас с Новым Годом!\n", customer.getName());
                    break;
                case WOMENS_DAY:
                    if (customer.getGender() == Gender.FEMALE) {
                        System.out.printf("Уважаемая %s! Поздравляем вас с 8 Марта!\n", customer.getName());
                    }
                    break;
                case MEN_DAY:
                    if (customer.getGender() == Gender.MALE) {
                        System.out.printf("Уважаемый %s! Поздравляем вас с 23 Февраля!\n", customer.getName());
                    }
                    break;
                case NONE:
                    System.out.println("Сегодня нет праздников");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // Тестирование поздравлений
        System.out.println("=== Тестирование системы поздравлений ===");
        congratulateCustomers(CUSTOMERS, Holiday.NEW_YEAR);
        congratulateCustomers(CUSTOMERS, Holiday.WOMENS_DAY);
        congratulateCustomers(CUSTOMERS, Holiday.MEN_DAY);
        congratulateCustomers(CUSTOMERS, Holiday.NONE);

        // Тестирование покупок
        System.out.println("\n=== Тестирование системы покупок ===");
        Object[][] testCases = {
                {CUSTOMERS[0], ITEMS[0], 1},    // valid
                {CUSTOMERS[1], ITEMS[1], -1},   // invalid amount
                {CUSTOMERS[0], ITEMS[2], 150},  // invalid amount
                {CUSTOMERS[1], new Item("Flower", 10), 1}, // unknown item
                {new Customer("Fedor", 40, "+3-444-555-66-77", Gender.MALE), ITEMS[3], 1} // unknown customer
        };

        int orderCount = 0;
        for (Object[] testCase : testCases) {
            try {
                if (orderCount < orders.length) {
                    orders[orderCount] = makePurchase(
                            (Customer) testCase[0],
                            (Item) testCase[1],
                            (int) testCase[2]
                    );
                    orderCount++;
                    System.out.println("Успешная покупка: " + orders[orderCount-1]);
                }
            } catch (ProductException e) {
                System.out.println("Ошибка продукта: " + e.getMessage());
            } catch (AmountException e) {
                System.out.println("Ошибка количества: " + e.getMessage());
                try {
                    // Пробуем купить 1 единицу при ошибке количества
                    orders[orderCount] = makePurchase(
                            (Customer) testCase[0],
                            (Item) testCase[1],
                            1
                    );
                    orderCount++;
                    System.out.println("Покупка с исправленным количеством: " + orders[orderCount-1]);
                } catch (ShopException ex) {
                    System.out.println("Не удалось исправить покупку: " + ex.getMessage());
                }
            } catch (CustomerException e) {
                System.out.println("Ошибка покупателя: " + e.getMessage());
            } finally {
                System.out.println("Текущее количество заказов: " + orderCount);
            }
        }

        System.out.println("\n=== Итоговый список заказов ===");
        for (int i = 0; i < orderCount; i++) {
            System.out.println((i+1) + ". " + orders[i]);
        }
    }
}