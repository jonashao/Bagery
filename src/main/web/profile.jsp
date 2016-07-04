<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:base>
    <jsp:attribute name="title">个人信息</jsp:attribute>

    <jsp:attribute name="head">
            <link href="../../css/login.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <!--Main layout-->
        <div class="container">
            <div class="widget-wrapper row">
                <div class="card card-login col-sm-push-2 col-sm-8 col-lg-push-4 col-lg-4 ">
                    <form class="form-user" action="update" namespace="/user"  method="POST">
                    <div class="card-block">

                        <div class="md-form">
                            <s:textfield label="用户名" id="form1"  name="username"  class="form-control" disabled="true"/>
                        </div>

                        <div class="md-form">
                            <s:textfield label="邮箱" name="email"  class="form-control" disabled="true"/>
                        </div>

                        <div class="md-form">
                            <s:textfield label="姓名" name="realname" class="form-control" disabled="false"/>
                        </div>

                        <s:submit cssClass="grey btn btn-primary" value="确认"/>
                        <a href="home.jsp">取消</a>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <!--/.Main layout-->
    </jsp:body>

</t:base>
