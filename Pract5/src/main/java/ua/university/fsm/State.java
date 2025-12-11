package ua.university.fsm;

public enum State {
    S,      // Початковий стан
    ONE,    // Стан 1 (знайдено T)
    TWO,    // Стан 2 (знайдено TE)
    THREE,  // Стан 3 (знайдено TES)
    F       // Стан F (Final - знайдено TEST)
}