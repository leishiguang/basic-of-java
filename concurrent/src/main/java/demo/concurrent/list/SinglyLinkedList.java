package demo.concurrent.list;

/**
 * 单向链表的实现（非线程安全）
 *
 * @author leishiguang
 * @since v1.0
 */
public class SinglyLinkedList<T> {

    /**
     * 头节点
     */
    private Node<T> head;

    /**
     * 末尾节点
     */
    private Node<T> end;

    private Integer length;

    /**
     * 链表中的节点
     *
     * @param <T> 链表内的数据格式
     */
    static class Node<T> {
        Node<T> next = null;
        T data;

        Node(T data) {
            this.data = data;
        }
    }

    public SinglyLinkedList() {
        this.head = new Node<>(null);
        this.end = this.head;
        this.head.next = this.end;
        this.length = 0;
    }

    /**
     * 在链表的最开头增加一个节点
     */
    public void addAtStart(T t) {
        Node<T> newNode = new Node<>(t);
        newNode.next = head.next;
        head.next = newNode;
        this.length += 1;
    }

    /**
     * 在链表的最末尾增加一个节点
     */
    public void addAtEnd(T t) {
        Node<T> newNode = new Node<>(t);
        end.next = newNode;
        end = newNode;
        this.length += 1;
    }

    /**
     * 打印所有节点
     */
    public void printAll() {
        System.out.println("链表总长度:" + length);
        Node<T> nowNode = head;
        for (int i = 0; i < length; i++) {
            nowNode = nowNode.next;
            System.out.print(nowNode.data+" ");
        }
        System.out.print("\n");
    }


    /**
     * 获取链表长度
     */
    public Integer getLength() {
        return this.length;
    }

    /**
     * 删除第 index 个节点，第一个节点的 index 为 0
     *
     * @param index 节点所在顺序
     */
    public void delete(Integer index) {
        this.checkIndexOutOfBound(index);
        Node<T> wantDeleteLastNode = head;
        for (int i = 0; i < index; i++) {
            wantDeleteLastNode = wantDeleteLastNode.next;
        }
        wantDeleteLastNode.next = wantDeleteLastNode.next.next;
        this.length -= 1;
    }

    public T get(Integer index){
        this.checkIndexOutOfBound(index);
        Node<T> wantNode = head;
        for (int i = 0; i <= index; i++) {
            wantNode = wantNode.next;
        }
        return wantNode.data;
    }

    /**
     * 反转链表，每个节点拷贝为新的
     */
    public SinglyLinkedList<T> reverseWithNew(){
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        Node<T> nowNode = head;
        for (int i = 0; i < length; i++) {
            nowNode = nowNode.next;
            newList.addAtStart(nowNode.data);
        }
        return newList;
    }


    private void checkIndexOutOfBound(Integer index){
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
    }

}
