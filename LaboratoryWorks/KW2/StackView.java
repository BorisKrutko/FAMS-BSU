package KW2;

import KW2.model.MyStack;
import java.util.ArrayList;

public class StackView {
    public String viewString(MyStack stack) {
        String resStr = "";
        ArrayList<Integer> arrayList = stack.getArrayList();
        for (int el : arrayList) {
            resStr += "el: " + el + "; ";
        } 
        return resStr;
    }
}
