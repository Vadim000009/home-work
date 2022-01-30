package com.sbrf.reboot.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, что бы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов что бы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {

        List<String> students = null;

        /*
        *   Ответ
        * Для вставки по индексу отлично подойдёт List, а именно его реализация в виде LinkedList
        * LinkedList - Это двусвязный массив, тем самым, для вставки в начало, мы получаем указатель
        * на первый элемент массива, и вставляем его в начало нашего нового, нулевого элемента.
        * Для других реализаций List будет происходить смещение (которое будет затрачивать время)
        * Сложность для LinkedList = О(1), для остальных = O(n-1)
         */

        students = new LinkedList<>();
        students.add("Иванов");
        students.add("Петров");
        students.add("Сидоров");
        students.add(0,"Козлов");

        assertEquals(4, students.size());
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет что бы тест завершился успешно.
     */
    @Test
    public void addMoneyToBox() {

        Set<Integer> moneyBox = null;

        /*
        *   Ответ
        * Для хранения уникальных монет (значения) можно использовать set
        * А так как порядок здесь не важен (хотя наверное, у коллекционера они в красивой книжке),
        * то допустимо использование HashSet
         */

        moneyBox = new HashSet<>();
        for (int i = 0; i < 10; i++){
            moneyBox.add(i);
        }

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется виртуальная книжная полка.
     * Периодически вы получаете книгу для чтения.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги что бы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        class Book {
            String bookName;
            Book(String bookName) {
                this.bookName = bookName;
            }
        }

        Book book1 = new Book("Книга А");
        Book book2 = new Book("Книга Б");
        Book book3 = new Book("Книга В");

        List<Book> bookshelf = null;
        bookshelf = Arrays.asList(book1,book2,book3);

        /*
        *   Ответ
        * Если обращение к книгам происходит по их мету на полке (индексу) - ArrayList
        * Если обращение по названию книги - HashMap
         */

        assertEquals(3, bookshelf.size());
    }

    /*
     * Задача 5+.
     * Требуется хранить список контактов, у каждого контакта (ФИО) может быть сколько угодно номеров
     * Периодически трубется получать список номеров у любого из контактов, обращаясь по имени контакта
     * Имена контактов должны хранится в отсортированном по алфавиту (лексографически)
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете для организации списка контактов.
     * */
    @Test
    public void addPhoneBook(){

        class Contact{
            String name;
            Set<String> numbers;

            Contact(String name){
                this.name = name;
            }
        }

        /*
         *  Ответ
         * Поскольку имена требуется хранить в отсортированном виде, а при обращении по имени
         * нужно получить список номеров этого контакта, то возможна следующая реализация
         * TreeMap<String (ФИО), Set<String> (номера)>
         * Можно ещё попробовать использовать номер как массив чисел, принимающие значения от 0 до 9,
         * но это уже каждый сам для себя решает
         */

        Contact contactBashirov = new Contact("Баширов");
        Contact contactPetrov = new Contact("Петров");
        Set<String> phoneBookBashirov = new HashSet<>();
        Set<String> phoneBookPetrov = new HashSet<>();

        phoneBookBashirov.add("+79990000000");
        phoneBookBashirov.add("+79991111111");
        phoneBookPetrov.add("+79992222222");
        phoneBookPetrov.add("+79993333333");

        contactBashirov.numbers = phoneBookBashirov;
        contactPetrov.numbers = phoneBookPetrov;

        Map<String,Set<String>> phoneBook = new TreeMap<>();
        phoneBook.put(contactBashirov.name,contactBashirov.numbers);
        phoneBook.put(contactPetrov.name,contactPetrov.numbers);

        assertEquals(2,phoneBook.size());
    }

}