public class Node<T> {
    T value;
    Node<T> next;

    public Node(T values,Node<T> next){
        this.value = values;
        this.next = next;
    }
}
