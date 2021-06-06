<%--
  Created by IntelliJ IDEA.
  User: wuyz
  Date: 2018/12/4
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="jquery.min.js"></script>
<script>
    function upload(){
        var formdata = new FormData($("#myform")[0]);

        $.ajax({
            url:"upload.do",
            type:"POST",
            data:formdata,
            contentType:false,
            processData:false,
            success:function(data){
                alert(data);
            }
        });
    }

</script>
<body>



<form method="post" enctype="multipart/form-data" id="myform">
    <input type="file" name="file">
    <input type="button" value="提交" onclick="upload()">
</form>
</body>
</html>
