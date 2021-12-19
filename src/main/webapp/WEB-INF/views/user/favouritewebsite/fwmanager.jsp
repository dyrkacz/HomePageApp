<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/views/user/header.jsp" %>
<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <div class="row">
                <div class="col-xxl-9">
                    <div class="card mb-4 mt-4">
                        <div class="card-header">
                            New website
                        </div>
                        <div class="card-body">

                            <form:form method="post" modelAttribute="favouriteWebsite">
                                <div class="row mb-0">
                                    <div class="col-md-5">
                                        <div class="form-floating mb-3">
                                            <form:input class="form-control" id="inputWebsiteName" type="text"
                                                        placeholder="Enter the name of website" path="websiteName"/>
                                            <label for="inputWebsiteName">Website Name</label>
                                        </div>
                                        <div class="form-floating mb-3">
                                            <form:errors class="validation-error" path="websiteName"/>
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="form-floating mb-3">
                                            <form:input class="form-control" id="inputWebsiteUrl" type="text"
                                                        placeholder="Enter website url" path="websiteUrl"/>
                                            <label for="inputWebsiteUrl">Website URL</label>
                                        </div>
                                        <div class="form-floating mb-3">
                                            <form:errors class="validation-error" path="websiteUrl"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-floating mb-3">
                                            <form:select class="form-select" itemValue="id" type="select"
                                                         itemLabel="categoryName"
                                                         path="favouriteWebsiteCategory"
                                                         id="selectFavouriteWebsiteCategory"
                                                         items="${favouriteWebsiteCategories}"/>
                                            <label for="selectFavouriteWebsiteCategory">Category</label>
                                        </div>
                                        <div class="form-floating mb-3">
                                            <form:errors class="validation-error" path="websiteUrl"/>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="ifFavouriteWebsite" value="true">
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
                <div class="col-xxl-3">
                    <div class="card mb-4 mt-4">
                        <div class="card-header">
                            New Category
                        </div>
                        <div class="card-body">
                            <form:form method="post" modelAttribute="favouriteWebsiteCategory">
                                <div class="row mb-0">
                                    <div class="col-md-12">
                                        <div class="form-floating mb-3">
                                            <form:input class="form-control" id="inputGameName" type="text"
                                                        placeholder="Enter the name of Category" path="categoryName"/>
                                            <label for="inputGameName">Category Name</label>
                                        </div>
                                        <div class="form-floating mb-3">
                                            <form:errors class="validation-error" path="categoryName"/>
                                        </div>
                                    </div>
                                    <input type="hidden" name="ifFavouriteWebsite" value="false">
                                    <div class="mt-4 mb-0">
                                        <div class="d-grid">
                                            <button type="submit" class="btn btn-dark btn-block">
                                                Save
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-xxl-9">
                    <div class="card mb-4">
                        <div class="card-header">
                            <div class="row justify-content-between">
                                <div class="col-7">
                                    Favourite websites
                                </div>

                                <div class="col-2 mr-n5">
                                    <div class="input">
                                        <input class="form-control" type="text" placeholder="Search for..."
                                               aria-label="Search for..." aria-describedby="btnNavbarSearch"
                                               onkeyup="showWebsite(this)"/>
                                    </div>
                                </div>
                                <script type="text/javascript">
                                    function showWebsite(selectObject) {
                                        let allWebsites = document.querySelectorAll("[data-website]");
                                        allWebsites.forEach(function (el) {
                                            let websiteId = el.id;
                                            if (!websiteId.toLowerCase().includes(selectObject.value.toLowerCase())) {
                                                el.classList.add("hide-row");
                                            } else {
                                                el.classList.remove("hide-row");
                                            }
                                        });
                                    }
                                </script>
                                <div class="col-3">
                                    <select id="categorySelect" class="form-select" onchange="showCategory(this)">
                                        <option value="all">Select category</option>
                                        <option value="all">All categories</option>
                                        <c:forEach items="${favouriteWebsiteCategories}" var="favouriteWebsiteCategory">
                                            <option value="${favouriteWebsiteCategory.categoryName}">${favouriteWebsiteCategory.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                    <script type="text/javascript">
                                        function showCategory(selectObject) {
                                            let allRows = document.querySelectorAll("[data-all]");
                                            let all = "all";
                                            allRows.forEach(function (el) {
                                                let categoryId = el.id;
                                                if (selectObject.value != categoryId && selectObject.value != all) {
                                                    el.classList.add("hide-row");
                                                } else if (selectObject.value == all) {
                                                    el.classList.remove("hide-row");
                                                } else {
                                                    el.classList.remove("hide-row");
                                                }
                                            });
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                        <div class="card-body mb-n4">
                            <div class="row">
                                <div class="col-3">
                                    <b>Website name</b>
                                </div>
                                <div class="col-7">
                                    <b>Website URL</b>
                                </div>
                                <div class="col-1">
                                    <b>Category</b>
                                </div>
                                <div class="col-1">
                                    <b>Delete</b>
                                </div>
                            </div>
                        </div>
                        <!-- Divider -->
                        <hr class="sidebar-divider">
                        <!-- Card Body -->
                        <div class="card-body scroll-fwmanager">


                            <c:forEach items="${favouriteWebsites}"
                                       var="favouriteWebsite"> <%--                                <c:if test="${param.category == favouriteWebsite.favouriteWebsiteCategory.categoryName || empty param}">--%>
                                <div data-website="website" id="${favouriteWebsite.websiteName}">
                                    <div data-all="all" id="${favouriteWebsite.favouriteWebsiteCategory.categoryName}">
                                        <div class="row">
                                            <div class="col-3">
                                                    ${favouriteWebsite.websiteName}
                                            </div>
                                            <div class="col-7">
                                                    ${favouriteWebsite.websiteUrl}
                                            </div>
                                            <div class="col-1">
                                                    ${favouriteWebsite.favouriteWebsiteCategory.categoryName}
                                            </div>
                                            <div class="col-1">
                                                    <%--                                                <a class="btn btn-danger btn- active" id="delete-game" role="button"--%>
                                                    <%--                                                   aria-pressed="true"--%>
                                                    <%--                                                   href="/user/game/delete/${favouriteWebsite.id}">Delete</a>--%>
                                                <button type="button" class="btn btn-danger btn- active"
                                                        data-toggle="modal"
                                                        data-target="#websiteModalCenter${favouriteWebsite.id}"
                                                        aria-pressed="true">
                                                    Delete
                                                </button>

                                                <!-- Modal -->
                                                <div class="modal fade"
                                                     id="websiteModalCenter${favouriteWebsite.id}"
                                                     tabindex="-1" role="dialog"
                                                     aria-labelledby="websiteModalCenterTitle${favouriteWebsite.id}"
                                                     aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title"
                                                                    id="websiteModalCenterTitle${favouriteWebsite.id}">
                                                                    Delete</h5>
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Are you sure you want to
                                                                delete ${favouriteWebsite.websiteName}?
                                                            </div>
                                                            <div class="modal-footer">
                                                                <a type="button" class="btn btn-danger active"
                                                                   aria-pressed="true"
                                                                   href="/user/favouritewebsite/delete/${favouriteWebsite.id}">Delete</a>
                                                                <a type="button" class="btn btn-dark active"
                                                                   aria-pressed="true" data-dismiss="modal">No</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Divider -->
                                        <hr class="sidebar-divider">
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-3">
                    <div class="card mb-4">
                        <div class="card-header">
                            Categories
                        </div>
                        <div class="card-body mb-n4">
                            <div class="row">
                                <div class="col-9">
                                    <b>Category name</b>
                                </div>
                                <div class="col-3">
                                    <b>Delete</b>
                                </div>
                            </div>
                            <!-- Divider -->
                            <hr class="sidebar-divider">
                        </div>
                        <!-- Card Body -->
                        <div class="card-body scroll-fwmanager">


                            <c:forEach items="${favouriteWebsiteCategories}" var="favouriteWebsiteCategory">
                                <div class="row">
                                    <div class="col-9">
                                            ${favouriteWebsiteCategory.categoryName}
                                    </div>
                                    <div class="col-3">
                                        <button type="button" class="btn btn-danger btn- active"
                                                data-toggle="modal"
                                                data-target="#categoryModalCenter${favouriteWebsiteCategory.id}"
                                                aria-pressed="true">
                                            Delete
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade"
                                             id="categoryModalCenter${favouriteWebsiteCategory.id}"
                                             tabindex="-1" role="dialog"
                                             aria-labelledby="categoryModalCenterTitle${favouriteWebsiteCategory.id}"
                                             aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title"
                                                            id="categoryModalCenterTitle${favouriteWebsiteCategory.id}">
                                                            Delete</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Are you sure you want to
                                                        delete ${favouriteWebsiteCategory.categoryName}?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <a type="button" class="btn btn-danger active"
                                                           aria-pressed="true"
                                                           href="/user/favouritewebsitecategory/delete/${favouriteWebsiteCategory.id}">Delete</a>
                                                        <a type="button" class="btn btn-dark active"
                                                           aria-pressed="true" data-dismiss="modal">No</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Divider -->
                                <hr class="sidebar-divider">
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="/WEB-INF/views/user/footer.jsp" %>
</html>

