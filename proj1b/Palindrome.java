public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (char ch: word.toCharArray()) {
            wordDeque.addLast(ch);
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() < 2) {
            return true;
        }
        char first = wordDeque.removeFirst();
        char last = wordDeque.removeLast();
        return (first == last) && isPalindromeHelper(wordDeque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(wordToDeque(word), cc);
    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() < 2) {
            return true;
        }
        char first = wordDeque.removeFirst();
        char last = wordDeque.removeLast();
        return cc.equalChars(first, last) && isPalindromeHelper(wordDeque, cc);
    }

}
