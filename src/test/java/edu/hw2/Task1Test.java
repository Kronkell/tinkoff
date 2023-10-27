package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    public void shouldCalculateResultGivenChainOfOperations() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        double actualResult = res.evaluate();
        double expectedResult = 37.0;

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void shouldCalculateResultGivenZeroConstant() {
        var zero = new Expr.Constant(0);

        double actualResult = zero.evaluate();
        double expectedResult = 0.0;

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void shouldCalculateResultGivenNegativeConstant() {
        var neg = new Expr.Constant(-1);
        var pos = new Expr.Negate(neg);

        double actualResult = pos.evaluate();
        double expectedResult = 1.0;

        assertThat(actualResult).isEqualTo(expectedResult);

    }
}
