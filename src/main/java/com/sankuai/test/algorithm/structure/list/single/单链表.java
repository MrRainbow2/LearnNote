package com.sankuai.test.algorithm.structure.list.single;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2022/10/12 22:03
 */
public class 单链表 {
    static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static Node buildNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Node head = new Node(arr[0], null);
        Node cur = head;
        for (int idx = 1; idx < arr.length; idx++) {
            Node node = new Node(arr[idx], null);
            cur.next = node;
            cur = cur.next;
        }
        return head;
    }

    public static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node next;
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void printList(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Node list = buildNode(arr);
        printList(list);
        Node reverseList = reverseList(list);
        printList(reverseList);
    }
}
