/*!
    * Start Bootstrap - SB Admin v7.0.4 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2021 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
//
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }


    function enableRegister() {
        const registerButton = document.querySelector("#registerButton");
        const inputRegisterPassword = document.querySelector("#inputReisterPassword");
        const inputRegisterPasswordConfirm = document.querySelector("#inputRegisterPasswordConfirm");

        if (inputRegisterPassword.value === inputRegisterPasswordConfirm.value && inputRegisterPassword.value.length !== 0) {
            registerButton.classList.remove("disabled");
        } else {
            registerButton.classList.add("disabled");
        }
    }

    const inputRegisterPassword = document.querySelector("#inputReisterPassword");
    if (inputRegisterPassword != null) {
        addEventListener("keyup", enableRegister)
    }

    function enableEdit() {
        const editButton = document.querySelector("#editButtonWithPassword");
        const inputEditPassword = document.querySelector("#inputEditPassword");
        const inputEditPasswordConfirm = document.querySelector("#inputEditPasswordConfirm");

        if (inputEditPassword.value === inputEditPasswordConfirm.value && inputEditPassword.value.length !== 0) {
            editButton.classList.remove("disabled");
        } else {
            editButton.classList.add("disabled");
        }
    }

    const inputEditPassword = document.querySelector("#inputEditPassword");
    if (inputEditPassword != null) {
        addEventListener("keyup", enableEdit)
    }

    function changeDeadline() {
        const isExecutionDateTime = document.querySelector("#isExecutionDateTime");
        const eventWithDeadline = document.querySelector("#eventWithDeadline");
        const eventWithoutDeadline = document.querySelector("#eventWithoutDeadline");

        if (isExecutionDateTime.checked) {
            eventWithoutDeadline.classList.add("hide-form");
            eventWithDeadline.classList.remove("hide-form");
        } else {
            eventWithDeadline.classList.add("hide-form");
            eventWithoutDeadline.classList.remove("hide-form");
        }
    }

    const isExecutionDateTime = document.querySelector("#isExecutionDateTime");
    if (isExecutionDateTime != null) {
        isExecutionDateTime.addEventListener("change", changeDeadline);
    }


    function changePassword() {
        const checkboxIfPassword = document.querySelector("#ifPassword");
        const editWithPassword = document.querySelector("#editWithPassword");
        const editWithoutPassword = document.querySelector("#editWithoutPassword");

        if (checkboxIfPassword.checked) {
            editWithoutPassword.classList.add("hide-form");
            editWithPassword.classList.remove("hide-form");
        } else {
            editWithPassword.classList.add("hide-form");
            editWithoutPassword.classList.remove("hide-form");
        }
    }

    const checkboxIfPassword = document.querySelector("#ifPassword");
    if (checkboxIfPassword != null) {
        checkboxIfPassword.addEventListener("change", changePassword)
    }


    function ifCheckboxEditChecked() {

        const editWithPassword = document.querySelector("#editWithPassword");
        const editWithoutPassword = document.querySelector("#editWithoutPassword");
        if (document.querySelector("#ifPassword").checked) {
            editWithoutPassword.classList.add("hide-form");
            editWithPassword.classList.remove("hide-form");
        }
    }

    if (document.querySelector("#ifPassword") != null) {
        window.addEventListener('load', ifCheckboxEditChecked);
    }

    function ifCheckboxEventDeadline() {

        const eventWithDeadline = document.querySelector("#eventWithDeadline");
        const eventWithoutDeadline = document.querySelector("#eventWithoutDeadline");
        if (document.querySelector("#isExecutionDateTime").checked) {
            eventWithoutDeadline.classList.add("hide-form");
            eventWithDeadline.classList.remove("hide-form");
        } else {
            eventWithDeadline.classList.add("hide-form");
            eventWithoutDeadline.classList.remove("hide-form");
        }
    }

    if (document.querySelector("#isExecutionDateTime") != null) {
        window.addEventListener('load', ifCheckboxEventDeadline);
    }

    let a = "a";
    a.toLowerCase().includes()

    // function showCategory(selectObject) {
    //     let allRows = document.querySelectorAll("[data-all]");
    //     let all ="all";
    //     console.log(all);
    //     allRows.forEach(function (el) {
    //         let categoryId = el.id;
    //         if (selectObject.value != categoryId && selectObject.value!=all) {
    //             el.classList.add("hide-row");
    //         } else if (selectObject.value == all) {
    //             el.classList.remove("hide-row");
    //         } else {
    //             el.classList.remove("hide-row");
    //         }
    //     });
    // }

    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })

});

