<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Панель администратора</h1>
    <hr>
    <div>
        <h2 style="text-align: center">Работа с услугами</h2>
        <div style=" display: flex;
            height: 20%;
            flex-flow: row wrap;
            align-items: center;
            justify-content: center;">
            <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 30%"
               data-toggle="collapse" href="/offer/addform" role="button"
               aria-expanded="true" aria-controls="collapseExample">
                Добавить услугу
            </a>
            &nbsp &nbsp &nbsp
            <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 30%"
               data-toggle="collapse" href="/offer/deleteform" role="button"
               aria-expanded="true" aria-controls="collapseExample">
                Удалить услугу
            </a>
        </div>
        <hr>
        <h2 style="text-align: center">Просмотр и изменение всех пользователей.</h2>
        <div style=" display: flex;
            height: 20%;
            flex-flow: row wrap;
            align-items: center;
            justify-content: center;">
            <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 30%"
               data-toggle="collapse" href="/admin/users" role="button"
               aria-expanded="true" aria-controls="collapseExample">
                Пользователи
            </a>
        </div>

        <hr>
        <h2 style="text-align: center">Просмотр клиентов, работников, операторов и изготовителей деталей.</h2>
        <div style=" display: flex;
            height: 20%;
            flex-flow: row wrap;
            align-items: center;
            justify-content: center;">
            <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 30%"
               data-toggle="collapse" href="/admin/clients" role="button"
               aria-expanded="true" aria-controls="collapseExample">
                Клиенты
            </a>
            <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 30%;margin-left: 5%"
               data-toggle="collapse" href="/admin/workers" role="button"
               aria-expanded="true" aria-controls="collapseExample">
                Работники
            </a>
        </div>
    </div>
</@t.template>
