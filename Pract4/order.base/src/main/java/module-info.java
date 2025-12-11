module order.base {
    requires static lombok;
    requires javafaker;
    requires java.sql;

    exports products;
    exports order;
    exports factory;
}