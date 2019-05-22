package springtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringTest {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("springtest\\applicationContext-test.xml");

		//getBean()メソッドでCalculatorインターフェイスの実装クラスのインスタンスを取得。
		//このとき、生成されたインスタンスのAccessor型のフィールドには既に
		//インスタンスがセットされている。
		Calculator calc=(Calculator) context.getBean("calc");

		int ret = calc.plusStrings("10","20");

		System.out.println(ret);

		// TODO 自動生成されたメソッド・スタブ

	}

}
