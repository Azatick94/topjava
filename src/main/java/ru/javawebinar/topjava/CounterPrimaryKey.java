package ru.javawebinar.topjava;


/**
 * Simple Counter realization, Analog for DataBase AUTO_INCREMENT functionality.
 */
public class CounterPrimaryKey {

    private static CounterPrimaryKey instance;
    private static Integer counter = 0;

    private CounterPrimaryKey() {
    }

    public static synchronized CounterPrimaryKey getInstance() {
        if (instance == null) {
            instance = new CounterPrimaryKey();
        }
        return instance;
    }

    public Integer getCounter() {
        counter++;
        return counter;
    }


}
