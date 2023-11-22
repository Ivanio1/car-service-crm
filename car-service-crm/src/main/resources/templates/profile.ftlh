<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мой аккаунт</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Электронная почта: <b>${user.email}</b>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        <br>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель администратора
            </a><br>
        </#if>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/my/products">
            Мои заказы - не сделано
        </a>

        <form action="/user/add/review" method="post">
            <hr>
            <h3>Оставить отзыв</h3>
            <label>Услуга: <select name="offer" id="offer-select">
                    <option value="">--Выберите услугу--</option>
                     <#list offers as offer>
                         <option value="${offer.name}">${offer.name}</option>
                     <#else>
                         <h1 style="text-align: center">Ничего не найдено</h1>
                     </#list>
                </select></label>

            <label>Текст отзыва: <input type="text" name="reviewText" required></label>
            <label>Рейтинг: <input type="number" name="rating" required min="1" max="5"></label>
            <input type="hidden" value="${user.email}" name="email">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <br><br>
            <button id="saveButton" type="submit" class="btn btn-dark" >Отправить</button>
            <hr>
        </form>

        <form action="/operator/add/question" method="post">
            <h3>Появился вопрос?</h3>
            <input type="text" name="question" required placeholder="Задай его нам" style="border-radius: 7px;">
            <input type="hidden" value="${user.email}" name="email">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <br><br>
            <button id="saveButton" type="submit" class="btn btn-dark" onclick="toggleTextField()">Отправить</button>
            <p id="textField" style="width: 100%;align-content: center;align-items: center;display: none">
                Спасибо за обращение. Скоро с вами свяжутся наши сотрудники и помогут решить любой вопрос.
            </p>
            <hr>
        </form>

        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти
            </button>
        </form>
    </div>
    <script>
        function toggleTextField() {
            var textField = document.getElementById("textField");
            textField.style.display = "block";
        }
    </script>
</@t.template>