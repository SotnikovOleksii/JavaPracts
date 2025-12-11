module order.storage {
    requires order.base; // Потрібен доступ до класу Order
    exports repository;  // Дозволяємо іншим бачити репозиторій
}