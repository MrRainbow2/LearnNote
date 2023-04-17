package com.sankuai.test.algorithm.structure.list.single;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/24 14:15
 */
public class 链表题 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    /**
     * LeetCode https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     * <p>
     * 图示两个链表在节点 c1 开始相交：
     */
    class Problem1 {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == headB) {
                return headA;
            }
            ListNode listATemp = headA;
            ListNode listBTemp = headB;

            int listASize = 1;
            int listBSize = 1;
            while (listATemp != null && listATemp.next != null) {
                listASize++;
                listATemp = listATemp.next;
            }
            while (listBTemp != null && listBTemp.next != null) {
                listBSize++;
                listBTemp = listBTemp.next;
            }
            if (listATemp != listBTemp) {
                return null;
            }
            int stepSub = Math.abs(listASize - listBSize);
            listATemp = headA;
            listBTemp = headB;
            if (listASize >= listBSize) {
                listATemp = move(listATemp, stepSub);
            } else {
                listBTemp = move(listBTemp, stepSub);
            }
            while (listATemp != null && listBTemp != null) {
                if (listATemp == listBTemp) {
                    return listATemp;
                }
                listATemp = listATemp.next;
                listBTemp = listBTemp.next;
            }
            return null;
        }

        public ListNode move(ListNode head, int step) {
            for (int i = 0; i < step; i++) {
                head = head.next;
            }
            return head;
        }
    }


    /**
     * 链表相加
     * header 为 个位，依次相加
     */
    class Problem2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int sizeL1 = getListSize(l1);
            int sizeL2 = getListSize(l2);
            ListNode longerList = sizeL1 >= sizeL2 ? l1 : l2;
            ListNode shorterList = longerList == l1 ? l2 : l1;
            ListNode header = longerList;
            ListNode LongerPre = null;
            int crash = 0;
            while (shorterList != null) {
                int curVal = shorterList.val + longerList.val + crash;
                longerList.val = curVal % 10;
                crash = curVal / 10;
                shorterList = shorterList.next;
                LongerPre = longerList;
                longerList = longerList.next;
            }

            while (longerList != null) {
                int curVal = longerList.val + crash;
                longerList.val = curVal % 10;
                crash = curVal / 10;
                LongerPre = longerList;
                longerList = longerList.next;
            }
            if (crash > 0) {
                LongerPre.next = new ListNode(crash);
            }
            return header;
        }

        public int getListSize(ListNode list) {
            if (list == null) {
                return 0;
            }
            int count = 0;
            while (list != null) {
                count++;
                list = list.next;
            }
            return count;
        }
    }


    /**
     * 回文链表
     * 不用栈实现，反转链表。
     */
    class Problem3 {

        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return false;
            }
            if (head.next == null) {
                return true;
            }
            ListNode slowPoint = head.next;
            ListNode fastPoint = head.next.next;
            while (fastPoint != null && fastPoint.next != null) {
                slowPoint = slowPoint.next;
                fastPoint = fastPoint.next.next;
            }
            ListNode reverseListHeader = reverseList(slowPoint);
            ListNode leftPointer = head;
            ListNode rightPointer = reverseListHeader;
            while (rightPointer != null && leftPointer != null) {
                if (rightPointer.val != leftPointer.val) {
                    return false;
                }
                rightPointer = rightPointer.next;
                leftPointer = leftPointer.next;
            }
            reverseList(reverseListHeader);
            return true;
        }


        public ListNode reverseList(ListNode header) {
            if (header == null || header.next == null) {
                return header;
            }
            ListNode next;
            ListNode cur = header;
            ListNode pre = null;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }

    /**
     * 复制带有random指针的链表；
     */
    class Problem4 {

    }

    /**
     * 合并k个有序链表
     * <p>
     * 给你一个链表数组，每个链表都已经按升序排列。
     * <p>
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * LeetCode 23
     */
    class Problem5 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null) {
                return null;
            }
            PriorityQueue<ListNode> minRootHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
            for (int idx = 0; idx < lists.length; idx++) {
                if (lists[idx] != null) {
                    minRootHeap.add(lists[idx]);
                }
            }
            if (minRootHeap.isEmpty()) {
                return null;
            }
            ListNode header = minRootHeap.poll();
            if (header.next != null) {
                minRootHeap.add(header.next);
            }
            ListNode curNode = header;
            while (!minRootHeap.isEmpty()) {
                ListNode curParam = minRootHeap.poll();
                curNode.next = curParam;
                if (curParam.next != null) {
                    minRootHeap.add(curParam.next);
                }
                curNode = curNode.next;
            }
            return header;
        }
    }

    /**
     * 给定一个链表，判断是否有环。
     * 如果有环，返回第一个入环节点
     * <p>
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * <p>
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/linked-list-cycle-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Problem6 {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) {
                return null;
            }
            ListNode slowPointer = head.next;
            ListNode fastPointer = head.next.next;
            while (slowPointer != fastPointer) {
                if (fastPointer.next == null || fastPointer.next.next == null) {
                    return null;
                }
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;
            }
            fastPointer = head;
            while (slowPointer != fastPointer) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next;
            }
            return slowPointer;
        }
    }

    /**
     * 两数相加，链表头为高位
     * LeetCode
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/add-two-numbers-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Problem7 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = addNormalList(reverseList(l1), reverseList(l2));
            return reverseList(result);
        }

        /**
         * 将两个头节点为个为的链表相加。
         *
         * @return
         */
        public ListNode addNormalList(ListNode l1, ListNode l2) {
            int size1 = getListSize(l1);
            int size2 = getListSize(l2);
            ListNode longerListNode = size1 >= size2 ? l1 : l2;
            ListNode shorterListNode = longerListNode == l1 ? l2 : l1;
            ListNode lastNode = longerListNode;
            ListNode result = longerListNode;
            int crash = 0;
            while (shorterListNode != null) {
                int cur = shorterListNode.val + longerListNode.val + crash;
                longerListNode.val = cur % 10;
                crash = cur / 10;
                lastNode = longerListNode;
                shorterListNode = shorterListNode.next;
                longerListNode = longerListNode.next;
            }
            while (longerListNode != null) {
                int cur = longerListNode.val + crash;
                longerListNode.val = cur % 10;
                crash = cur / 10;
                lastNode = longerListNode;
                longerListNode = longerListNode.next;
            }
            if (crash > 0) {
                lastNode.next = new ListNode(1);
            }
            return result;
        }

        public ListNode reverseList(ListNode listNode) {
            if (listNode == null || listNode.next == null) {
                return listNode;
            }
            ListNode pre = null;
            ListNode cur = listNode;
            ListNode next;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

        public int getListSize(ListNode list) {
            if (list == null) {
                return 0;
            }
            int count = 0;
            while (list != null) {
                count++;
                list = list.next;
            }
            return count;
        }
    }


    public static void printList(ListNode l) {
        while (l != null) {
            System.out.print(l.val);
            l = l.next;
        }
    }

    public static void main(String[] args) {
        //999

        ListNode node2 = new ListNode(2, new ListNode(0));
        node2.next.next = new ListNode(-4, node2);
        ListNode list1 = new ListNode(3, node2);
        //1
        ListNode result = new 链表题().new Problem6().detectCycle(list1);
        printList(result);
    }
}

