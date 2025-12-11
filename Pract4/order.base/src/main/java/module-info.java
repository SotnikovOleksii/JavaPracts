module order.base {
    requires static lombok;
    requires javafaker;
    requires java.sql; // <--- ДОДАЙ ЦЕЙ РЯДОК

    exports products;
    exports order;
    exports factory;
}