package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by burak on 11/26/2016.
 */
class TrieNode {
    private char key;
    private boolean isEnd;
    private Map<Character, TrieNode> children;
    // Initialize your data structure here.
    public TrieNode(char key) {
        this.key = key;
        children = new HashMap<>();
        isEnd = false;
    }

    public TrieNode() {
        this.key = ' ';
        children = new HashMap<>();
        isEnd = false;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curNode = this.root;
        for (char c : word.toCharArray()) {
            Map<Character, TrieNode> children = curNode.getChildren();
            if (!children.containsKey(c)) {
                children.put(c, new TrieNode(c));
            }
            curNode = curNode.getChildren().get(c);
        }
        curNode.setEnd(true);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curNode = this.root;
        for (char c : word.toCharArray()) {
            Map<Character, TrieNode> children = curNode.getChildren();
            if (!children.containsKey(c)) {
                return false;
            }
            curNode = curNode.getChildren().get(c);
        }
        if (curNode.isEnd()) {
            return true;
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode curNode = this.root;
        for (char c : prefix.toCharArray()) {
            Map<Character, TrieNode> children = curNode.getChildren();
            if (!children.containsKey(c)) {
                return false;
            }
            curNode = curNode.getChildren().get(c);
        }
        return true;
    }
}

// Your leetcode.Trie object will be instantiated and called as such:
// leetcode.Trie trie = new leetcode.Trie();
// trie.insert("somestring");
// trie.search("key");
