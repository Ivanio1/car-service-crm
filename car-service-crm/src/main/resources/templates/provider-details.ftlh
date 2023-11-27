<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Детали поставщика</h1>
    <hr>
    <table class="table table-hover table-dark table-striped">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Количество</th>
            <th scope="col">Цена</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list details as detail>
            <tr>
                <td>${detail.name}</td>
                <td>${detail.stock}</td>
                <td>${detail.price} ₽</td>
                <td>
                    <form action="/admin/provider/${provider.id}/delete/detail/${detail.id}" method="post">

                        <input type="hidden" value="${user.email}" name="email">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-danger m-3">
                            Удалить
                        </button>
                    </form>
                </td>
            </tr>
        <#else>
            <h3>Нет деталей</h3>
        </#list>
        </tbody>
    </table>
    <hr>
</@t.template>
