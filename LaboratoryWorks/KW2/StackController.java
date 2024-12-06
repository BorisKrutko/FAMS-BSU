package KW2;

import KW2.model.MyStack;
import KW2.model.Visitor;

public class StackController {
    private MyStack myStack;
    private StackView stackView;

    public StackController(MyStack myStack, StackView stackView) {
        this.myStack = myStack;
        this.stackView = stackView;
    }

    public void push(int lastEl) {
        this.myStack.push(lastEl);
    }

    public int pop() {
        return this.pop();
    }

    public int getSize(Visitor visitor) {
        return this.myStack.accept(visitor);
    }

    public String viewString() {
        return this.stackView.viewString(this.myStack);
    }
}
