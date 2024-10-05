package JavaConcurrency;

import java.util.concurrent.TimeUnit;

public class ComplexTask {
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    // Метод, который выполняет часть сложной задачи
    public void execute() {
        System.out.println("Task " + taskId + " is being executed by " + Thread.currentThread().getName());
        try {
            // Симуляция работы
            TimeUnit.SECONDS.sleep((long) (Math.random() * 3 + 1)); // Работа занимает случайное время от 1 до 3 секунд
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
