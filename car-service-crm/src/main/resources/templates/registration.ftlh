<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Регистрация</h1>
    <hr>


    <form class="row g-3 requires-validation" novalidate action="/registration" method="post">
        <div class="col-md-12">
            <label for="name" class="form-label">Имя</label>
            <input type="text" class="form-control" required id="name" name="name">
            <div class="valid-feedback">
            </div>
            <div class="invalid-feedback">
                Неверное имя
            </div>
        </div>
        <div class="col-md-12">
            <label for="surname" class="form-label">Фамилия</label>
            <input type="text" class="form-control" required id="surname" name="surname">
            <div class="valid-feedback">
            </div>
            <div class="invalid-feedback">
                Неверная фамилия
            </div>
        </div>
        <div class="col-md-12">
            <label for="email" class="form-label">Электронная почта</label>
            <input type="email" class="form-control" id="email" required name="email">
            <div class="valid-feedback">

            </div>
            <div class="invalid-feedback">
                Неверная электронная почта
            </div>
        </div>
        <div class="col-md-12">
            <label for="phoneNumber" class="form-label">Номер телефона</label>
            <input type="tel" class="form-control" id="phoneNumber" placeholder="+7**********" pattern="^\+[7]{1}[0-9]{10}$" required name="phoneNumber">
            <div class="valid-feedback">

            </div>
            <div class="invalid-feedback">
                Неверный номер телефона
            </div>
        </div>
        <div class="col-md-12">
            <label for="password" class="form-label">Пароль</label>
            <input type="password" class="form-control" id="password" required name="password">
            <div class="valid-feedback">

            </div>
            <div class="invalid-feedback">
                Неверный пароль
            </div>
        </div>

        <div class="col-12">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-dark" style="width: 100%" type="submit">Войти</button>
        </div>
    </form>


    <script>
        (function () {
            'use strict'
            const forms = document.querySelectorAll('.requires-validation')
            Array.from(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }

                        form.classList.add('was-validated')
                    }, false)
                })
        })()
    </script>
    <#if errorMessage??>
        <h2 style="color: red;alignment: center">${errorMessage}</h2>
    </#if>
</@t.template>
