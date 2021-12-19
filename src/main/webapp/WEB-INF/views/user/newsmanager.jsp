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
                <div class="col-xxl-2">
                    <div class="card mb-4 mt-4">
                        <div class="card-header">
                            News categories
                        </div>
                        <div class="card-body justify-content-center">
                            <div class="row">
                                <div class="col-4"></div>
                                <div class="col-4">
                                    <form:form method="post" modelAttribute="user" cssClass="form-span">
                                        <div class="form-floating mb-3 form-div">
                                            <form:checkboxes cssClass="form-check-input checkbox-mr" id="selectNewsRss"
                                                             itemLabel="newsCategoryName"
                                                             items="${newsRssCategoryList}" path="newsRssList"/>
                                        </div>
                                        <form:hidden path="roles" />
                                        <form:hidden path="username"/>
                                        <form:hidden path="email"/>
                                        <form:hidden path="password"/>
                                        <form:hidden path="id"/>
                                        <form:hidden path="enabled"/>
                                        <div class="mt-4 mb-0">
                                            <div class="d-grid">
                                                <button type="submit" class="btn btn-dark btn-block">
                                                    Save
                                                </button>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                                <div class="col-4"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="/WEB-INF/views/user/footer.jsp" %>
</html>

