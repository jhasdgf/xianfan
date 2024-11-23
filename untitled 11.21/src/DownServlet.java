import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "DownServlet", value = "/DownServlet")
public class DownServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取请求下载的文件名
        String fn = request.getParameter("fn");
        //获取下载文件的路径
        String realPath = getServletContext().getRealPath("/down/");
        //通过路径得到文件对象
        File file = new File(realPath+fn);
        System.out.println("地址"+file);
        if(!file.exists()||!file.isFile()){
            request.setAttribute("msg2","没有你要下载的文件");
            request.getRequestDispatcher("index.jsp").forward(request,response);

        }
        //设置响应类型,"application/x-msdownload" 是一种 MIME 类型,通常用于指示响应是一个文件,且浏览器应该将其作为下载文件处理，而不是直接显示.
        response.setContentType("application/x-msdownload");
        //设置响应头,当浏览器接收到 Content-Disposition: attachment 响应头时,会显示文件下载对话框,提示用户保存文件，而不是直接在浏览器中打开.
        //如果文件包含中文,会出现乱码的现象,为了避免这个问题,可以对文件名进行URL编码
        String encode = URLEncoder.encode(fn, "UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+encode);
        //接下来读取文件,响应输出到浏览器
        FileInputStream fileInputStream = new FileInputStream(file);//获取输入流
        ServletOutputStream outputStream = response.getOutputStream();//获取输出流
        byte[] bytes = new byte[1024];  //定义字节数组
        int len=0;
        while((len=fileInputStream.read(bytes))!=-1){  //循环读写
            outputStream.write(bytes,0,len);
        }
        fileInputStream.close();
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
