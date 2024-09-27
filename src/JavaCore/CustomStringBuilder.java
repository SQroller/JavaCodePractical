package JavaCore;

import java.util.Stack;

// Снимок (Snapshot), хранящий состояние строки
class StringBuilderSnapshot {
    private final String state;

    public StringBuilderSnapshot(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// Класс CustomStringBuilder с поддержкой undo
public class CustomStringBuilder {
    private StringBuilder stringBuilder;
    private Stack<StringBuilderSnapshot> snapshots; // Стек для хранения состояний

    public CustomStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.snapshots = new Stack<>();
    }

    // Метод для сохранения состояния перед изменением
    private void saveState() {
        snapshots.push(new StringBuilderSnapshot(stringBuilder.toString()));
    }

    // Метод undo для восстановления последнего состояния
    public void undo() {
        if (!snapshots.isEmpty()) {
            StringBuilderSnapshot lastSnapshot = snapshots.pop();
            stringBuilder = new StringBuilder(lastSnapshot.getState());
            System.out.println("Состояние откатилось к: " + stringBuilder.toString());
        } else {
            System.out.println("Нет сохраненных состояний для отката.");
        }
    }

    // Метод append с сохранением предыдущего состояния
    public CustomStringBuilder append(String str) {
        saveState();
        stringBuilder.append(str);
        return this;
    }

    // Метод insert с сохранением предыдущего состояния
    public CustomStringBuilder insert(int offset, String str) {
        saveState();
        stringBuilder.insert(offset, str);
        return this;
    }

    // Метод delete с сохранением предыдущего состояния
    public CustomStringBuilder delete(int start, int end) {
        saveState();
        stringBuilder.delete(start, end);
        return this;
    }

    // Метод для получения текущего состояния строки
    public String toString() {
        return stringBuilder.toString();
    }

    // Пример других методов StringBuilder

    // Метод reverse с сохранением предыдущего состояния
    public CustomStringBuilder reverse() {
        saveState();
        stringBuilder.reverse();
        return this;
    }

    // Метод replace с сохранением предыдущего состояния
    public CustomStringBuilder replace(int start, int end, String str) {
        saveState();
        stringBuilder.replace(start, end, str);
        return this;
    }

    // Метод setCharAt с сохранением предыдущего состояния
    public void setCharAt(int index, char ch) {
        saveState();
        stringBuilder.setCharAt(index, ch);
    }

    // Метод substring (не меняет состояние, просто возвращает подстроку)
    public String substring(int start, int end) {
        return stringBuilder.substring(start, end);
    }

    // Метод length для получения длины строки
    public int length() {
        return stringBuilder.length();
    }

    // Метод для очистки строки с сохранением предыдущего состояния
    public void clear() {
        saveState();
        stringBuilder.setLength(0);
    }
}

