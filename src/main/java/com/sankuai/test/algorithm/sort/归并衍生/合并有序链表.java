package com.sankuai.test.algorithm.sort.归并衍生;

import com.sun.tools.javac.tree.Pretty;

import javax.security.auth.login.CredentialException;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/24 11:08
 */
public class 合并有序链表 {
    public static class ListNode {
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

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
            ListNode header;
            if (list1.val <= list2.val) {
                header = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                header = new ListNode(list2.val);
                list2 = list2.next;
            }
            ListNode cur = header;
            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    cur.next = new ListNode(list1.val, null);
                    cur = cur.next;
                    list1 = list1.next;
                } else {
                    cur.next = new ListNode(list2.val, null);
                    cur = cur.next;
                    list2 = list2.next;
                }
            }
            while (list1 != null) {
                cur.next = new ListNode(list1.val, null);
                cur = cur.next;
                list1 = list1.next;
            }
            while (list2 != null) {
                cur.next = new ListNode(list2.val, null);
                cur = cur.next;
                list2 = list2.next;
            }
            return header;
        }

        public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
            PriorityQueue<ListNode> minRootHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
            minRootHeap.add(list1);
            minRootHeap.add(list2);
            ListNode cur = minRootHeap.poll();
            ListNode resultHead = new ListNode(cur.val);
            if (cur.next != null) {
                minRootHeap.add(cur.next);
            }
            ListNode currentNode = resultHead;
            while (!minRootHeap.isEmpty()) {
                cur = minRootHeap.poll();
                currentNode.next = new ListNode(cur.val);
                currentNode = currentNode.next;
                if (cur.next != null) {
                    minRootHeap.add(cur.next);
                }
            }
            return resultHead;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(2,new ListNode(4,new ListNode(6,new ListNode(8,new ListNode(10)))));
        ListNode list2 = new ListNode(1,new ListNode(3,new ListNode(5,new ListNode(7,new ListNode(9)))));
        ListNode result = new 合并有序链表().new Solution().mergeTwoLists2(list1, list2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
