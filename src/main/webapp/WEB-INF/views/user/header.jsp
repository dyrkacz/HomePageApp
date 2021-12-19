<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>HomePageApp</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
    <link href="<c:url value="/theme/css/styles.css"/>" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="/user">HomePageApp</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
            class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <%--    <div class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">--%>
    <div class="input-group text-white-50">
        <i class="fas fa-cloud fa-fw ml-2 mr-2"></i>
        <%--            <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />--%>
        <%--            <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>--%>
        <c:choose>
            <c:when test="${not empty weather}">
                Location: ${weather.cityName} Temperature: ${weather.temp} &#176;C Weather: ${weather.weatherDescription}
            </c:when>
            <c:otherwise>
                Set location in settings to see weather.
            </c:otherwise>
        </c:choose>
    </div>
    <%--    </div>--%>
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
               aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="/user/setting/settings">Settings</a></li>
                <li><a class="dropdown-item" href="/user/game/gamesmanager">Games manager</a></li>
                <li><a class="dropdown-item" href="/user/favouritewebsite/fwmanager">Websites manager</a></li>
                <li><a class="dropdown-item" href="/user/newsmanager">News manager</a></li>
                <li><a class="dropdown-item" id="editProfile" href="/user/edit">Edit profile</a></li>
                <li>
                    <hr class="dropdown-divider"/>
                </li>
                <li>
                    <form action="<c:url value="/logout"/>" method="post">
                        <input class="dropdown-item" type="submit" value="Logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </li>
            </ul>
        </li>
    </ul>
</nav>
<div id="layoutSidenav">
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Events</div>
                <a class="nav-link" href="/user/event/list">
                    <div class="sb-nav-link-icon"><i class="fas fa-tasks"></i></div>
                    Events list
                </a>
                <a class="nav-link" href="/user/event/add">
                    <div class="sb-nav-link-icon"><i class="fas fa-pencil-alt"></i></div>
                    Add event
                </a>
                <div class="sb-sidenav-menu-heading">Holidays</div>
                <a class="nav-link" href="/user/holiday/list">
                    <div class="sb-nav-link-icon"><i class="fas fa-list"></i></div>
                    Holidays list
                </a>
                <a class="nav-link" href="/user/holiday/add">
                    <div class="sb-nav-link-icon"><i class="fas fa-birthday-cake"></i></div>
                    Add holiday
                </a>
                <div class="sb-sidenav-menu-heading">Entertainment</div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts1"
                   aria-expanded="false" aria-controls="collapseLayouts1">
                    <div class="sb-nav-link-icon"><i class="fas fa-gamepad"></i></div>
                    Games
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts1" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <c:forEach items="${games}" var="game">
                            <a type="button" class="nav-link" data-toggle="modal"
                               data-target="#exampleModalCenter${game.gameId}" aria-pressed="true">
                                    ${game.gameName}
                            </a>
                        </c:forEach>
                    </nav>
                </div>

                <!------------------------------------------------------------>

                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages"
                   aria-expanded="false" aria-controls="collapsePages">
                    <div class="sb-nav-link-icon"><i class="fas fa-thumbs-up"></i></div>
                    Favourite websites
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>

                <div class="collapse" id="collapsePages" aria-labelledby="headingTwo"
                     data-bs-parent="#sidenavAccordion">

                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                        <c:forEach items="${favouriteWebsiteCategories}" var="favouriteWebsiteCategory">
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                               data-bs-target="#pagesCollapse${favouriteWebsiteCategory.id}" aria-expanded="false"
                               aria-controls="pagesCollapseAuth">
                                    ${favouriteWebsiteCategory.categoryName}
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapse${favouriteWebsiteCategory.id}"
                                 aria-labelledby="headingOne"
                                 data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <c:forEach items="${favouriteWebsites}" var="favouriteWebsite">
                                        <c:if test="${favouriteWebsite.favouriteWebsiteCategory == favouriteWebsiteCategory}">
                                            <a class="nav-link" target="_blank" rel="noopener noreferrer"
                                               href="${favouriteWebsite.websiteUrl}">${favouriteWebsite.websiteName}</a>
                                        </c:if>
                                    </c:forEach>
                                        <%--                                                        <a class="nav-link" href="register.html">Register</a>--%>
                                        <%--                                                        <a class="nav-link" href="password.html">Forgot Password</a>--%>
                                </nav>
                            </div>

                            <%--                                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">--%>
                            <%--                                                    Error--%>
                            <%--                                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>--%>
                            <%--                                                </a>--%>
                            <%--                                                <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">--%>
                            <%--                                                    <nav class="sb-sidenav-menu-nested nav">--%>
                            <%--                                                        <a class="nav-link" href="401.html">401 Page</a>--%>
                            <%--                                                        <a class="nav-link" href="404.html">404 Page</a>--%>
                            <%--                                                        <a class="nav-link" href="500.html">500 Page</a>--%>
                            <%--                                                    </nav>--%>
                            <%--                                                </div>--%>
                        </c:forEach>
                    </nav>

                </div>


            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            ${currentUser.getUsername()}
        </div>
    </nav>
</div>
<!-- Modals for games -->
<c:forEach items="${games}" var="game">
    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter${game.gameId}" tabindex="-1"
         role="dialog" aria-labelledby="exampleModalCenterTitle${game.gameId}"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"
                        id="exampleModalCenterTitle${game.gameId}">Game</h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Start playing ${game.gameName}?
                </div>
                <div class="modal-footer">
                    <a type="button" class="btn btn-dark active"
                       aria-pressed="true" data-dismiss="modal"
                       onclick="window.location.href='steam://rungameid/${game.gameId}'">Start</a>
                    <a type="button" class="btn btn-danger active" aria-pressed="true"
                       data-dismiss="modal">No</a>
                </div>
            </div>
        </div>
    </div>
</c:forEach>