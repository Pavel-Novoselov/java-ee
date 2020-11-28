package ru.geekbrains.utils;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

public class Logger {

    @AroundInvoke
    public Object printLog(InvocationContext ctx) throws Exception{
        System.out.println("Invoke method " + ctx.getMethod().getName());
        System.out.println("Parameters: " + Arrays.toString(ctx.getParameters()));
        System.out.println("Proceed: " + ctx.proceed());
        return ctx.proceed();
    }
}
