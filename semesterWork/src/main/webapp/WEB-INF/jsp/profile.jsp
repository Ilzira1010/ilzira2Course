<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.12.2020
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<@base.mainMacro title="Profile" css=['main.css']>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ilzira
  Date: 08.11.2020
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
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
                <button class="btn btn-primary my-2 my-sm-0" onclick="location.href='<c:url value="/signIn"/>'">Sign In</button>
                <button class="btn btn-primary my-2 my-sm-0 ml-3 mr-3" onclick="location.href='<c:url value="/signUp"/>'">Sign Up</button>
            </c:if>
            <button class="btn btn-primary my-2 my-sm-0" onclick="location.href='<c:url value="/search"/>'">Search</button>
        </div>
    </nav>
</header>
<main>
    <button class="btn btn-primary m-3" onclick="location.href='<c:url value="/create"/>'">Create Podcast</button>
    <button class="btn btn-primary m-3" onclick="location.href='<c:url value="/quit"/>'">Sign Out</button>
    <div class="container">
        <h2 class="text-center">My Podcasts</h2>
        <c:forEach items="${podcasts}" var="p">
            <div class="card container m-3">
                <div class="row">
                    <div class="col-lg-3 p-0">
                        <img class="card-img-top img-fluid" src="<c:url value="/img?name=${p.getImg()}"/>" alt="Image of podcast">
                    </div>
                    <div class="col-lg-9">
                        <div class="card-body">
                            <h2 class="card-text podcast-title title">${p.getTitle()}</h2>
                            <h3 class="card-text podcast-title category">Category: <span>${p.getCategory().getName()}</span></h3>
                        </div>
                        <div class="card-body">
                            <p class="card-text">${p.getUser().getNickname()} / ${p.getCreated_at()}</p>
                            <div class="container-audio">
                                <audio controls preload="auto" id="audio">
                                    <source src="<c:url value="/track?name=${p.getTrack()}"/>" type="audio/ogg">
                                    Your browser dose not Support the audio Tag
                                </audio>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="button-container mb-2 w-50 ml-2">
                <a class="btn btn-success mx-2 w-25" href='<c:url value="/edit?id=${p.getId()}"/>'>Edit</a>
                <a class="btn btn-danger mx-2 w-25" href='<c:url value="/delete?id=${p.getId()}"/>'>Delete</a>
            </div>
        </c:forEach>
    </div>
</main>
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


