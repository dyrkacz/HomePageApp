<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/startheader.jsp" %>
<main>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Login</h3></div>
                    <div class="card-body">
                        <%--                                <form method="post">--%>
                        <%--                                    <div><label> User Name : <input type="text" name="username"/> </label></div>--%>
                        <%--                                    <div><label> Password: <input type="password" name="password"/> </label></div>--%>
                        <%--                                    <div><input type="submit" value="Sign In"/></div>--%>
                        <%--                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                        <%--                                </form>--%>

                        <form method="post">
                            <div class="form-floating mb-3">
                                <input class="form-control" id="inputEmail" type="text" placeholder="name@example.com"
                                       name="username"/>
                                <label for="inputEmail">Username</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input class="form-control" id="inputPassword" type="password" placeholder="Password"
                                       name="password"/>
                                <label for="inputPassword">Password</label>
                            </div>
                            <c:if test="${not empty param}">
                                <span class="validation-error">E-mail or password are incorrect</span>
                            </c:if>
                            <%--                                    <div class="form-check mb-3">--%>
                            <%--                                        <input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" />--%>
                            <%--                                        <label class="form-check-label" for="inputRememberPassword">Remember Password</label>--%>
                            <%--                                    </div>--%>
                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                <%--                                        <a class="small" href="password.html">Forgot Password?</a>--%>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-dark">Login</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center py-3">
                        <div class="small"><a href="/register">Need an account? Sign up!</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</div>
<%@ include file="/WEB-INF/views/startfooter.jsp" %>
</html>


