package JavaStreamAPI;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        //Группировка заказов по продуктам и подсчет общей стоимости
        Map<String, Double> productTotalCosts = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct, // Группируем по имени продукта
                        Collectors.summingDouble(Order::getCost) // Подсчитываем общую стоимость каждого продукта
                ));

        //Сортировка продуктов по убыванию общей стоимости
        List<Map.Entry<String, Double>> sortedProducts = productTotalCosts.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()) // Сортировка по убыванию стоимости
                .collect(Collectors.toList());

        //Выбор трех самых дорогих продуктов
        List<Map.Entry<String, Double>> topThreeProducts = sortedProducts.stream()
                .limit(3) // Выбираем только первые три элемента
                .collect(Collectors.toList());

        //Вывод результатов
        System.out.println("Top 3 expensive products:");
        topThreeProducts.forEach(entry ->
                System.out.println(entry.getKey() + ": " + entry.getValue()));

        // Общая стоимость трёх самых дорогих продуктов
        double totalCostOfTopThree = topThreeProducts.stream()
                .mapToDouble(Map.Entry::getValue)
                .sum();

        System.out.println("Total cost of top 3 expensive products: " + totalCostOfTopThree);
    }
}
