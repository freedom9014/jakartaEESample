package springIntegrator;

public class OracleAccessor implements Accessor {

	public void writeLog(String str) {
		System.out.println("OracleAccessorに渡されたデータ:"+str);

	}

}
