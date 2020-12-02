<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="<c:url value="/views/static/bootstrap.min.css"/>" rel="stylesheet" id="bootstrap-css">
    <title>Main</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand mb-0 h1" href="/main">Podcaster</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/profile"/>">My profile</a>
                </li>
            </ul>
            <c:if test="${user == null}">
                <button class="btn btn-primary my-2 my-sm-0" onclick="location.href='<c:url value="/signIn"/>'">Sign
                    In
                </button>
                <button class="btn btn-primary my-2 my-sm-0 ml-3 mr-3" onclick="location.href='<c:url value="/signUp"/>'">
                    Sign Up
                </button>
            </c:if>
            <button class="btn btn-primary my-2 my-sm-0" onclick="location.href='<c:url value="/search"/>'">Search
            </button>
        </div>
    </nav>
</header>
<div class="container">
    <section class="mt-5 mb-4">
        <div class="row">
            <div class="col"></div>
            <div class="col-8 align-self-center">
                <div class="card wish-list mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Edit Podcast</h3>
                            <h4 class="error-message" id="error-message"></h4>
                            <hr>
                            <form action="<c:url value="/edit?id=${podcast.getId()}"/>" method="post">
                                <div class="form-group">
                                    <label for="title">Title</label>
                                    <input name="title" id="title" type="text" class="form-control w-100"
                                           placeholder="Title" required value="${podcast.getTitle()}">
                                </div>
                                <button class="btn btn-primary" type="submit">Update</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col"></div>
    </section>
</div>
<footer class="footer navbar-fixed-bottom font-small pt-4">
    <hr>
    <div class="container">
        <ul class="list-unstyled list-inline text-center">
            <li class="list-inline-item">
                <a class="btn-floating btn-fb mx-1 text-dark" href="https://vk.com/i.akhmetdinova" target="_blank">
                    <i class="fab fa-vk"> </i>
                </a>
            </li>
            <li class="list-inline-item">
                <a class="btn-floating btn-tw mx-1 text-dark" href="http://instagram.com/a.ilzira_1010" target="_blank">
                    <i class="fab fa-instagram"> </i>
                </a>
            </li>
            <li class="list-inline-item">
                <a class="btn-floating btn-li mx-1 text-dark" href="https://t.me/IlziraAkhmetdinova" target="_blank">
                    <i class="fab fa-telegram"> </i>
                </a>
            </li>
        </ul>
    </div>
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a>Elzira Akhmatdinov</a>
    </div>
</footer>
<script src="<c:url value="/views/static/fontawesome.js"/>"></script>
<script src="<c:url value="/views/static/jquery-3.5.1.min.js"/>"></script>
<script src="<c:url value="/views/static/bootstrap.bundle.js"/>"></script>
</body>
</html>
