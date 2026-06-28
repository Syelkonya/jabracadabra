package su.syelkonya.concurrency.stack;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

//9) Реализуй неблокирующий стек push/pop через AtomicReference и CAS-цикл, без synchronized.
@Slf4j
public class Stack<T> {

    AtomicReference<Node<T>> currentNodeAtomicReference = new AtomicReference<>(null);

    void push(T value) {
        while (true) {
            Node<T> current = currentNodeAtomicReference.get();
            Node<T> newNode = new Node<>(value, current);
            if (currentNodeAtomicReference.compareAndSet(current, newNode)) {
//                log.info("Add Node with Value {}", value);
                break;
            }
            log.info("COMPARE AND SET - FALSE IN PUSH {} - {}", current.value, newNode.value);
        }
    }

    T pop() {
        while (true) {
            Node<T> current = currentNodeAtomicReference.get();
            if (current == null) {
//                log.info("NO NODES In Stack");
                return null;
            }
            Node<T> next = current.next;
            if (currentNodeAtomicReference.compareAndSet(current, next)) {
//                log.info("Return Node with value {}", current.value);
                return current.value;
            }
            log.info("COMPARE AND SET - FALSE IN POP {} - {}", current.value, next.value);
        }
    }

}
