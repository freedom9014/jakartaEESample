package springIntegrator;

public class OracleAccessor implements Accessor {

	public void writeLog(String str) {
		//メソッドの名称的にはDBにでも書き込みたいところだけど、
		//サンプルなので今回は素直に出力。
		System.out.println("OracleAccessorに渡されたデータ:"+str);

	}

}
