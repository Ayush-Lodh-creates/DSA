package trie;
class TrieNode {
    TrieNode[] children;
    boolean isLeaf;

    TrieNode() {
        children = new TrieNode[26];
        isLeaf = false;
    }
}
class Trie {

    TrieNode root;

    Trie(TrieNode trieNode) {
        this.root = trieNode;
    }
    public void addWord(String s) {
        TrieNode parent = root;
        for(int i=0; i<s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            if(parent.children[ch] == null) {
                TrieNode trieNode = new TrieNode();
                parent.children[ch] = trieNode;
            }
            parent = parent.children[ch];
            if(i == s.length() - 1) {
                parent.isLeaf = true;
            }
        }
    }
}
public class PrintAllWords {

    public int dfs(TrieNode trieNode, int[] chars) {
        int count = 0;
        if(trieNode.isLeaf) {
            count++;
        }
        for(int i=0; i<chars.length; i++) {
            if(chars[i] > 0 && trieNode.children[i] != null) {
                chars[i]--;
                count += dfs(trieNode.children[i], chars);
                chars[i]++;
            }
        }
        return count;
    }

    public int countAllPossibleWords(String[] strs, char[] chars) {
        TrieNode parent = new TrieNode();
        Trie trie = new Trie(parent);
        for (String str : strs) {
            trie.addWord(str);
        }
        int[] hash = new int[26];
        for (char aChar : chars) {
            hash[aChar - 'a']++;
        }
        return dfs(trie.root, hash);
    }

    public static void main(String[] args) {
        String[] strs = {"bat", "boy", "eat", "go", "goal", "me", "run"};
        char[] chars = {'e','o','b', 'a','m','g', 'l'};
        PrintAllWords printAllWords = new PrintAllWords();
        int ans = printAllWords.countAllPossibleWords(strs, chars);
        System.out.println(ans);
    }
}
