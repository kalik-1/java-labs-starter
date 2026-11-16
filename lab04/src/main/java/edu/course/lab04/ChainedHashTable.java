package edu.course.lab04;

public class ChainedHashTable {

    private static class Node {
        IssueID key;
        ProjectTask value;
        Node next;

        Node(IssueID key, ProjectTask value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private final Node[] buckets = new Node[16];
    private int size = 0;

    private int index(IssueID key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public void put(IssueID key, ProjectTask value) {
        int i = index(key);
        for (Node cur = buckets[i]; cur != null; cur = cur.next) {
            if (cur.key.equals(key)) {
                cur.value = value;
                return;
            }
        }
        buckets[i] = new Node(key, value, buckets[i]);
        size++;
    }

    public ProjectTask get(IssueID key) {
        for (Node cur = buckets[index(key)]; cur != null; cur = cur.next) {
            if (cur.key.equals(key)) {
                return cur.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }
}
