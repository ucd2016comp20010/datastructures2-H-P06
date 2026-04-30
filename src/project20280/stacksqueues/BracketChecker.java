package project20280.stacksqueues;

import java.util.Scanner;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        // TODO
        LinkedStack<Character> stack = new LinkedStack<>();
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("");

        int i = 0;
        while (scanner.hasNext()) {
            char ch = scanner.next().charAt(0);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    System.out.println("Not correct");
                    scanner.close();
                    return;
                }
                char open = stack.pop();
                if ((ch == ')' && open != '(') ||
                        (ch == ']' && open != '[') ||
                        (ch == '}' && open != '{')) {
                    System.out.println("Not correct");
                    scanner.close();
                    return;
                }
            }
            i++;
        }

        scanner.close();

        if (!stack.isEmpty()) {
            System.out.println("Not correct");
        } else {
            System.out.println("Correct");
        }
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}