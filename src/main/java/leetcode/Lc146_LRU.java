package leetcode;

import map.lru.LruCache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author gaoxing
 * @Date 2020-11-11 15:04
 */
public class Lc146_LRU {


    int cap;

    Map<Integer, DLinkedNode<Integer, Integer>> lruMap;

    DLinkedNode head;
    DLinkedNode tail;

    public Integer get(int k) {
        DLinkedNode<Integer, Integer> node = lruMap.get(k);
        if (node == null) return -1;
        moveToFirst(node);
        return node.v;
    }

    public void put(int k, int v) {
        DLinkedNode<Integer, Integer> node = lruMap.get(k);
        if (node == null) {
            if (lruMap.size() >= cap) {
                DLinkedNode lastNode = removeLast();
                lruMap.remove(lastNode.k);
            }
            node = addFirst(k, v);
            lruMap.put(k, node);
        } else {
            node.v = v;
            moveToFirst(node);
        }
    }

    public Lc146_LRU(int cap) {
        this.head = new DLinkedNode<>();
        this.tail = new DLinkedNode<>();
        head.next = tail;
        tail.pre = head;
        this.cap = cap;
        lruMap = new HashMap<>(cap * 4 / 3);
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

    private DLinkedNode addFirst(Integer k, Integer v) {
        DLinkedNode<Integer, Integer> node = new DLinkedNode<>(k, v);
        addFirst(node);
        return node;
    }

    private DLinkedNode removeLast() {
        DLinkedNode lastNode = tail.pre;
        remove(lastNode);
        return lastNode;
    }

    private void remove(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private class DLinkedNode<K, T> {
        K k;
        T v;
        DLinkedNode next;
        DLinkedNode pre;

        public DLinkedNode() {
        }

        public DLinkedNode(K k, T v) {
            this.v = v;
            this.k = k;
        }
    }

    public static void main(String[] args) {
        Lc146_LRU lRUCache = new Lc146_LRU(2);
        lRUCache.put(2, 1); // 缓存是 {1=1}
        lRUCache.put(1, 1); // 缓存是 {1=1, 2=2}
        lRUCache.put(2, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.put(4, 1); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(2));
        System.out.println(lRUCache.get(4));
    }

    private void show() {
        DLinkedNode h = this.head.next;
        System.out.print("head -> ");
        while (h!=tail) {
            System.out.print("["+h.k+":"+h.v+"] -> ");
            h = h.next;
        }
        System.out.println("tail");
    }
}
