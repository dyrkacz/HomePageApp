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
                <div class="col-xl-4">
                    <div class="card mb-4 mt-4">
                        <div class="card-header">
                            Edit ${currentUser.getUsername()}
                        </div>
                        <div class="card-body">
                            <div class="card-body">
                                <c:choose>
                                    <c:when test="${changePassword==true}">
                                        <div class="form-check-inline mb-3">
                                            <input type="checkbox" id="ifPassword" checked
                                                   class="form-check-input mr-2"/>
                                            <label class="form-check-label" for="ifPassword">Change Password</label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-check-inline mb-3">
                                            <input type="checkbox" id="ifPassword" class="form-check-input mr-2"/>
                                            <label class="form-check-label" for="ifPassword">Change Password</label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <%--                        <div class="form-check-inline mb-3">--%>
                                <%--                            <input type="checkbox" id="ifPassword" class="form-check-input mr-2"/>--%>
                                <%--                            <label class="form-check-label" for="ifPassword">Change Password</label>--%>
                                <%--                        </div>--%>
                                <form:form method="post" modelAttribute="user" id="editWithPassword" class="hide-form">
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputUsername" type="text"
                                                    placeholder="Enter your username" path="username"/>
                                        <label for="inputUsername">Username</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="username"/>
                                    </div>
                                    <c:if test="${not empty errorUsername}">
                                        <div class="form-floating mb-3">
                                            <span class="validation-error">A user with the given name already exists</span>
                                        </div>
                                    </c:if>
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputEmail" type="email"
                                                    placeholder="name@example.com" path="email"/>
                                        <label for="inputEmail">Email address</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="email"/>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                    <%--                                        <form:input class="form-control" id="inputEditPassword" type="password"--%>
                                                    <%--                                                    placeholder="Create a password" path="password"--%>
                                                    <%--                                                    aria-autocomplete="none" value=""/>--%>
                                                <input id="inputEditPassword" name="password" aria-autocomplete="list"
                                                       placeholder="Create a password" type="password"
                                                       class="form-control">
                                                <label for="inputEditPassword">Password</label>
                                            </div>
                                            <div class="form-floating mb-3 mb-md-0">
                                                <form:errors class="validation-error" path="password"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputEditPasswordConfirm"
                                                       type="password"
                                                       placeholder="Confirm password"/>
                                                <label for="inputEditPasswordConfirm">Confirm Password</label>
                                            </div>
                                        </div>
                                    </div>
                                    <form:hidden path="newsRssList" />
                                    <form:hidden path="roles" />
                                    <form:hidden path="id"/>
                                    <form:hidden path="enabled"/>
                                    <input type="hidden" name="changePassword" value="true">
                                    <div class="mt-4 mb-0">
                                        <div class="d-grid">
                                            <button type="submit" class="btn btn-dark btn-block disabled"
                                                    id="editButtonWithPassword">Save
                                            </button>
                                        </div>
                                    </div>
                                </form:form>
                                <form:form method="post" modelAttribute="user" id="editWithoutPassword">
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputUsername" type="text"
                                                    placeholder="Enter your username" path="username"/>
                                        <label for="inputUsername">Username</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="username"/>
                                    </div>
                                    <c:if test="${not empty errorUsername}">
                                        <div class="form-floating mb-3">
                                            <span class="validation-error">A user with the given name already exists</span>
                                        </div>
                                    </c:if>
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputEmail" type="email"
                                                    placeholder="name@example.com" path="email"/>
                                        <label for="inputEmail">Email address</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="email"/>
                                    </div>
                                    <form:hidden path="newsRssList" />
                                    <form:hidden path="roles" />
                                    <form:hidden path="password"/>
                                    <form:hidden path="id"/>
                                    <form:hidden path="enabled"/>
                                    <input type="hidden" name="changePassword" value="false">
                                    <div class="mt-4 mb-0">
                                        <div class="d-grid">
                                            <button type="submit" class="btn btn-dark btn-block"
                                                    id="editButtonWithoutPassword">
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

