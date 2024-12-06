<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--コメント追加-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web計算機</title>
</head>
<body>
		<h1>WEB計算機</h1>
		<form action="CalcServlet" method="get">
			<table>
				<tr>
					<td RowSpan="4"><input type="text" name="pre" ></td>
					<td>＋<input type="radio" name="radio" value="r1" checked></td>
					<td RowSpan="4"><input type="text" name="suf" ></td>
				</tr>
				<tr><td>－<input type="radio" name="radio" value="r2"></td></tr>
				<tr><td>×<input type="radio" name="radio" value="r3"></td></tr>
				<tr><td>÷<input type="radio" name="radio" value="r4"></td></tr>

			</table>
			<input type="submit" value="計算">　

		</form>

		<%
			Integer dresult = (Integer)request.getAttribute("mess");
			if(dresult!=null){
				out.println("<h2>解答は"+dresult+"</h2>");
			}
		%>

</body>
</html>