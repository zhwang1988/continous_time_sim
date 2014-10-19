package com.pat.test;

import java.lang.reflect.*;  
   
//定义项目接口  
interface Subject {  
    public String say(String name, int age);  
}  
   
// 定义真实项目  
class RealSubject implements Subject {  
    @Override  
    public String say(String name, int age) {  
        return name + "  " + age;  
    }  
}  
   
class MyInvocationHandler implements InvocationHandler {  
    private Object obj = null;  
   
    public Object bind(Object obj) {  
        this.obj = obj;  
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj  
                .getClass().getInterfaces(), this);  
    }  
   
    @Override  
    public Object invoke(Object proxy, Method method, Object[] args)  
            throws Throwable {  
        Object temp = method.invoke(this.obj, args);  
        return temp;  
    }  
}  
   
class hello {  
    public static void main(String[] args) {  
        MyInvocationHandler demo = new MyInvocationHandler();  
        Subject sub = (Subject) demo.bind(new RealSubject());  
        String info = sub.say("Rollen", 20);  
        System.out.println(info);  
    }  
}  
