package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class Hw11Tests {
    @Test
    public void task1Test()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final String greeting = "Hello, ByteBuddy!";
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value(greeting))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        assertThat(dynamicType.getDeclaredConstructor().newInstance().toString()).isEqualTo(greeting);
    }

    @Test
    public void task2Test()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ByteBuddyAgent.install();
        Class<?> dynamicType = new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(NewArithmeticUtils.class))
            .make()
            .load(getClass().getClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();

        final int a = 5;
        final int b = 10;
        final int expected = 50;

        ArithmeticUtils utils = (ArithmeticUtils) dynamicType.getDeclaredConstructor().newInstance();

        assertThat(utils.sum(a, b)).isEqualTo(expected);
    }

    @Test
    public void task3Test()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> fibClass = createFibClass();

        final int n = 20;
        final int expected = 6765;

        long val = (long) fibClass.getDeclaredMethod("fib", int.class)
            .invoke(fibClass.getDeclaredConstructor().newInstance(), n);

        assertThat(val).isEqualTo(expected);
    }

    private Class<?> createFibClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("FibCalculator")
            .defineMethod("fib", long.class, Modifier.PUBLIC).withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public @NotNull ByteCodeAppender appender(@NotNull Target target) {
                    return (mv, context, methodDescription) -> {
                        Label l1 = new Label();

                        mv.visitCode();

                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1);

                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.I2L);
                        mv.visitInsn(Opcodes.LRETURN);

                        mv.visitLabel(l1);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibCalculator", "fib", "(I)J");
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibCalculator", "fib", "(I)J");
                        mv.visitInsn(Opcodes.LADD);
                        mv.visitInsn(Opcodes.LRETURN);
                        mv.visitEnd();

                        return new ByteCodeAppender.Size(5, 2);
                    };
                }

                @Override
                public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
    }

    static class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    static class NewArithmeticUtils {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}
