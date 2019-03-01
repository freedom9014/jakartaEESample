package calc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet("/calc/CalcServlet")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CalcServlet() {
        super();
    }

	//計算結果を格納する変数。転送先のJSPで使用するためにインスタンス変数
	Integer result;

	//ブラウザからGETメソッドが来たときの処理。
	public void doGet(HttpServletRequest req,HttpServletResponse res)
		throws ServletException,IOException{

			//送られてきた文字コードのセット
			req.setCharacterEncoding("UTF-8");

			//preとsufにブラウザから送信された値を数値に直してセット
			int pre = Integer.parseInt(req.getParameter("pre"));
			int suf = Integer.parseInt(req.getParameter("suf"));

			//計算の記号を受け取る。
			String choi = req.getParameter("radio");

			//受け取った記号が何かによって処理を判定
			if(choi.equals("r1")){
				result = pre + suf;//足し算
			}else if(choi.equals("r2")){
				result = pre - suf;//引き算
			}else if(choi.equals("r3")){
				result = pre * suf;//掛け算
			}else if(choi.equals("r4")){
				result = pre / suf;//割り算
			}

			//JSPに転送するための値をセット。
			req.setAttribute("mess",result);

			//転送先のファイル名として、「form.jsp」をセット
			RequestDispatcher dpat = req.getRequestDispatcher("calcinput.jsp");

			//JSPに転送
			dpat.forward(req,res);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
