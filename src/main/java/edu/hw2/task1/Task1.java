package edu.hw2.task1;

import edu.hw2.task4.Task4;
import static edu.hw2.task4.Task4.callingInfo;

public class Task1 {
    public static void main(String[] args) {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var x = new Expr.Multiplication(exp, new Expr.Constant(4));
        var res = new Expr.Addition(x, new Expr.Constant(1));

        System.out.println(res + " = " + res.evaluate());
    }
}

