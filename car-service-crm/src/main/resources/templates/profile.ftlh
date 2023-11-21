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
            <h3>Оставить отзыв или обращение</h3>
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
            <button id="saveButton" type="submit" class="btn btn-dark" >Отправить</button>
            <hr>
        </form>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти
            </button>
        </form>
    </div>
</@t.template>