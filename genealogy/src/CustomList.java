import java.util.NoSuchElementException;

public class CustomList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;

    public void addLast(T value){
        Node<T> current = new Node<>(value,null);
        if(head == null){
            head = current;
        }else{
            tail.next = current;
        }
        tail=current;
    }
    public T getLast(){
        try{
            return tail.value;
        }catch (NullPointerException e){
            throw new NoSuchElementException(e);
        }

    }
    public void addFirst(T value){
        Node<T> current = new Node<>(value,head);
        head = current;
        if(tail==null){
            tail = current;

        }
    }
    public T getFirst(){
        try{
            return head.value;
        }catch (NullPointerException e){
            throw new NoSuchElementException(e);
        }
    }

    public T removeFirst(){
        if(head==null) {
            throw new NoSuchElementException();
        }
        Node<T> current = head;
        if(head==tail){
            tail = null;
        }
        head = head.next;
        return current.value;
    }
    public T removeLast(){
        if(tail==null){
            throw new NoSuchElementException();
        }
        Node<T> current = head;
        if(head == null){
            head = null;
            tail = null;
            return current.value;
        }else{
            while (current.next != tail){
                current=current.next;
            }
            T result = tail.value;
            current.next = null;
            tail = current;
            return result;
        }
    }
}
