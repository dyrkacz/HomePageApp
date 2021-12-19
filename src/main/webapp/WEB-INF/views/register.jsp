<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/startheader.jsp" %>
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">Create Account</h3></div>
                            <div class="card-body">
                                <form:form method="post" modelAttribute="user">
                                    <div class="form-floating mb-3">
                                        <form:input  class="form-control" id="inputUsername" type="text" placeholder="Enter your username" path="username"/>
                                        <label for="inputUsername">Username</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors  class="validation-error"  path="username"/>
                                    </div>
<%--                                    <c:if test="${not empty errorUsername}">--%>
<%--                                        <div class="form-floating mb-3">--%>
<%--                                            <span class="validation-error">A user with the given name already exists</span>--%>
<%--                                        </div>--%>
<%--                                    </c:if>--%>
                                    <div class="form-floating mb-3">
                                        <form:input class="form-control" id="inputEmail" type="email" placeholder="name@example.com" path="email"/>
                                        <label for="inputEmail">Email address</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:errors class="validation-error" path="email"/>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <form:input class="form-control" id="inputReisterPassword" type="password" placeholder="Create a password" path="password"/>
                                                <label for="inputReisterPassword">Password</label>
                                            </div>
                                            <div class="form-floating mb-3 mb-md-0">
                                                <form:errors class="validation-error" path="password"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputRegisterPasswordConfirm" type="password" placeholder="Confirm password" />
                                                <label for="inputRegisterPasswordConfirm">Confirm Password</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mt-4 mb-0">
                                        <div class="d-grid"><button type="submit" class="btn btn-dark btn-block disabled" id="registerButton" >Create Account</button></div>
                                    </div>
                                </form:form>
                            </div>
                            <div class="card-footer text-center py-3">
                                <div class="small"><a href="/login">Have an account? Go to login</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
<%@ include file="/WEB-INF/views/startfooter.jsp" %>
</html>

