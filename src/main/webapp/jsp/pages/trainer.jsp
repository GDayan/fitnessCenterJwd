<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="absolutePath">${pageContext.request.contextPath}</c:set>
<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}" scope="session"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="${language = 'ru_RU'}" scope="session"/></c:when>
</c:choose>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="CSS/styleGuest.css" rel="stylesheet">
    <title>Guest</title>
<body>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/controller?command=SHOW_GUEST" class="nav-link px-2 text-white">Главное</a></li>
                <li><a href="/controller?command=SHOW_PRICING" class="nav-link px-2 text-white">Цены</a></li>
                <li><a href="/controller?command=SHOW_TRAINER" class="nav-link px-2 text-white">Тренеры</a></li>
                <li><a href="#" class="nav-link px-2 text-white">Контакты</a></li>
                <li><a href="#" class="nav-link px-2 text-white">О нас</a></li>
            </ul>
            <div class="text-end">
                <a href="/controller?command=CHANGE_LANGUAGE"><button class="btn btn-outline-light" type="submit">En/Ru</button></a>
                <a href="/controller?command=SHOW_REGISTRATION"><button type="button" class="btn btn-outline-light me-2">Регистрация</button></a>
                <a href="/controller?command=SHOW_SIGN_IN"><button type="button" class="btn btn-warning">Войти</button></a>
            </div>
        </div>
    </div>
</header>

<main>
    <div class="container">
        <!-- START THE FEATURETTES -->
        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">First featurette heading. <span class="text-muted">It’ll blow your mind.</span></h2>
                <p class="lead">Some great placeholder content for the first featurette here. Imagine some exciting prose here.</p>
            </div>
            <div class="col-md-5">
                <img src="picture/1.jpg" width="500" height="500" alt="" class="img-fluid" />
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading">Oh yeah, it’s that good. <span class="text-muted">See for yourself.</span></h2>
                <p class="lead">Another featurette? Of course. More placeholder content here to give you an idea of how this layout would work with some actual real-world content in place.</p>
            </div>
            <div class="col-md-5">
                <img src="picture/2.jpg"  alt="" class="img-fluid" />
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
                <p class="lead">And yes, this is the last block of representative placeholder content. Again, not really intended to be actually read, simply here to give you a better view of what this would look like with some actual content. Your content.</p>
            </div>
            <div class="col-md-5">
                <img src="picture/3.jpg"  alt="" class="img-fluid" />
            </div>
        </div>

        <hr class="featurette-divider">

        <!-- /END THE FEATURETTES -->

    </div><!-- /.container -->
</main>


    <!-- FOOTER -->
    <div class="container">
        <p class="float-end"><a href="#">Back to top</a></p>
        <ctg:footertag/>
    </div>
</main>
</body>
</html>
