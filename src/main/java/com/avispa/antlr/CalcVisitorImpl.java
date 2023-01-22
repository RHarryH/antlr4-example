package com.avispa.antlr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rafał Hiszpański
 */
public class CalcVisitorImpl extends CalcPatternsBaseVisitor<Double> {
    private final List<Double> results = new ArrayList<>();

    @Override
    public Double visitLine(CalcPatternsParser.LineContext ctx) {
        double result = this.visit(ctx.expression());
        results.add(result);
        System.out.println("Result: " + result);
        return result;
    }

    @Override
    public Double visitAddSub(CalcPatternsParser.AddSubContext ctx) {
        if (ctx.op.getText().equals("+")) {
            return this.visit(ctx.left) + this.visit(ctx.right);
        }

        return this.visit(ctx.left) - this.visit(ctx.right);
    }

    @Override
    public Double visitMulDiv(CalcPatternsParser.MulDivContext ctx) {
        if (ctx.op.getText().equals("*")) {
            return this.visit(ctx.left) * this.visit(ctx.right);
        }

        return this.visit(ctx.left) / this.visit(ctx.right);
    }

    @Override
    public Double visitPow(CalcPatternsParser.PowContext ctx) {
        return Math.pow(this.visit(ctx.left), this.visit(ctx.right));
    }

    @Override
    public Double visitUnaryMinus(CalcPatternsParser.UnaryMinusContext ctx) {
        return -this.visit(ctx.atom());
    }

    @Override
    public Double visitNumber(CalcPatternsParser.NumberContext ctx) {
        return Double.parseDouble(ctx.NUM().getText());
    }

    public List<Double> getResults() {
        return Collections.unmodifiableList(this.results);
    }
}
