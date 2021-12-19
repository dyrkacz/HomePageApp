<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/views/user/header.jsp" %>
<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <div class="card mb-4 mt-4">
                <div class="card-header">
                    <div class="row justify-content-between">
                        <div class="col">
                            <c:choose>
                                <c:when test="${empty param.status and empty param.search}">
                                    <ul class="nav nav-tabs card-header-tabs card-bottom">
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark active" id="activeEvents" href="/user/event/list">Active
                                                events</a>
                                        </li>
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark" id="indefiniteEvents"
                                               href="/user/event/list?status=indefinite">Indefinite
                                                events</a>
                                        </li>
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark" id="oldEvents" href="/user/event/list?status=old">Old
                                                events</a>
                                        </li>
                                    </ul>
                                </c:when>
                                <c:when test="${param.status == 'old'}">
                                    <ul class="nav nav-tabs card-header-tabs card-bottom">
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark" id="activeEvents" href="/user/event/list">Active
                                                events</a>
                                        </li>
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark" id="indefiniteEvents"
                                               href="/user/event/list?status=indefinite">Indefinite
                                                events</a>
                                        </li>
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark active" id="oldEvents"
                                               href="/user/event/list?status=old">Old
                                                events</a>
                                        </li>
                                    </ul>
                                </c:when>
                                <c:when test="${not empty param.search}">
                                    <ul class="nav nav-tabs card-header-tabs card-bottom">
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark" id="activeEvents" href="/user/event/list">Active
                                                events</a>
                                        </li>
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark" id="indefiniteEvents"
                                               href="/user/event/list?status=indefinite">Indefinite
                                                events</a>
                                        </li>
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark" id="oldEvents"
                                               href="/user/event/list?status=old">Old
                                                events</a>
                                        </li>
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark active disabled" id="searchEvents"
                                               href="">Search
                                                events</a>
                                        </li>
                                    </ul>
                                </c:when>
                                <c:when test="${param.status == 'indefinite'}">
                                    <ul class="nav nav-tabs card-header-tabs card-bottom">
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark" id="activeEvents" href="/user/event/list">Active
                                                events</a>
                                        </li>
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark active" id="indefiniteEvents"
                                               href="/user/event/list?status=indefinite">Indefinite events</a>
                                        </li>
                                        <li class="nav-item-dark">
                                            <a class="nav-link-dark" id="oldEvents" href="/user/event/list?status=old">Old
                                                events</a>
                                        </li>
                                    </ul>
                                </c:when>

                            </c:choose>
                        </div>
                        <div class="col-2">
                            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                                <div class="input-group">
                                    <input class="form-control" type="text" placeholder="Search by name..."
                                           aria-label="Search for..." aria-describedby="btnNavbarSearch" name="search"/>
                                    <button class="btn btn-dark" id="btnNavbarSearch" type="submit"><i
                                            class="fas fa-search"></i></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Card Body -->
                <div class="card-body mb-n3">
                    <div class="row">
                        <div class="col-xxl-2">
                            <b>Name</b>
                        </div>
                        <div class="col-xxl-4">
                            <b>Description</b>
                        </div>
                        <div class="col-xxl-1">
                            <b>Date</b>
                        </div>
                        <div class="col-xxl-1">
                            <b>Time</b>
                        </div>
                        <div class="col-xxl-1">
                            <b>Priority</b>
                        </div>
                        <div class="col-xxl-1">
                            <b>Email reminder</b>
                        </div>
                        <div class="col-xxl-1">
                            <b>Done</b>
                        </div>
                        <div class="col-xxl-1">
                            <b>Edit/Delete</b>
                        </div>
                    </div>
                    <!-- Divider -->
                    <hr class="sidebar-divider">
                </div>
                <div class="card-body scroll-events mt-n4">
                    <c:forEach items="${events}" var="event">
                        <div class="row">
                            <div class="col-xxl-2">
                                    ${event.eventName}
                            </div>
                            <div class="col-xxl-4">
                                    ${event.eventDescription}
                            </div>
                            <div class="col-xxl-1">
                                <c:choose>
                                    <c:when test="${event.eventExecutionDate == null}">
                                        ---
                                    </c:when>
                                    <c:otherwise>
                                        ${event.eventExecutionDate}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-xxl-1">
                                <c:choose>
                                    <c:when test="${event.eventExecutionTime == null}">
                                        ---
                                    </c:when>
                                    <c:otherwise>
                                        ${event.eventExecutionTime}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-xxl-1">
                                    ${event.priority}
                            </div>
                            <div class="col-xxl-1">
                                    ${event.emailReminder}
                            </div>
                            <div class="col-xxl-1">
                                    ${event.done}
                            </div>
                            <div class="col-xxl-1">
                                <div class="row">
                                    <div class="btn-group-sm" role="group">
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-danger btn-sm active"
                                                data-toggle="modal"
                                                data-target="#exampleModalCenter${event.id}" aria-pressed="true">
                                            Delete
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade" id="exampleModalCenter${event.id}" tabindex="-1"
                                             role="dialog" aria-labelledby="exampleModalCenterTitle${event.id}"
                                             aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title"
                                                            id="exampleModalCenterTitle${event.id}">Delete</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Are you sure you want to delete ${event.eventName}?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <a type="button" class="btn btn-danger active"
                                                           aria-pressed="true"
                                                           href="/user/event/delete/${event.id}">Delete</a>
                                                        <a type="button" class="btn btn-dark active"
                                                           aria-pressed="true"
                                                           data-dismiss="modal">No</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <a class="btn btn-dark btn-sm active" role="button"
                                           aria-pressed="true"
                                           href="/user/event/edit/${event.id}">Edit</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Divider -->
                        <hr class="sidebar-divider">
                    </c:forEach>
                </div>
                <c:choose>
                    <c:when test="${empty param.page}">
                        <c:set var="pageJstl" value="1"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="pageJstl" value="${param.page}"/>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${empty param.status && empty param.search}">
                        <c:set var="statusJstl" value=""/>
                    </c:when>
                    <c:when test="${param.status== 'old'}">
                        <c:set var="statusJstl" value="status=old"/>
                    </c:when>
                    <c:when test="${param.status== 'indefinite'}">
                        <c:set var="statusJstl" value="status=indefinite"/>
                    </c:when>
                    <c:when test="${not empty param.search}">
                        <c:set var="statusJstl" value="search=${param.search}"/>
                    </c:when>
                </c:choose>


                <div class="card-body mt-n5 mb-n2">
                    <nav aria-label="...">
                        <ul class="pagination mb-0">
                            <c:choose>
                                <c:when test="${pageJstl==1}">
                                    <li class="page-item-dark disabled">
                                        <a class="page-link-dark" href="#" tabindex="-1"
                                           aria-disabled="true">Previous</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item-dark">
                                        <a class="page-link-dark" href="list?${statusJstl}&page=${pageJstl-1}"
                                           tabindex="-1" aria-disabled="true">Previous</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${pageJstl>1}">
                                <li class="page-item-dark"><a class="page-link-dark"
                                                              href="list?${statusJstl}&page=${pageJstl-1}">${pageJstl-1}</a>
                                </li>
                            </c:if>
                            <li class="page-item-dark active" aria-current="page">
                                <a class="page-link-dark" href="list?${statusJstl}&page=${pageJstl}">${pageJstl} </a>
                            </li>
                            <c:if test="${pageJstl<pages}">
                                <li class="page-item-dark"><a class="page-link-dark"
                                                              href="list?${statusJstl}&page=${pageJstl+1}">${pageJstl+1}</a>
                                </li>
                            </c:if>
                            <c:choose>
                                <c:when test="${pageJstl == pages || pages== 0}">
                                    <li class="page-item-dark disabled">
                                        <a class="page-link-dark" href="#">Next</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item-dark">
                                        <a class="page-link-dark" href="list?${statusJstl}&page=${pageJstl+1}">Next</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </nav>
                </div>

            </div>
        </div>
    </main>
    <%@ include file="/WEB-INF/views/user/footer.jsp" %>
</html>

