package map.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author gaoxing
 * @Date 2020-11-11 10:51
 */
public class LruCache<K, V> {

    int cap;

    Map<K, DLinkedNode<V>> lruMap;

    DLinkedNode head;
    DLinkedNode tail;

    public V get(K k) {
        DLinkedNode<V> node = lruMap.get(k);
        if (node == null) return null;
        moveToFirst(node);
        return node.v;
    }

    public void put(K k, V v) {
        DLinkedNode<V> node = lruMap.get(k);
        if (node == null) {
            if (lruMap.size() >= cap) {
                removeLast();
                lruMap.remove(k);
            }
            addFirst(v);
        } else {
            node.v = v;
        }
    }

    public LruCache(int cap) {
        this.head = new DLinkedNode<>();
        this.tail = new DLinkedNode<>();
        this.cap = cap;
        lruMap = new HashMap<>(cap*4/3);
    }

    private void moveToFirst(DLinkedNode node) {
        remove(node);
        addFirst(node);
    }

    private void addFirst(DLinkedNode node) {
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    private void addFirst(V v) {
        DLinkedNode<V> node = new DLinkedNode<>(v);
        addFirst(node);
    }

    private void removeLast() {
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
    }

    private void remove(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private class DLinkedNode<T> {
        T v;
        DLinkedNode next;
        DLinkedNode pre;

        public DLinkedNode() {
        }

        public DLinkedNode(T v) {
            this.v = v;
        }
    }

}
