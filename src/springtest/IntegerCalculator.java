package springtest;

import springIntegrator.Accessor;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;


public class IntegerCalculator implements Calculator{

	//インテグレーションレイヤへの参照を保持する
	private Accessor ac;

	public IntegerCalculator(){
	}

	public void setAccessor(Accessor ac){
		this.ac=ac;
	}

	/*ApplicationContext context = new ClassPathXmlApplicationContext(
	"applicationContext-test.xml");

	Accessor ac=(Accessor) context.getBean("calc");
*/

	public int plusStrings(String xStr, String yStr) {

		int x = 0;
		int y = 0;
		try {
			x = Integer.parseInt(xStr);
			y = Integer.parseInt(yStr);
		} catch (NumberFormatException e) {
			// 本来は、独自の例外を送出する
			throw new IllegalArgumentException("数値として解釈できません。");
		}
		int ret = x + y;

		//ファイルへ出力する
		try {
			ac.writeLog(x+"+"+y+"="+ret);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}
}
