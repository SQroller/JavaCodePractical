package JavaStreamAPI;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {// Базовый случай
        if (n <= 1) {
            return 1L;
        }

        // Разделение задачи на две подзадачи
        FactorialTask subTask1 = new FactorialTask(n - 1);
        subTask1.fork(); // Асинхронное выполнение подзадачи

        // Выполнение текущей задачи
        long result = n * subTask1.join(); // Получаем результат подзадачи

        return result;
    }
}
