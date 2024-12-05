module projectDVN {
    requires spring.context;
    requires spring.beans;
    requires java.sql;
    requires org.slf4j;

    opens Restaurant;
    opens Restaurant.entities;
    opens Restaurant.config;
    opens Restaurant.views;
    opens Restaurant.services;
    opens Restaurant.repositories;






}