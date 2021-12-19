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
                            New game
                        </div>
                        <div class="card-body">
                            <div class="card-body">
                                <form:form method="post" modelAttribute="game">
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3">
                                                <form:input class="form-control" id="inputGameName" type="text"
                                                            placeholder="Enter the name of the game" path="gameName"/>
                                                <label for="inputGameName">Game Name</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <form:errors class="validation-error" path="gameName"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3">
                                                <form:input class="form-control" id="inputGameId" type="number"
                                                            placeholder="Enter Steam id of the game." path="gameId"/>
                                                <label for="inputGameId">Game Steam Id</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <form:errors class="validation-error" path="gameId"/>
                                            </div>
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
            <div class="row justify-content-md-center">
                <div class="col-xxl-6">
                    <div class="card mb-4">
                        <div class="card-header">
                            <div class="row justify-content-between">
                                <div class="col">
                                    Games
                                </div>
                                <div class="col-3">
                                        <div class="input">
                                            <input class="form-control" type="text" placeholder="Search for..."
                                                   aria-label="Search for..." aria-describedby="btnNavbarSearch" onkeyup="showGame(this)"/>
                                        </div>
                                </div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            function showGame(selectObject) {
                                let allGames = document.querySelectorAll("[data-game]");
                                allGames.forEach(function (el) {
                                    let gameId = el.id;
                                    if (!gameId.toLowerCase().includes(selectObject.value.toLowerCase())) {
                                        el.classList.add("hide-row");
                                    } else {
                                        el.classList.remove("hide-row");
                                    }
                                });
                            }
                        </script>
                        <div class="card-body mb-n4">
                            <div class="row">
                                <div class="col-6">
                                    <b>Game name</b>
                                </div>
                                <div class="col-3">
                                    <b>Steam id</b>
                                </div>
                                <div class="col-3">
                                    <b>Edit/Delete</b>
                                </div>
                            </div>
                            <!-- Divider -->
                            <hr class="sidebar-divider">
                        </div>
                        <!-- Card Body -->
                        <div class="card-body scroll-gamemanager">
                            <c:forEach items="${games}" var="game">
                                <div data-game="game" id="${game.gameName}">
                                    <div class="row">
                                        <div class="col-6">
                                                ${game.gameName}
                                        </div>
                                        <div class="col-3">
                                                ${game.gameId}
                                        </div>
                                        <div class="col-3">
                                            <button type="button" class="btn btn-danger btn- active"
                                                    data-toggle="modal"
                                                    data-target="#exampleModalCenter${game.id}" aria-pressed="true">
                                                Delete
                                            </button>

                                            <!-- Modal -->
                                            <div class="modal fade" id="exampleModalCenter${game.id}" tabindex="-1"
                                                 role="dialog" aria-labelledby="exampleModalCenterTitle${game.id}"
                                                 aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title"
                                                                id="exampleModalCenterTitle${game.id}">
                                                                Delete</h5>
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Are you sure you want to delete ${game.gameName}?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <a type="button" class="btn btn-danger active"
                                                               aria-pressed="true"
                                                               href="/user/game/delete/${game.id}">Delete</a>
                                                            <a type="button" class="btn btn-dark active"
                                                               aria-pressed="true"
                                                               data-dismiss="modal">No</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Divider -->
                                    <hr class="sidebar-divider">
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="/WEB-INF/views/user/footer.jsp" %>
</html>

