<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/views/user/header.jsp" %>
<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <div class="row justify-content-md-center">
                <div class="col-xxl-4">
                    <div class="card mb-4 mt-4">
                        <div class="card-header">
                            Edit ${event.eventName}
                        </div>
                        <div class="card-body">
                            <div class="card-body">
                                <c:choose>
                                    <c:when test="${event.eventNoExecutionDateTime==false}">
                                        <div class="form-check-inline mb-3">
                                            <input type="checkbox" id="isExecutionDateTime" checked
                                                   class="form-check-input mr-2"/>
                                            <label class="form-check-label" for="isExecutionDateTime">Deadline</label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-check-inline mb-3">
                                            <input type="checkbox" id="isExecutionDateTime"
                                                   class="form-check-input mr-2"/>
                                            <label class="form-check-label" for="isExecutionDateTime">Deadline</label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <form:form method="post" modelAttribute="event" id="eventWithDeadline">
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputEventName" type="text"
                                                    placeholder="Enter the name of the event" path="eventName"/>
                                        <label for="inputEventName">Event name</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="eventName"/>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputEventDescription" type="text"
                                                    placeholder="Enter the description of event"
                                                    path="eventDescription"/>
                                        <label for="inputEventDescription">Event description</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="eventDescription"/>
                                    </div>
                                    <form:hidden path="eventNoExecutionDateTime" value="false"/>
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputEventDate" type="date"
                                                    path="eventExecutionDate"/>
                                        <label for="inputEventDate">Event date</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="eventExecutionDate"/>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputEventTime" type="time"
                                                    path="eventExecutionTime"/>
                                        <label for="inputEventTime">Event time</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="eventExecutionTime"/>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <div class="dataTable-dropdown">
                                            <form:label path="priority">Priority:</form:label>
                                            <form:select class="dataTable-selector" id="priority"
                                                         path="priority" items="${event.priorities}">
                                            </form:select>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="form-check-inline mb-3">
                                            <form:checkbox path="emailReminder" id="emailReminderCheckbox"
                                                           cssClass="form-check-input mr-2"/>
                                            <form:label path="emailReminder" for="emailReminderCheckbox"
                                                        cssClass="form-check-label">E-mail remainder</form:label>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="form-check-inline mb-3">
                                            <form:checkbox path="done" id="doneCheckbox"
                                                           cssClass="form-check-input mr-2"/>
                                            <form:label path="done" for="doneCheckbox"
                                                        cssClass="form-check-label">Done</form:label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-8">
                                            <div class="mt-4 mr-n3">
                                                <div class="d-grid">
                                                    <button type="submit" class="btn btn-dark btn-block">
                                                        Save
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="mt-4 ml-n2">
                                                <div class="d-grid">
                                                    <a class="btn btn-danger btn-block" role="button"
                                                       aria-pressed="true"
                                                       href="/user/event/list">Back</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                                <form:form method="post" modelAttribute="event" id="eventWithoutDeadline"
                                           class="hide-form">
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputEventName" type="text"
                                                    placeholder="Enter the name of the event" path="eventName"/>
                                        <label for="inputEventName">Event name</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="eventName"/>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputEventDescription" type="text"
                                                    placeholder="Enter the description of event"
                                                    path="eventDescription"/>
                                        <label for="inputEventDescription">Event description</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="eventDescription"/>
                                    </div>
                                    <form:hidden path="eventNoExecutionDateTime" value="true"/>
                                    <div class="form-floating mb-3">
                                        <div class="dataTable-dropdown">
                                            <form:label path="priority">Priority:</form:label>
                                            <form:select class="dataTable-selector" id="priority"
                                                         path="priority" items="${event.priorities}">
                                            </form:select>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="form-check-inline mb-3">
                                            <form:checkbox path="emailReminder" id="emailReminderCheckbox"
                                                           cssClass="form-check-input mr-2"/>
                                            <form:label path="emailReminder" for="emailReminderCheckbox"
                                                        cssClass="form-check-label">E-mail remainder</form:label>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="form-check-inline mb-3">
                                            <form:checkbox path="done" id="doneCheckbox"
                                                           cssClass="form-check-input mr-2"/>
                                            <form:label path="done" for="doneCheckbox"
                                                        cssClass="form-check-label">Done</form:label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-8">
                                            <div class="mt-4 mr-n3">
                                                <div class="d-grid">
                                                    <button type="submit" class="btn btn-dark btn-block">
                                                        Save
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="mt-4 ml-n2">
                                                <div class="d-grid">
                                                    <a class="btn btn-danger btn-block" role="button"
                                                       aria-pressed="true"
                                                       href="/user/event/list">Back</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="/WEB-INF/views/user/footer.jsp" %>
</html>

