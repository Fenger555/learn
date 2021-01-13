package leetcode;

/**
 * 反转一个单链表。
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author Fenger
 * @date 2021-01-13 18:02
 */
public class Lc206_Linked {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {

        ListNode p = null;
        ListNode c = head;
        ListNode tmp;

        while (c != null) {
            tmp = c.next;
            c.next = p;
            p = c;
            c = tmp;
        }
        return p;
    }
}
