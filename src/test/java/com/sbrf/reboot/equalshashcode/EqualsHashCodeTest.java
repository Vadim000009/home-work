package com.sbrf.reboot.equalshashcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EqualsHashCodeTest {

    class Car {
        String model;
        String color;
        Calendar releaseDate;
        int maxSpeed;

        @Override
        public boolean equals(Object obj) {
            // Сравнение для защиты от NullPointerException (потому что обработчики много кушают, и лучше всего, когда все обработки через if)
            //Рефлексивность: объект должен равняться самому себе
            if (obj == this) return true;
            // Проверка на null
            if ( obj == null) return false;
            // Проверка на соответствие классу
            if (obj.getClass() != this.getClass()) return false;

            Car objCar = (Car) obj;
            return  maxSpeed == objCar.maxSpeed && (model == objCar.model || model.equals(objCar.model))
                    && (color == objCar.color || color.equals(objCar.color))
                    && (releaseDate == objCar.releaseDate || releaseDate.equals(objCar.releaseDate));
        }

        @Override
        public int hashCode(){
            final int base = 13;
            int result = 1;

            int hashCarColor = (null == color) ? 0 : color.hashCode();
            int hashCarModel = (null == model) ? 0 : model.hashCode();
            int hashCarCalendar = (null == releaseDate) ? 0 : releaseDate.hashCode();
            // Можно через цикл, но думаю, что такое выполнение потребует меньше кроцессорнго времени, ну плюс ко всему
            // это же хэш, сюда редко смотрят)
            result = base * result + hashCarModel;
            result = base * result + hashCarColor;
            result = base * result + hashCarCalendar;
            result = base * result + maxSpeed;
            return result;
        }
    }

    @Test
    public  void assertTrueEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;


        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void successEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertEquals(car1.hashCode(),car2.hashCode());

    }

    @Test
    public void failEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertNotEquals(car1.hashCode(),car2.hashCode());

    }

    /*  тест на null    */
    @Test
    public void trueNullEqualsTest(){
        Car car = new Car();
        car.model = "Mitsubishi";
        car.color = "black";
        car.releaseDate = new GregorianCalendar(2009, 4, 30);
        car.maxSpeed = 200;

        Assertions.assertFalse(car.equals(null));
    }

    /*  Тест на рефлексию   */
    @Test
    public void trueReflexEqualsTest(){
        Car car = new Car();
        car.model = "Mitsubishi";
        car.color = "black";
        car.releaseDate = new GregorianCalendar(2009, 4, 30);
        car.maxSpeed = 200;

        Assertions.assertTrue(car.equals(car));
    }

    /*  Тест на симметрию   */
    @Test
    public void trueSymmetryEqualsTest(){
        Car car1 = new Car();
        Car car2 = new Car();

        car1.model = "Dodge";
        car1.color = "red";
        car1.releaseDate = new GregorianCalendar(2022, 1, 18);
        car1.maxSpeed = 160;

        car2.model = "Dodge";
        car2.color = "red";
        car2.releaseDate = new GregorianCalendar(2022, 1, 18);
        car2.maxSpeed = 160;

        Assertions.assertEquals(car1.equals(car2),car2.equals(car1));
    }

    /*  Тест на транзитивность  */
    @Test
    public void trueTransitivityEqualsTest(){
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        car1.model = "Volvo";
        car1.color = "blue";
        car1.releaseDate = new GregorianCalendar(2000, 1, 18);
        car1.maxSpeed = 120;

        car2.model = "Volvo";
        car2.color = "blue";
        car2.releaseDate = new GregorianCalendar(2000, 1, 18);
        car2.maxSpeed = 120;

        car3.model = "Volvo";
        car3.color = "blue";
        car3.releaseDate = new GregorianCalendar(2000, 1, 18);
        car3.maxSpeed = 120;

        Assertions.assertEquals(car1.equals(car2) == car2.equals(car3),car3.equals(car1));
    }

    /*  Согласованность   */
    @Test
    public void trueConsistencyEqualsTest(){
        Car car1 = new Car();
        Car car2 = new Car();

        car1.model = "Volvo";
        car1.color = "blue";
        car1.releaseDate = new GregorianCalendar(2000, 1, 18);
        car1.maxSpeed = 120;

        car2.model = "Volvo";
        car2.color = "blue";
        car2.releaseDate = new GregorianCalendar(2000, 1, 18);
        car2.maxSpeed = 120;

        // Два объекта равны - true, пока значения в переменных этих объектах равны, но как только
        // одна из переменных в объекте примет другое значение, то false
        for (int i= 0; i < 30; i++){
            if (!car1.equals(car2))
                Assertions.assertNotEquals(car1, car2);
            if (i == 20)
                car2.maxSpeed = 200;
        }
    }
}
