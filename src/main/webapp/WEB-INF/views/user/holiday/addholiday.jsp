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
                            New holiday
                        </div>
                        <div class="card-body">
                            <div class="card-body">
                                <form:form method="post" modelAttribute="holiday">
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputHolidayName" type="text"
                                                    placeholder="Enter the name of the holiday" path="holidayName"/>
                                        <label for="inputHolidayName">Holiday Name</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="holidayName"/>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputHolidayDate" type="date"
                                                    path="holidayDate"/>
                                        <label for="inputHolidayDate">Holiday date</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="holidayDate"/>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <div class="dataTable-dropdown">
                                            <form:label path="priority">Priority:</form:label>
                                            <form:select class="dataTable-selector" id="priority"
                                                         path="priority" items="${holiday.priorities}">
                                            </form:select>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="form-check-inline mb-3">
                                            <form:checkbox path="annual" id="annualCheckbox"
                                                           cssClass="form-check-input mr-2"/>

                                            <form:label path="annual" for="annualCheckbox"
                                                        cssClass="form-check-label">Every year</form:label>
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
                                    <div class="mt-4 mb-0">
                                        <div class="d-grid">
                                            <button type="submit" class="btn btn-dark btn-block">
                                                Save
                                            </button>
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

