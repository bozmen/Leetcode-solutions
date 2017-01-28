package leetcode;

import java.util.Arrays;

/**
 * Created by burak on 11/26/2016.
 */
public class WordDictionary {
    private WordDictionaryNode root;

    public WordDictionary() {
        root = new WordDictionaryNode();
    }
    // Adds a word into the data structure.
    public void addWord(String word) {
        WordDictionaryNode curNode = this.root;
        for (char c : word.toCharArray()) {
            if (!curNode.containsChild(c)) {
                curNode.putChildNode(c, new WordDictionaryNode(c));
            }
            curNode = curNode.getChildNode(c);
        }
        curNode.setEnd(true);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchRecursively(word.toCharArray(), this.root);
    }

    public boolean searchRecursively(char[] charArray, WordDictionaryNode node) {
        if (charArray.length == 0) {
            return node.isEnd();
        }
        char c = charArray[0];
        if (c == '.') {
            for (WordDictionaryNode curNode : node.getChildNodes()) {
                if (curNode != null && searchRecursively(Arrays.copyOfRange(charArray, 1, charArray.length), curNode)) {
                    return true;
                }
            }
            return false;
        } else if (node.containsChild(c)) {
            return searchRecursively(Arrays.copyOfRange(charArray, 1, charArray.length), node.getChildNode(c));
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
    }
}

class WordDictionaryNode {
    private WordDictionaryNode parent;
    private boolean isEnd;
    private char key;
    private WordDictionaryNode[] childNodes;

    // Initialize your data structure here.
    public WordDictionaryNode(char key) {
        this.key = key;
        childNodes = new WordDictionaryNode[26];
        isEnd = false;
    }

    public WordDictionaryNode() {
        this.key = ' ';
        childNodes = new WordDictionaryNode[26];
        isEnd = false;
    }

    public WordDictionaryNode getParent() {
        return parent;
    }

    public void setParent(WordDictionaryNode parent) {
        this.parent = parent;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public WordDictionaryNode[] getChildNodes() {
        return childNodes;
    }

    public WordDictionaryNode getChildNode(char c) {
        return childNodes[c - 'a'];
    }

    public void putChildNode(char c, WordDictionaryNode wordDictionaryNode) {
        this.childNodes[c - 'a'] = wordDictionaryNode;
    }

    public boolean containsChild(char c) {
        return getChildNode(c) != null;
    }
}

// Your leetcode.WordDictionary object will be instantiated and called as such:
// leetcode.WordDictionary wordDictionary = new leetcode.WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
