<%--
  Created by IntelliJ IDEA.
  User: ilzira
  Date: 29.10.2020
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Authorization</title>
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
                            <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Authorization</h3>
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
                            <form autocomplete="off" action="/signIn" method="post" id="form">
                                <div class="form-group">
                                    <span class="text-black-50">Email</span>
                                    <input id="email-signin" type="text" data-verify="email"
                                           class="form-control" name="email"
                                           required>
                                </div>
                                <div class="form-group">
                                    <span class="text-black-50">Password</span>
                                    <input id="password-signin" type="password" class="form-control"
                                           name="password" required>
                                </div>
                                <button id='submit'
                                        class="btn btn-primary btn-lg btn-block waves-effect waves-light"
                                        type="submit">
                                    Sign In
                                </button>
                            </form>
                            <hr>
                            <p><a href="/signUp" class="text-secondary">Sign Up?</a></p>
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
