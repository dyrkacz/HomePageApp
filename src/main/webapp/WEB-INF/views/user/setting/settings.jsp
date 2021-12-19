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
                <div class="col-md-auto">
                    <div class="card mb-4 mt-4">
                        <div class="card-header">
                            Location
                        </div>
                        <div class="card-body">
                            <form:form method="post" modelAttribute="settingCurrentLocation">
                                <div class="col">
                                    <div class="form-floating mb-3">
                                        <form:select class="form-select" itemValue="cityId" type="select"
                                                     itemLabel="cityName"
                                                     path="settingValue"
                                                     id="selectSettingValue"
                                                     items="${cities}"/>
                                        <label for="selectSettingValue">Select city for weather</label>
                                    </div>
                                </div>
                                <form:hidden path="id"/>
                                <form:hidden path="settingKey" value="currentLocation"/>
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
            <div class="row justify-content-md-center">
                <div class="col-md-auto">
                    <div class="card mb-4 mt-4">
                        <div class="card-header">
                            Twitter Id
                        </div>
                        <div class="card-body">
                            <form:form method="post" modelAttribute="settingTwitterId">
                                <div class="col">
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputTwitterId" type="text"
                                                    placeholder="Enter twitter id" path="settingValue"/>
                                        <label for="inputTwitterId">Twitter id</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="settingValue"/>
                                    </div>
                                </div>
                                <form:hidden path="id"/>
                                <form:hidden path="settingKey" value="twitterId"/>
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
            <div class="row justify-content-md-center">
                <div class="col-md-auto">
                    <div class="card mb-4 mt-4">
                        <div class="card-header">
                            Twitter Token
                        </div>
                        <div class="card-body">
                            <form:form method="post" modelAttribute="settingTwitterToken">
                                <div class="col">
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputTwitterToken" type="text"
                                                    placeholder="Enter twitter token" path="settingValue"/>
                                        <label for="inputTwitterToken">Twitter token</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="settingValue"/>
                                    </div>
                                </div>
                                <form:hidden path="id"/>
                                <form:hidden path="settingKey" value="twitterToken"/>
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
    </main>
    <%@ include file="/WEB-INF/views/user/footer.jsp" %>
</html>

