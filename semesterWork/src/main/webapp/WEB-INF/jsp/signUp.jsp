<%--
  Created by IntelliJ IDEA.
  User: ilzira
  Date: 29.10.2020
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
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
                            <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Registration</h3>
                            <%
                                String error = (String) request.getAttribute("error");
                                if (error != null) {
                            %>
                            <div class="alert alert-danger fade show" role="alert">
                                <%=error%>
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <%
                                }
                            %>
                            <form action="/signUp" autocomplete="off" method="post" id="form">
                                <div class="form-group">
                                    <span class="text-black-50">Nickname</span>
                                    <input id="nickname-signup" type="text" class="form-control" name="nickname" required>
                                </div>
                                <div class="form-group">
                                    <span class="text-black-50">Email</span>
                                    <input id="email-signup" type="email" data-verify="email" class="form-control"
                                           name="email"
                                           required>
                                </div>
                                <div class="form-group">
                                    <span class="text-black-50">Password</span>
                                    <input id="password-signup" type="password" class="form-control" name="password"
                                           title="Password should consist of latin alphabet and must contains letter with capital case and at list one num"
                                           required>
                                </div>
                                <div class="form-group">
                                    <span class="text-black-50">Repeat password</span>
                                    <input id="password-rep-signup" type="password" class="form-control"
                                           name="password-repeat"
                                           required>
                                </div>
                                <button class="btn btn-primary btn-lg btn-block waves-effect waves-light" id="submit" type="submit">
                                    Sign Up
                                </button>
                            </form>
                            <hr>
                            <p><a href="/signIn" class="text-secondary">Sign In?</a></p>
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
