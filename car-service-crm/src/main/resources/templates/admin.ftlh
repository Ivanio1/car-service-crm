<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Панель администратора</h1><hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Почта</th>
            <th scope="col">Номер телефона</th>
            <th scope="col">Активность</th>
            <th scope="col">Роль</th>
            <th scope="col">Забанить</th>
            <th scope="col">Редактировать</th>
            <th scope="col">Подробная информация</th>
        </tr>
        <#list users as user>
            <tr>
                <th>${user.email}</th>
                <th>${user.phoneNumber}</th>
                <th><#if user.active>true<#else>false</#if></th>
                <th><#list user.roles as role>${role}</#list></th>
                <th>
                    <form action="/admin/user/ban/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-light" value="Бан">
                    </form>
                </th>
                <th><a href="/admin/user/edit/${user.id}">Редактировать</a></th>
                <th><a href="/user/${user.id}">Смотреть</a></th>
            </tr>
        <#else>
            <h3>Нет пользователей</h3>
        </#list>
    </table>
</@t.template>
