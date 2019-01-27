package FileUpload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
//いつもはつかってない「nio」パッケージ使っているので忘れずに
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Collectors;

//importはannotation忘れずに
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//アノテーション記述
@WebServlet(name="MyFileUpload", urlPatterns={"/MyFileUpload"})
@MultipartConfig(fileSizeThreshold=5000000,maxFileSize=10000000,location="C:\\GoToImage")

public class MyFileUpload extends HttpServlet {


	   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {

			        // <INPUT type="file" name="content"> で指定した名前より取得
			        Part part = request.getPart("content");
			        System.out.println(part);
			        // ファイル名の取得
			        String name = getFileName(part);
			        System.out.println(name);
			        // ファイルの保存 @MultipartConfig(location=”/tmp”) で設定した
			        // ディレクトリ配下に保存される。
			        //part.write(name);

			        String destination ="C:\\GoToImage";
			        Path filePath = Paths.get(destination + File.separator + name);
			        // アップしたファイルを取得して、保存
			        InputStream in = part.getInputStream();
			        Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
			        // 画面遷移先で保存したファイルパスを表示
			        //テスト用なのでサーブレットで表示
			        response.setContentType("text/html;charset=UTF-8");
			        try (PrintWriter out = response.getWriter()) {
			            out.println("<!DOCTYPE html>");
			            out.println("<html>");
			            out.println("<head>");
			            out.println("<title>Servlet Upload</title>");
			            out.println("</head>");
			            out.println("<body>");
			            out.println("<h1>File upload result</h1>");
			            out.println("<div>");
			            out.println("upload succeed[file path:" + filePath + "]");
			            out.println("<div>");
			            out.println("</body>");
			            out.println("</html>");
			        }
			    }

			    @Override
			    public void doPost(HttpServletRequest request, HttpServletResponse response)
			    throws IOException, ServletException{
			        processRequest(request,response);
			    }

			    private String getFilename(Part part) {
			        for (String cd : part.getHeader("Content-Disposition").split(":")) {
			            if (cd.trim().startsWith("filename")) {
			                return cd.substring(cd.indexOf('=') + 1).trim().replace("\" ", "");
			            }
			        }
			        return null;
			    }

			    private String getFileName(Part part) {
			        String header = part.getHeader("content-disposition");
			        System.out.println("***" + header);
			        String[] split = header.split(";");
			        // headerは、以下の内容になっているので、ここからfilenameである「fileupload.png」を取得
			        // form-data; name="file"; filename="fileupload.png"
			        String fullPathName =
			                Arrays.asList(split).stream()
			                        .filter(s -> s.trim().startsWith("filename"))
			                        .collect(Collectors.joining());
			       String last = fullPathName.substring(fullPathName.indexOf("=") + 1).replace("\"", "");
			       System.out.println(last);
			       //フルパスから、最後のファイル名だけとってくる処理
			       String fileName = last.substring(last.lastIndexOf("\\") + 1);
			        return fileName;
			    }


}
