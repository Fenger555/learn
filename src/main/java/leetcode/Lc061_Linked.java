package leetcode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 *
 * @author Fenger
 * @date 2020-12-24 18:13
 */
public class Lc061_Linked {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode node = head.next;
        ListNode pre=head;
        int len = 1;
        while (node != null) {
            pre = node;
            node = node.next;
            len++;
        }
        pre.next = head;

        for (int i = 0; i < len - k % len; i++) {
            pre = head;
            head=head.next;
        }
        pre.next=null;

        return head;
    }

}
