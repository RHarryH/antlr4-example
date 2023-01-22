package com.avispa.antlr;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @author Rafał Hiszpański
 */
public class CalcListenerImpl extends CalcPatternsBaseListener {
    private final Deque<Double> stack = new ArrayDeque<>();
    private final List<Double> results = new ArrayList<>();

    @Override
    public void exitLine(CalcPatternsParser.LineContext ctx) {
        Double result = this.stack.pop();
        results.add(result);
        System.out.println("Result: " + result);
    }

    @Override
    public void exitAddSub(CalcPatternsParser.AddSubContext ctx) {
        Double right = this.stack.pop();
        Double left = this.stack.pop();

        if (ctx.op.getText().equals("+")) {
            this.stack.push(left + right);
        } else {
            this.stack.push(left - right);
        }
    }

    @Override
    public void exitMulDiv(CalcPatternsParser.MulDivContext ctx) {
        Double right = this.stack.pop();
        Double left = this.stack.pop();

        if (ctx.op.getText().equals("*")) {
            this.stack.push(left * right);
        } else {
            this.stack.push(left / right);
        }
    }

    @Override
    public void exitPow(CalcPatternsParser.PowContext ctx) {
        Double right = this.stack.pop();
        Double left = this.stack.pop();

        this.stack.push(Math.pow(left, right));
    }

    @Override
    public void exitUnaryMinus(CalcPatternsParser.UnaryMinusContext ctx) {
        Double number = this.stack.pop();

        this.stack.push(-number);
    }

    @Override
    public void exitNumber(CalcPatternsParser.NumberContext ctx) {
        Double number = Double.parseDouble(ctx.NUM().getText());

        this.stack.push(number);
    }

    public List<Double> getResults() {
        return Collections.unmodifiableList(this.results);
    }
}
