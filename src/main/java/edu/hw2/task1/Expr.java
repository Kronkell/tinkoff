package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(int constant) implements Expr {
        @Override
        public double evaluate() {
            return constant;
        }
    }

    public record Negate(Expr first) implements Expr {
        @Override
        public double evaluate() {
            return -first.evaluate();
        }
    }

    public record Exponent(Expr first, int second) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(first().evaluate(), second);
        }
    }

    public record Addition(Expr first, Expr second) implements Expr {
        @Override
        public double evaluate() {
            return first.evaluate() + second.evaluate();
        }
    }

    public record Multiplication(Expr first, Expr second) implements Expr {
        @Override
        public double evaluate() {
            return first.evaluate() * second.evaluate();
        }
    }
}
