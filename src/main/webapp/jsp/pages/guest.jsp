<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="absolutePath">${pageContext.request.contextPath}</c:set>
<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}" scope="session"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="ru_RU"/></c:when>
</c:choose>

<fmt:setBundle basename="context.language"/>
<fmt:message key="guest.name" var="guest_name"/>
<fmt:message key="guest.contacts" var="contacts"/>
<fmt:message key="guest.language" var="lang"/>
<fmt:message key="guest.main" var="main"/>
<fmt:message key="guest.sign_in" var="sign_in"/>
<fmt:message key="guest.registration" var="registration"/>
<fmt:message key="guest.pricing" var="pricing"/>
<fmt:message key="guest.trainer" var="trainer"/>
<fmt:message key="guest.circle_one" var="circle_one"/>
<fmt:message key="guest.circle_one_one" var="circle_one_one"/>
<fmt:message key="guest.circle_two" var="circle_two"/>
<fmt:message key="guest.circle_two_two" var="circle_two_two"/>
<fmt:message key="guest.circle_three" var="circle_three"/>
<fmt:message key="guest.circle_three_three" var="circle_three_three"/>
<fmt:message key="guest.carousel_one_main" var="carousel_one_main"/>
<fmt:message key="guest.carousel_one_text" var="carousel_one_text"/>
<fmt:message key="guest.carousel_two_main" var="carousel_two_main"/>
<fmt:message key="guest.carousel_two_text" var="carousel_two_text"/>
<fmt:message key="guest.carousel_three_main" var="carousel_three_main"/>
<fmt:message key="guest.carousel_three_text" var="carousel_three_text"/>
<fmt:message key="guest.carousel_more" var="carousel_more"/>
<fmt:message key="guest.marketing" var="marketing"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="CSS/styleGuest.css" rel="stylesheet">
    <title>${guest_name}</title>
<body>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"/>
                </svg>
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/controller?command=SHOW_GUEST" class="nav-link px-2 text-white">${main}</a></li>
                <li><a href="/controller?command=SHOW_PRICING" class="nav-link px-2 text-white">${pricing}</a></li>
                <li><a href="/controller?command=SHOW_TRAINER" class="nav-link px-2 text-white">${trainer}</a></li>
                <li><a href="/controller?command=SHOW_CONTACTS" class="nav-link px-2 text-white">${contacts}</a></li>
                <c:choose>
                    <c:when test="${language eq 'ru_RU'}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${absolutePath}/controller?command=change_language&language=en_US">${lang}</a>
                        </li>
                    </c:when>
                    <c:when test="${language eq 'en_US'}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${absolutePath}/controller?command=change_language&language=ru_RU">${lang}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${absolutePath}/controller?command=change_language&language=ru_RU">${lang}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <div class="text-end">
                <a href="/controller?command=SHOW_REGISTRATION">
                    <button type="button" class="btn btn-outline-light me-2">${registration}</button>
                </a>
                <a href="/controller?command=SHOW_SIGN_IN">
                    <button type="button" class="btn btn-warning">${sign_in}</button>
                </a>
            </div>
        </div>
    </div>
</header>

<main>
    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true"
                    aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                     aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <img src="picture/9.jpg"  height="100%"/>
                </svg>

                <div class="container">
                    <div class="carousel-caption text-start">
                        <h1>${carousel_one_main}</h1>
                        <p>${carousel_one_text}</p>
                        <p><a class="btn btn-lg btn-primary" href="#">${carousel_more}</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                     aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <img src="picture/7.jpg" height="100%" fill="#777"/>
                </svg>

                <div class="container">
                    <div class="carousel-caption">
                        <h1>${carousel_two_main}</h1>
                        <p>${carousel_two_text}</p>
                        <p><a class="btn btn-lg btn-primary" href="#">${carousel_more}</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                     aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <img src="picture/8.jpg" height="100%" fill="#777"/>
                </svg>

                <div class="container">
                    <div class="carousel-caption text-end">
                        <h1>${carousel_three_main}</h1>
                        <p>${carousel_three_text}</p>
                        <p><a class="btn btn-lg btn-primary" href="#">${carousel_more}</a></p>
                    </div>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

        <!-- Three columns of text below the carousel -->
        <div class="row">
            <div class="col-lg-4">
                <img src="picture/4.jpg" class="circle"/>

                <h3>${circle_one}</h3>
                <p>${circle_one_one}</p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="picture/5.jpg" class="circle"/>

                <h3>${circle_two}</h3>
                <p>${circle_two_two}</p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">

                <img src="picture/6.jpg" class="circle"/>

                <h3>${circle_three}</h3>
                <p>${circle_three_three}</p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->
    </div>
</main>

<article class="my-3" id="images">
    <div class="bd-example">
        <svg class="bd-placeholder-img bd-placeholder-img-lg img-fluid" width="100%" height="0"
             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Responsive image"
             preserveAspectRatio="xMidYMid slice" focusable="false">
            <img src="picture/10.jpg" width="100%" height="70%" fill="#868e96"/>
        </svg>

    </div>
</article>

<div class="container marketing">
    <div class="pricing-header p-3 pb-md-4 mx-auto text-center">
        <h1 class="display-4 fw-normal">${marketing}</h1>
    </div>

    <main>
        <div class="row row-cols-1 row-cols-md-3 mb-3 text-center">
            <div class="col">
                <div class="card mb-4 rounded-3 shadow-sm">
                    <div class="card-header py-3">
                        <h4 class="my-0 fw-normal">Free</h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">$0<small class="text-muted fw-light">/mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>10 users included</li>
                            <li>2 GB of storage</li>
                            <li>Email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="w-100 btn btn-lg btn-outline-primary">Sign up for free</button>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card mb-4 rounded-3 shadow-sm">
                    <div class="card-header py-3">
                        <h4 class="my-0 fw-normal">Pro</h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">$15<small class="text-muted fw-light">/mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>20 users included</li>
                            <li>10 GB of storage</li>
                            <li>Priority email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="w-100 btn btn-lg btn-primary">Get started</button>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card mb-4 rounded-3 shadow-sm border-primary">
                    <div class="card-header py-3 text-white bg-primary border-primary">
                        <h4 class="my-0 fw-normal">Enterprise</h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">$29<small class="text-muted fw-light">/mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>30 users included</li>
                            <li>15 GB of storage</li>
                            <li>Phone and email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="w-100 btn btn-lg btn-primary">Contact us</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<div class="container">
    <p class="float-end"><a href="#">Back to top</a></p>
    <ctg:footertag/>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</head>
</html>
