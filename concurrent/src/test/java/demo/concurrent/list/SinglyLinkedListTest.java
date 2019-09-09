package demo.concurrent.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 单向链表的测试
 *
 * @author leishiguang
 * @since v1.0
 */
class SinglyLinkedListTest {

    SinglyLinkedList<String> stringList;

    @BeforeEach
    void beforeEach(){
        stringList = new SinglyLinkedList<>();
        stringList.addAtEnd("0");
        stringList.addAtEnd("1");
        stringList.addAtEnd("2");
        stringList.addAtEnd("3");
        stringList.addAtEnd("4");
    }

    @Test
    void addAtStart() {
        SinglyLinkedList<String> stringList = new SinglyLinkedList<>();
        stringList.addAtStart("1");
        stringList.addAtStart("2");
        stringList.addAtStart("3");
        stringList.addAtStart("4");
        stringList.printAll();
    }

    @Test
    void addAtEnd() {
        SinglyLinkedList<String> stringList = new SinglyLinkedList<>();
        stringList.addAtEnd("1");
        stringList.addAtEnd("2");
        stringList.addAtEnd("3");
        stringList.addAtEnd("4");
        stringList.printAll();
    }


    @Test
    void delete() {
        stringList.printAll();
        stringList.delete(0);
        stringList.printAll();
        stringList.delete(2);
        stringList.printAll();
        stringList.delete(2);
        stringList.printAll();
    }

    @Test
    void get(){
        stringList.printAll();
        assertEquals("0",stringList.get(0));
        assertEquals("2",stringList.get(2));
        assertEquals("4",stringList.get(4));
    }

    @Test
    void reverseWithNew(){
        SinglyLinkedList<String> newList = stringList.reverseWithNew();
        newList.printAll();
    }
}