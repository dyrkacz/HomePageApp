<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/views/user/header.jsp" %>
<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <div class="row">
                <div class="col-xxl-8 mb-n4">
                    <div class="card mb-4 mt-3 mr-n3">
                        <div class="card-header justify-content-around">
                            <div class="row">
                                <div class="col-10 text-center">
                                    <c:choose>
                                        <c:when test="${not empty tweets}">
                                            <div class="sb-nav-link-icon"><i class="fas fa-dove"></i> Twitter </div>
                                        </c:when>
                                        <c:otherwise>
                                            Set twitter id and token to upload tweets. If You did just click "Get tweets"-->
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-2 text-end">
                                <a class="nav-link-dark mt-n2 mb-n2 mr-n2" href="/user/tweets" >Get tweets</a>
                                </div>
                            </div>
                        </div>
                        <div class="card-body scroll-twitter">
                            <ul class="list-unstyled">
                                <c:forEach var="tweet" items="${tweets}">
                                    <li>
                                        <div class="card mb-1">
                                            <div class="card-header">
                                                        <a class="nav-link mt-n2 mb-n2" target="_blank" rel="noopener noreferrer" href="https://twitter.com/${tweet.getUsername()}"
                                                           target="_blank"
                                                           rel="noopener noreferrer">${tweet.getUsername()}</a>
                                            </div>
                                            <div class="card-body gr">
                                                <div class="row">
                                                    <div class="col-xxl-3 img-size-twitter">
                                                            <img src="${tweet.getProfileImgUrl()}">
                                                    </div>
                                                    <div class="col-xxl-9">
                                                        <a class="nav-link mt-n2 mb-n2" target="_blank" rel="noopener noreferrer" href="https://twitter.com/${tweet.getUsername()}/status/${tweet.getId()}"
                                                           target="_blank"
                                                           rel="noopener noreferrer">${tweet.getTweetText()}</a>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-xxl-4 mb-n4">
                    <div class="row">
                        <div class="col-xxl-12">
                            <div class="card mb-2 mt-3">
                                <div class="card-header text-center">
                                    <div class="sb-nav-link-icon"><i class="fas fa-envelope-open"></i> Gmail</div>
                                </div>
                                <div class="card-body scroll-gmail">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xxl-12">
                            <div class="card mb-4">
                                <div class="card-header text-center">
                                    <div class="sb-nav-link-icon"><i class="fas fa-birthday-cake"></i> Holidays</div>
                                </div>
                                <div class="card-body scroll-holidaysPanel">
                                    <ul class="list-group">
                                        <c:forEach items="${holidaysPanel}" var="holidayPanel">
                                            <c:choose>
                                                <c:when test="${holidayPanel.priority == 'Normal'}">
                                                    <li class="list-group-item list-group-item-success">${holidayPanel.holidayDate}
                                                        - ${holidayPanel.holidayName}</li>
                                                </c:when>
                                                <c:when test="${holidayPanel.priority == 'Important'}">
                                                    <li class="list-group-item list-group-item-warning">${holidayPanel.holidayDate}
                                                        - ${holidayPanel.holidayName}</li>
                                                </c:when>
                                                <c:when test="${holidayPanel.priority == 'Urgent'}">
                                                    <li class="list-group-item list-group-item-danger">${holidayPanel.holidayDate}
                                                        - ${holidayPanel.holidayName}</li>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xxl-8  mb-n5">
                    <div class="card mb-4 mt-3 mr-n3">
                        <div class="card-header">
                            <c:choose>
                                <c:when test="${not empty newsCategories}">
                                    <div class="row">
                                        <div class="col">
                                            <ul class="nav nav-tabs card-header-tabs">
                                                <c:forEach items="${newsCategories}" var="newscategory">
                                                    <li class="nav-item-dark">
                                                        <c:choose>
                                                            <c:when test="${active==newscategory.newsCategoryName}">
                                                                <a class="nav-link-dark active" id="activeEvents"
                                                                   href="/user?newsCategory=${newscategory.newsCategoryName}">${newscategory.newsCategoryName}</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a class="nav-link-dark" id="activeEvents"
                                                                   href="/user?newsCategory=${newscategory.newsCategoryName}">${newscategory.newsCategoryName}</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    Set news category to see some interesting articles.
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="card-body scroll-news">
                            <ul class="list-unstyled">
                                <c:forEach var="oneNews" items="${news}">
                                    <li>
                                        <div class="card mb-1">
                                            <div class="card-header">
                                                <a class="nav-link mt-n2 mb-n2" href="${oneNews.getUri()}"
                                                   target="_blank"
                                                   rel="noopener noreferrer">${oneNews.getTitle()}</a>
                                            </div>
                                            <div class="card-body gr">
                                                <div class="row">
                                                    <div class="col-xxl-3 img-size">
                                                            ${oneNews.getImg()}
                                                    </div>
                                                    <div class="col-xxl-9">
                                                            ${oneNews.getDescription()}
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-xxl-4 mb-n5">
                    <div class="row">
                        <div class="col-xxl-6">
                            <div class="card mb-4 mt-3 mr-n3">
                                <div class="card-header text-center">
                                    <div class="sb-nav-link-icon"><i class="fas fa-calendar-day"></i> Active events</div>
                                </div>
                                <div class="card-body scroll-calendar">
                                    <ul class="list-group">
                                        <c:forEach items="${activeEventsPanel}" var="activeevent">
                                            <c:choose>
                                                <c:when test="${activeevent.priority == 'Normal'}">
                                                    <li class="list-group-item list-group-item-success"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${activeevent.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${activeevent.eventExecutionDate} ${activeevent.eventExecutionTime}
                                                                - ${activeevent.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/done/${activeevent.id}"
                                                                                                  class="btn btn-success btn-sm text-white">Done</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                                <c:when test="${activeevent.priority == 'Important'}">
                                                    <li class="list-group-item list-group-item-warning"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${activeevent.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${activeevent.eventExecutionDate} ${activeevent.eventExecutionTime}
                                                                - ${activeevent.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/done/${activeevent.id}"
                                                                                                  class="btn btn-success btn-sm text-white">Done</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                                <c:when test="${activeevent.priority == 'Urgent'}">
                                                    <li class="list-group-item list-group-item-danger"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${activeevent.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${activeevent.eventExecutionDate} ${activeevent.eventExecutionTime}
                                                                - ${activeevent.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/done/${activeevent.id}"
                                                                                                  class="btn btn-success btn-sm text-white">Done</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                        <c:forEach items="${indefinitePanel}" var="indefinite">
                                            <c:choose>
                                                <c:when test="${indefinite.priority == 'Normal'}">
                                                    <li class="list-group-item list-group-item-success"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${indefinite.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${indefinite.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/done/${indefinite.id}"
                                                                                                  class="btn btn-success btn-sm text-white">Done</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                                <c:when test="${indefinite.priority == 'Important'}">
                                                    <li class="list-group-item list-group-item-warning"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${indefinite.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${indefinite.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/done/${indefinite.id}"
                                                                                                  class="btn btn-success btn-sm text-white">Done</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                                <c:when test="${indefinite.priority == 'Urgent'}">
                                                    <li class="list-group-item list-group-item-danger"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${indefinite.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${indefinite.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/done/${indefinite.id}"
                                                                                                  class="btn btn-success btn-sm text-white">Done</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </ul>

                                </div>
                            </div>
                        </div>
                        <div class="col-xxl-6">
                            <div class="card mb-4 mt-3">
                                <div class="card-header text-center">
                                    <div class="sb-nav-link-icon"><i class="fas fa-calendar-times"></i> Passed events</div>
                                </div>
                                <div class="card-body scroll-calendar">
                                    <ul class="list-group">
                                        <c:forEach items="${activeDoneEventsPanel}" var="activeDoneEvent">
                                            <c:choose>
                                                <c:when test="${activeDoneEvent.priority == 'Normal'}">
                                                    <li class="list-group-item list-group-item-success"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${activeDoneEvent.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${activeDoneEvent.eventExecutionDate} ${activeDoneEvent.eventExecutionTime}
                                                                - ${activeDoneEvent.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/edit/${activeDoneEvent.id}"
                                                                                                  class="btn btn-dark btn-sm text-white">Edit</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                                <c:when test="${activeDoneEvent.priority == 'Important'}">
                                                    <li class="list-group-item list-group-item-warning"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${activeDoneEvent.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${activeDoneEvent.eventExecutionDate} ${activeDoneEvent.eventExecutionTime}
                                                                - ${activeDoneEvent.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/edit/${activeDoneEvent.id}"
                                                                                                  class="btn btn-dark btn-sm text-white">Edit</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                                <c:when test="${activeDoneEvent.priority == 'Urgent'}">
                                                    <li class="list-group-item list-group-item-danger"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${activeDoneEvent.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${activeDoneEvent.eventExecutionDate} ${activeDoneEvent.eventExecutionTime}
                                                                - ${activeDoneEvent.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/edit/${activeDoneEvent.id}"
                                                                                                  class="btn btn-dark btn-sm text-white">Edit</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                        <c:forEach items="${passedEvents}" var="passed">
                                            <c:choose>
                                                <c:when test="${passed.priority == 'Normal'}">
                                                    <li class="list-group-item list-group-item-success"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${passed.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${passed.eventExecutionDate} ${passed.eventExecutionTime}
                                                                - ${passed.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/edit/${passed.id}"
                                                                                                  class="btn btn-dark btn-sm text-white">Edit</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                                <c:when test="${activeevent.priority == 'Important'}">
                                                    <li class="list-group-item list-group-item-warning"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${passed.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${passed.eventExecutionDate} ${passed.eventExecutionTime}
                                                                - ${passed.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/edit/${passed.id}"
                                                                                                  class="btn btn-dark btn-sm text-white">Edit</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                                <c:when test="${activeevent.priority == 'Urgent'}">
                                                    <li class="list-group-item list-group-item-danger"
                                                        data-toggle="tooltip" data-placement="top"
                                                        title="${passed.eventDescription}">
                                                        <div class="row justify-content-between">
                                                            <div class="col-xxl mr-n2 ml-n2">${passed.eventExecutionDate} ${passed.eventExecutionTime}
                                                                - ${passed.eventName}</div>
                                                            <div class="col-xxl-4 mr-n2 ml-n2"><a type="button"
                                                                                                  href="/user/event/edit/${passed.id}"
                                                                                                  class="btn btn-dark btn-sm text-white">Edit</a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </main>
    <%@ include file="/WEB-INF/views/user/footer.jsp" %>
</html>

