import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList<T>{
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
        if(head==null){
            throw new NoSuchElementException();
        }
        Node<T> current = head;
        if(head == tail){
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
    @Override
    public T get(int index){
        if(index==0){
            if(head==null)return null;
            return head.value;
        }else{
            Node<T> current = head;
            for(int j = 0; j<index;j++){
                if(current.next == null) return null;
                current = current.next;
            }
            return current.value;
        }
    }
    @Override
    public int size(){
        if(head==null){
            return 0;
        }else{
            Node<T> current = head;
            int i = 1;
            while (current.next != null){
                current = current.next;
                i++;
            }
            return i;
        }
    }

    @Override
    public boolean add(T t){
        addLast(t);
        return true;
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            private Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T result = current.value;
                current=current.next;
                return result;
            }
        };
    }
    @Override
    public Stream<T> stream(){
        Stream.Builder<T> builder = Stream.builder();
        for(T value : this){
            builder.accept(value);
        }
        return builder.build();
    }

    public static <T> List<T> filterByExactClass(List<T> list, Class<?> clazz) {
              List<T> result = new ArrayList<>();
            for (T item : list) {
                if (item != null && item.getClass().equals(clazz)) {
                    result.add(item);
                }
            }
            return result;
    }

    public static <T> List<T> filterByHierarchy(List<T> list, Class<?> clazz) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (clazz.isInstance(item)) {
                result.add(item);
            }
        }
        return result;
    }


    public static <T extends Comparable<T>> long countInRange(List<T> list, T min, T max) {
        Predicate<T> isInRange = x -> x.compareTo(min) > 0 && x.compareTo(max) < 0;

        return list.stream()
               .filter(isInRange)
               .count();
    }
}

