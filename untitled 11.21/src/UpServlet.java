import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
@MultipartConfig //分段上传时要写@MultipartConfig
@WebServlet(name = "UpServlet", value = "/UpServlet")
public class UpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        System.out.println("上传者: "+name);
        Part filename = request.getPart("filename");
        String submittedFileName = filename.getSubmittedFileName();
        long size = filename.getSize();
        System.out.println("上传的文件名是： "+submittedFileName);
        System.out.println("上传的文件大小是： "+size);
        if (size>1024*1024){
            request.setAttribute("msg","你上传的文件过大，不能超过1MB");
            request.getRequestDispatcher("index.jsp").forward(request,response);
            return;
        }
        //将unload的路径转化为绝对路径
        File file = new File(getServletContext().getRealPath("/unload"));
        System.out.println("文件的上传位置"+file);
        if (!file.exists()){
            file.mkdirs(); //没有这个文件的时候创建文件
        }
        //file.getAbsolutePath()返回file所指向的目录地址, File.separator 则是表示文件路径分隔符的系统常量，值因操作系统而异：Windows:"\",Linux/Unix:"/",使用File.separator是为了保证代码的跨平台兼容性
        String savePath = file.getAbsolutePath() + File.separator + submittedFileName;
        //将文件保存到指定位置
        filename.write(savePath);
        System.out.println("文件已保存到: " + savePath);
        //如果上传的是图片，就将图片展示到页面当中
        if (submittedFileName.endsWith(".jpg")||submittedFileName.endsWith(".png")||submittedFileName.endsWith(".gif")){
            request.setAttribute("picurl","./unload/"+submittedFileName);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }

//        filename.write(file+"/"+submittedFileName);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
