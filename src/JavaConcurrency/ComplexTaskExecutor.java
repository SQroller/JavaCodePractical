package JavaConcurrency;

import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final CyclicBarrier barrier;
    private final ExecutorService executor;
    private final int numberOfTasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;

        // Создание CyclicBarrier с указанным количеством потоков и действием после их синхронизации
        this.barrier = new CyclicBarrier(numberOfTasks, () -> {
            // Это действие выполняется, когда все потоки достигли барьера
            System.out.println("All tasks completed. Now combining results.");
        });

        // Создание пула потоков, который будет выполнять задачи
        this.executor = Executors.newFixedThreadPool(numberOfTasks);
    }

    // Метод для выполнения задач
    public void executeTasks(int numberOfTasks) {
        for (int i = 1; i <= numberOfTasks; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    // Выполнение сложной задачи
                    ComplexTask task = new ComplexTask(taskId);
                    task.execute();

                    // Ожидание всех потоков на барьере
                    System.out.println(Thread.currentThread().getName() + " waiting at barrier.");
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Завершение работы пула после выполнения всех задач
        shutdownExecutor();
    }

    // Метод для корректного завершения работы пула потоков
    private void shutdownExecutor() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
