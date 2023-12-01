<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Инструменты на складе</h1>
    <hr>
<div style="overflow-y: scroll;
                    height: 400px;"><table class="table table-hover table-dark table-striped">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Количество</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list tools as tool>
            <tr>
                <td>${tool.name}</td>
                <td>${tool.stock}</td>
                <td>
                    <form action="/admin/delete/tool/${tool.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-danger m-3">
                            Удалить
                        </button>
                    </form>
                </td>
            </tr>
        <#else>
            <h3>Нет инструментов</h3>
        </#list>
        </tbody>
    </table>
</div>
    <hr>
    <h2>Добавить инструмент</h2>
    <form action="/admin/add/tool" method="post">
        <hr>
        <div>
            <label>Введите название: <input type="text" name="name" required></label>
            <label>Введите количество: <input type="number" name="stock" required></label>
        </div>
        <br>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button id="saveButton" type="submit" class="btn btn-dark">Добавить</button>
    </form>
</@t.template>