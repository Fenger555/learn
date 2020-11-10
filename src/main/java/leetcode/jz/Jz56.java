package leetcode.jz;

/**
 * @Author gaoxing
 * @Date 2020-06-18 16:52
 */
public class Jz56 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode v1 = new ListNode(2);
        ListNode v2 = new ListNode(3);
        ListNode v3 = new ListNode(4);
        ListNode v4 = new ListNode(5);
        ListNode v5 = new ListNode(6);
        ListNode v6 = new ListNode(7);

        head.next = v1;
        v1.next = v2;
        v2.next = v3;
        v3.next = v4;
        v4.next = v5;
        v5.next = v2;


//        ListNode listNode = deleteDuplication(head.next);


        ListNode p = entryNodeOfLoop(head);

        System.out.println();

    }

    public static ListNode entryNodeOfLoop(ListNode pHead)
    {
        ListNode p1 = pHead;
        ListNode p2 = pHead;
        while (p2!=null
                && p2.next!=null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                break;
            }
        }

        if (p2==null || p2.next==null) {
            return null;
        }

        p1 = pHead;
        while (p1!=p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    public static ListNode deleteDuplication(ListNode pHead)
    {

        if (pHead.next == null) {
            return pHead;
        }

        if (pHead.next.val == pHead.val) {
            pHead.next = pHead.next.next;
            return deleteDuplication(pHead);
        } else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }

    }

    // 1 -> 2 -> 2
    //      2 -> 2
    //           2
    //      2
    // 1 -> 2
    public static ListNode deleteDuplication1(ListNode pHead)
    {

        if (pHead.next == null) {
            return pHead;
        }

        if (pHead.next.val == pHead.val) {
            pHead.next = pHead.next.next;
            return deleteDuplication(pHead);
        } else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }

    }

    public static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

}
