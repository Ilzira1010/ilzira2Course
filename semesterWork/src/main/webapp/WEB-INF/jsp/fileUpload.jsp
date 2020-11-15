<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.11.2020
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/static/bootstrap.min.css">
</head>
<body>
<div class="container">
    <section class="mt-5 mb-4">
        <div class="row">
            <div class="col"></div>
            <div class="col-8 align-self-center">
                <div class="card wish-list mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Upload File</h3>
                            <form autocomplete="off" action="/files" method="post" id="form" enctype="multipart/form-data">
                                <div class="form-group">
                                    <span class="text-black-50">File</span>
                                    <input id="file" type="file"
                                           class="form-control" name="file" required>
                                </div>
                                <button id='submit'
                                        class="btn btn-primary btn-lg btn-block waves-effect waves-light"
                                        type="submit">
                                    Upload!
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col"></div>
        </div>
    </section>
</div>
<script src="${pageContext.request.contextPath}/views/static/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/views/static/bootstrap.bundle.js"></script>
</body>
</html>
