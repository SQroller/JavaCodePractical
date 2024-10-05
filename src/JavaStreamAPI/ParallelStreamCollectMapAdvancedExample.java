package JavaStreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ParallelStreamCollectMapAdvancedExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        // Использование Parallel Stream для обработки данных и вычисления средних оценок по предметам
        ConcurrentMap<String, Double> averageGrades = students.parallelStream()
                .flatMap(student -> student.getGrades().entrySet().stream())  // Получаем поток всех предметов и оценок
                .collect(Collectors.groupingByConcurrent(
                        Map.Entry::getKey,  // Группируем по названию предмета
                        Collectors.averagingDouble(Map.Entry::getValue)  // Рассчитываем среднюю оценку
                ));

        // Вывод результатов
        System.out.println("Average grades per subject:");
        averageGrades.forEach((subject, avgGrade) ->
                System.out.println(subject + ": " + avgGrade));
    }
}
