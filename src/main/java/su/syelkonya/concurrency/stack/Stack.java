package su.syelkonya.concurrency.stack;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

//9) Реализуй неблокирующий стек push/pop через AtomicReference и CAS-цикл, без synchronized.
@Slf4j
public class Stack<T> {

    AtomicReference<Node<T>> currentNodeAtomicReference = new AtomicReference<>(null);

    void push(T value) {
        if (currentNodeAtomicReference.get() == null) {
            currentNodeAtomicReference.compareAndSet(null, new Node<>(value, null));
        } else {
            currentNodeAtomicReference.compareAndSet(
                    currentNodeAtomicReference.get(),
                    new Node<>(value, currentNodeAtomicReference.get())
            );
        }
        log.info("Add Node with Value {}", value);
    }

    T pop() {
        if (currentNodeAtomicReference.get() == null) {
            log.info("NO NODES In Stack");
        }
        if (currentNodeAtomicReference.get().next == null) {
            log.info("Take our last node in stack");
            AtomicReference<Node<T>> returnNode = currentNodeAtomicReference;
            currentNodeAtomicReference.compareAndSet(currentNodeAtomicReference.get(), null);
            log.info("Return Node with value {}", returnNode.get().value);
            return returnNode.get().value;
        } else {
            AtomicReference<Node<T>> returnNode = currentNodeAtomicReference;
            currentNodeAtomicReference.compareAndSet(currentNodeAtomicReference.get(), currentNodeAtomicReference.get().next);
            log.info("Return Node with value {}", returnNode.get().value);
            return returnNode.get().value;
        }
    }

}
