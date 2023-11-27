<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Детали на складе</h1>
    <hr>
    <table class="table table-hover table-dark table-striped">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Количество</th>
        </tr>
        </thead>
        <tbody>
        <#list details as detail>
            <tr>
                <td>${detail.name}</td>
                <td>${detail.storagestock}</td>
            </tr>
        <#else>
            <h3>Нет деталей</h3>
        </#list>
        </tbody>
    </table>

</@t.template>
