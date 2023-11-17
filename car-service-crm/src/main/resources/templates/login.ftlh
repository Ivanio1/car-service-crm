<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Авторизация</h1>
    <hr>
    <form class="row g-3 requires-validation" novalidate action="/login" method="post">
        <div class="col-md-12">
            <label for="email" class="form-label">Электронная почта:</label>
            <input type="email" class="form-control" id="email" required>
            <div class="valid-feedback">

            </div>
            <div class="invalid-feedback">
                Неверная электронная почта
            </div>
        </div>
        <div class="col-md-12">
            <label for="password" class="form-label">Пароль:</label>
            <input type="password" class="form-control" id="password" required>
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

    <hr>
    <h2>Нет аккаунта? <a href="/registration">Зарегистрируйся!</a></h2>

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
</@t.template>