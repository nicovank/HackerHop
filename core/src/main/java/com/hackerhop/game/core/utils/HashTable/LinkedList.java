package com.hackerhop.game.core.utils.HashTable;

import com.hackerhop.game.core.objects.Platform;

import java.util.NoSuchElementException;

public class LinkedList {
    Node head;

    public LinkedList() {
        head = null;
    }

    /**
     * Attempts to add Platform <code>p</code> to this LinkedList.
     * <code>p</code> will not be added if this LinkedList already contains a Node with a Platform that is within
     * a specified distance threshold from <code>p</code>.
     *
     * @param p the Platform object to be added
     * @return <code>true</code> if <code>p</code> was added, <code>false</code> if otherwise
     * @see com.hackerhop.game.core.objects.Platform#distanceTo(Platform)
     */
    public boolean add(Platform p) {
        if (head != null) {
            return head.add(p);
        } else {
            head = new Node(p);
            return true;
        }
    }

    /**
     * Removes a Node contaiing Platform <code>p</code>.
     * @param p Platform to be removed
     */
    public void remove(Platform p) {
        if (head != null) {
            head = head.remove(p);
        }
    }

    /**
     * Looks for and returns Platform <code>p</code> such that <code>p.hashCode() == hash</code>
     * if <code>p</code> is contained in this list.
     *
     * @param hash the hashcode of the target Platform
     * @return Platform <code>p</code> or null
     */
    public Platform getPlatform(int hash){
        return head.getNode(hash).platform;
    }

    private class Node {
        Platform platform;
        Node next;

        public Node(Platform p) {
            this.platform = p;
            this.next = null;
        }

        /**
         * Attempts to add Platform <code>p</code> to this Node.
         * <p>If <code>p</code> is within a certain distance from <code>this.platform</code>,
         * <code>p</code> is not added and the method returns <code>false</code>.
         * </p>
         * <p>If <code>p</code> is outside the threshold, then:
         * <ul>
         * <li>If <code>this.next == null</code>, a new Node is instantiated with <code>p</code>
         * as its parameter.</li>
         * <li>Otherwise, <code>p</code> gets passed onto <code>this.next</code>.</li>
         * </ul>
         * </p>
         *
         * @param p the platform object to be added
         * @return <code>true</code> if a new Node was created, <code>false</code> if otherwise
         */
        boolean add(Platform p) {
            if (this.platform.distanceTo(p) > 250) {   // p is outside distance threshold
                if (next != null) {     // Node has child
                    return next.add(p);// attempt add on child
                } else {                    // Node has no child
                    next = new Node(p);
                    return true;    // add p, add successful
                }
            } else {                                // p is inside distance threshold
                return false;       // add failed
            }
        }

        /**
         * <p>Removes a Node containing Platform <code>p</code>.</p>
         * <p>If this Node contains <code>p</code> the next Node is returned.
         * If this Node does not contain <code>p</code>, and if <code>next</code>
         * is not <code>null</code>, <code>p</code> is passed on to <code>next</code> and
         * this Node is returned. if <code>next</code> is <code>null</code>, this Node is returned.
         * </p>
         *
         * @param p the Platform object to be removed
         * @return this Node, the next Node, or <code>null</code>
         */
        Node remove(Platform p) {
            if (this.platform.hashCode() != p.hashCode()) {
                return next;
            } else {
                if (next != null) {
                    next = next.remove(p);
                }
                return this;
            }
        }

        /**
         * <p>Returns a Node containing Platform <code>p</code> such that <code>p.hashCode() == hash</code></p>
         * <p>If the current Node does not contain such <code>p</code>,
         *  the method calls <code>getNode</code> on <code>next</code>,
         *  and returns its return value.</p>
         *
         * @param hash the hashcode of the Platform object to look for
         * @return Node object containing Platform <code>p</code>, or <code>null</code>
         */
        Node getNode(int hash) {
            if (this.platform.hashCode() != hash) {
                if (next != null) {
                    return next.getNode(hash);
                } else {
                    return null;
                }
            } else {
                return this;
            }
        }
    }
}
