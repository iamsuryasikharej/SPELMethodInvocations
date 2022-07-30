package com.surya.test;

import java.lang.reflect.Method;

import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ClassUtils;

import com.surya.beans.Student;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {
	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/surya/resources/applicationContext.xml");
		Student student=(Student) applicationContext.getBean("student");
		System.out.println(student.toString());
		Student student1=new Student();
		ExpressionParser parser=new SpelExpressionParser();
		StandardEvaluationContext context=new StandardEvaluationContext(student1);
		context.setVariable("name1", "surya");
		context.setVariable("roll1", 115);
		Expression expr1=parser.parseExpression("Name= #name1");
		Expression expr2=parser.parseExpression("Roll= #roll1");
		expr1.getValue(context);
		expr2.getValue(context);
//		expr.setValue(context,"surya");
		System.out.println(student1.getName());
		System.out.println(student1.getRoll());
		
		Class clazz=Class.forName("com.surya.beans.StringPrinter");
		Method method[]=clazz.getDeclaredMethods();
		StandardEvaluationContext contextt=new StandardEvaluationContext();
		contextt.registerFunction("display", method[0]);
		contextt.setVariable("s", "suryyyya");
		ExpressionParser ep=new SpelExpressionParser();
		Expression expr3=ep.parseExpression("#display(#s)");
		expr3.getValue(contextt);
		

	}

}
