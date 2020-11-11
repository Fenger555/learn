package leetcode;

/**
 * @Author gaoxing
 * @Date 2020-11-11 17:04
 *
 * 判断链表是否有环
 *
 */
public class Lc141_Linked {

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode p1 = head;
        ListNode p2 = head;
        do {
            p1 = p1.next;
            p2 = p2.next;
            if (p1 == null || p2 == null || p2.next == null) {
                return false;
            }
            p2 = p2.next;
        } while (p1 != p2);
        return true;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
