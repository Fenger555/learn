package leetcode;

/**
 * @Author gaoxing
 * @Date 2020-11-11 16:03
 *
 * 查找两个链表相交节点
 *
 */
public class Lc160_Linked {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode pa = headA;
        ListNode pb = headB;

        while (pa!=pb) {
            System.out.println(pa.val+" - "+pb.val);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pa = pa.next == null ? headB : pa.next;
            pb = pb.next == null ? headA : pb.next;
        }

        do {

        }while (pa==pb);

        return pa;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode a0 = new ListNode(1);
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(3);
        ListNode a3 = new ListNode(4);

        ListNode b0 = new ListNode(11);
        ListNode b1 = new ListNode(12);
        ListNode b2 = new ListNode(13);
        ListNode b3 = new ListNode(14);

        ListNode n = new ListNode(0);
        ListNode n1 = new ListNode(1000);

        a0.next=a1;
        a1.next=a2;
        a2.next=a3;
        a3.next=n;

        b0.next=b1;
        b1.next=b2;
        b2.next=b3;
        b3.next=n;

        n.next=n1;


        System.out.println(getIntersectionNode(a0, b0).val);



    }

}
