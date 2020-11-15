<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.11.2020
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/static/bootstrap.min.css">
</head>
<body>
<div>
    <div class="container">
        <section class="mt-5 mb-4">
            <div class="row">
                <div class="col"></div>
                <div class="col-8 align-self-center">
                    <div class="card wish-list mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Add User</h3>
                                <div id="error_block" class="alert alert-danger fade show" role="alert" style="display: none;">
                                    <span id="error"></span>
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="form-group">
                                    <span class="text-black-50">Firstname</span>
                                    <input id="first_name" type="text"
                                           class="form-control" name="name"
                                           required>
                                </div>
                                <div class="form-group">
                                    <span class="text-black-50">Lastname</span>
                                    <input id="last_name" type="text"
                                           class="form-control" name="Lastname"
                                           required>
                                </div>
                                <div class="form-group">
                                    <span class="text-black-50">Email</span>
                                    <input id="email" type="text" data-verify="email"
                                           class="form-control" name="email"
                                           required>
                                </div>
                                <button onclick="sendUser(
                                    $('#first_name').val(),
                                    $('#last_name').val(),
                                    $('#email').val())"
                                        class="btn btn-primary btn-lg btn-block waves-effect waves-light">Send
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col"></div>
            </div>
        </section>
    </div>
</div>
<div class="container">
    <div class="row mb-5">
        <div class="col"></div>
        <div class="col-8">
            <h3 id="table_caption" class="mb-4 text-black-80 mt-0 font-weight-bold"></h3>
            <table id="table" class="table table-striped table-bordered"></table>
        </div>
        <div class="col"></div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/views/static/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/views/static/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/views/js/userAjax.js"></script>
</body>
</html>
