<!DOCTYPE html>
<html>
<head>
    <title>用户信息</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <img alt="140x140" src="${userForm.headimgurl}"
                 class="img-circle center-block"/>
            <form role="form" action="/wechat/update" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="text1">OpenID</label><input type="text" class="form-control" value="${userForm.openid}"
                                                            name="openid" readonly/>
                </div>
                <div class="form-group">
                    <label for="text2">性别</label><input type="text" class="form-control" value="${userForm.sex}"
                                                        name="sex" readonly/>
                </div>
                <div class="form-group">
                    <label for="text3">昵称</label><input type="text" class="form-control" value="${userForm.nickname}"
                                                        name="nickname" readonly/>
                </div>
                <div class="form-group">
                    <label for="text4">电话号码</label><input type="text" class="form-control" value="${userForm.phone!''}"
                                                          name="phone"/>
                </div>

                <div class="form-group">
                    <label for="exampleInputFile">论坛头像</label>
                    <#--<input type="file" name="fileName" value="D:\test\ttt.jpg"} />-->
                <#--显示图片，一定要在img中的src发请求给controller，否则直接跳转是乱码-->
                    <br>
                    <#if fileName?? && fileName != "">
                    <img src="/file/show?fileName=${fileName}" style="width: 200px"/>
                    </#if>
                </div>

                <a href="/file/showUpload?userid=${userForm.userid}"> <button type="button" class="btn btn-info btn-default">修改头像</button></a>
                <br><br>
                <label for="text4">个人兴趣</label>
                <div class="checkbox">
                <#--判断hobbys是否为空-->
                    <#if hobbys ?? && (hobbys?size > 0)>

                        <label><input type="checkbox" name="hobby" value="爬山"
                        <#if hobbys?seq_contains("爬山")>  checked="checked" </#if>
                        />爬山</label>

                        <label><input type="checkbox" name="hobby" value="看书"
                        <#if hobbys?seq_contains("看书")>  checked="checked" </#if>
                        />看书</label>

                        <label><input type="checkbox" name="hobby" value="游泳"
                        <#if hobbys?seq_contains("游泳")>  checked="checked" </#if>
                        />游泳</label>

                    <#else >

                         <label><input type="checkbox" name="hobby" value="爬山"/>爬山</label>
                         <label><input type="checkbox" name="hobby" value="看书"/>看书</label>
                         <label><input type="checkbox" name="hobby" value="游泳"/>游泳</label>
                    </#if>

                </div>
                <br>
                <br>
                <div class="container">
                    <div class="row clearfix">
                            <button type="submit" class="btn btn-block btn-success btn-lg">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>

