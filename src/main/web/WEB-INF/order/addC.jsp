<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<t:base>
    <jsp:attribute name="title">添加评论</jsp:attribute>

    <jsp:attribute name="head">
        <link href="../../css/login.css" rel="stylesheet">
        <link rel="stylesheet" href="../../css/star-rating.css" media="all" type="text/css"/>
        <link href="../../css/themes/krajee-fa/theme.css" media="all" rel="stylesheet" type="text/css"/>
    </jsp:attribute>

    <jsp:attribute name="scripts">

        <script src="../../js/star-rating.js" type="text/javascript"></script>
        <script src="../../js/themes/krajee-fa/theme.min.js" type="text/javascript"></script>
        <script src="../../js/locales/zh.js"></script>
        <script type="text/javascript">
            $(document).on('ready', function () {
                $(".kv-fa").rating(
                        {
                            theme: 'krajee-fa',
                            filledStar: '<i class="fa fa-star"></i>',
                            emptyStar: '<i class="fa fa-star-o"></i>',
                            step: '1',
                            language: 'zh'
                        }
                );
            });
        </script>

    </jsp:attribute>

    <jsp:body>
        <!--Main layout-->
        <div class="container">
            <form action="addComment" namespace="/comment">
                <s:iterator begin="1" end="2">

                    <div class="row order-item">
                        <div class="col-md-2">
                            <!--Card image-->
                            <div class="view overlay hm-white-slight">
                                <img src="../../img/bags/bag1.png" class="img-fluid" alt="">
                                <a href="#">
                                    <div class="mask"></div>
                                </a>
                            </div>
                            <!--/.Card image-->
                        </div>
                        <div class="col-md-3 order-item-block">
                            <div>
                                <!--Title-->
                                <h5 class="card-title">
                                    商品标题
                                </h5>
                                <div><i class="fa fa-rmb" aria-hidden="true"></i>
                                    80
                                    (
                                    2
                                    件)
                                </div>
                                <div class="bag-color">
                                    颜色:蓝色
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <s:textfield type="text" class="kv-fa rating-loading" value="0" data-size="s" title=""
                                         showClear="false"/>

                            <div class="md-form comment-form-item">
                                <s:textfield label="评论内容" name="content1"/>
                            </div>

                        </div>


                    </div>

                </s:iterator>
                <s:submit value="提交评论"/>
            </form>
        </div>
        <!--/.Main layout-->
    </jsp:body>

</t:base>