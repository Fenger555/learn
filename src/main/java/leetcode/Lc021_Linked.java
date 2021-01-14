package leetcode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author Fenger
 * @date 2021-01-14 14:00
 */
public class Lc021_Linked {

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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode h = new ListNode(0);
        ListNode p = h;

        while (l1!=null && l2!=null) {
            if (l1.val > l2.val) {
                p.next = l2;
                l2 = l2.next;
            } else {
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
        }

        if (l1 == null) {
            p.next = l2;
        } else {
            p.next = l1;
        }

        return h.next;
    }

    public static void main(String[] args) {
        Lc021_Linked lc = new Lc021_Linked();

        Lc021_Linked.ListNode l3 = lc.new ListNode(8);
        Lc021_Linked.ListNode l2 = lc.new ListNode(5, l3);
        Lc021_Linked.ListNode l1 = lc.new ListNode(2, l2);

        Lc021_Linked.ListNode ll3 = lc.new ListNode(9);
        Lc021_Linked.ListNode ll2 = lc.new ListNode(3, ll3);
        Lc021_Linked.ListNode ll1 = lc.new ListNode(1, ll2);

        ListNode l = lc.mergeTwoLists(l1, ll1);
        System.out.println();
    }

}
