<%--
  Created by IntelliJ IDEA.
  User: UNN
  Date: 2024/11/21
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="UpServlet" method="post" enctype="multipart/form-data">
    <p>上传者:<input type="text" name="name"></p>
    <p>上传文件:<input type="file" name="filename"></p>
    <p><input type="submit" value="上传"></p>
  </form>
  <h2>${msg}</h2>
<%--  <c:if> 是 JSTL 中用于进行条件判断的标签.test是用来指定条件表达式的,not empty picurl表示如果picurl不为空,就执行标签内部的内容--%>
  <c:if test="${not empty picurl}">
    <h2>上传的图片：</h2>
    <img src="${picurl}" alt="上传的图片" style="max-width: 500px; max-height: 500px;">
  </c:if>
  <h2>前台超链接文件下载</h2>
  //当要把浏览器能够识别的资料放在浏览器下载时,要在链接中加入download
  <p><a href="./down/IMG_9464.JPG" download>图片下载</a></p>
  <p><a href="./down/test.zip">压缩包下载</a></p>
  <p><a href="./down/2.txt" download>文本文件下载</a></p>

  <h2>后台文件下载</h2>
  <form action="DownServlet" method="post">
    <p>输入要下载的文件：<input type="text" name="fn"></p>
    <p><input type="submit" value="下载"></p>
    <h2>${msg2}</h2>
  </form>
  </body>
</html>
