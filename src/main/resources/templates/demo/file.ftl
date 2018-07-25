<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>修改头像</title>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h1 >论坛头像上传</h1>
<br>
<form action="/file/fileUpload" method="post" enctype="multipart/form-data">
    <input type="file" name="fileName"/>
    <br>
    <button type="submit" class="btn btn-info btn-default">上传并预览</button>
</form>
<br>
<br>

<#--显示图片，一定要在img中的src发请求给controller，否则直接跳转是乱码-->
<#if fileName??>
<img src="/file/show?fileName=${fileName}" style="width: 200px"/>
</#if>

<br>
<br>
<a href="/wechat/authorize"> <button type="button" class="btn btn-info btn-default">返回</button></a>
</body>
</html>