package org.txazo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class MinStack {

    private final List<Integer> stack;

    public MinStack() {
        stack = new ArrayList<>();
    }

    public void push(int x) {
        stack.add(x);
        int min = stack.size() == 1 ? x : Math.min(x, stack.get(stack.size() - 2));
        stack.add(min);
    }

    public void pop() {
        if (stack.size() <= 0) {
            return;
        }
        stack.remove(stack.size() - 1);
        stack.remove(stack.size() - 1);
    }

    public int top() {
        if (stack.size() <= 0) {
            return -1;
        }
        return stack.get(stack.size() - 2);
    }

    public int getMin() {
        if (stack.size() <= 0) {
            return -1;
        }
        return stack.get(stack.size() - 1);
    }

}
