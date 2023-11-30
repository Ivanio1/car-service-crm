<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Форма распределения</h1>
    <hr>
    <form action="/admin/allocate/purchase/${purchase.id}" method="post" id="application">
        <div style=" display: flex;
            height: 20%;
            flex-flow: row wrap;
            align-items: center;
            justify-content: center;">

           <label> Рабочий: <select name="worker" id="offer-select">
                        <option value="">--Выберите рабочего--</option>
                        <#list workers as worker>
                            <option value="${worker.id}">${worker.employee.name} ${worker.employee.surname}</option>
                        <#else>
                            <h1 style="text-align: center">Ничего не найдено</h1>
                        </#list>
                    </select></label>

            </div>
            <input type="hidden" value="${user.id}" name="userId">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </div>
        <br>
        <div style=" display: flex;
            height: 20%;
            flex-flow: row wrap;
            align-items: center;
            justify-content: center;">
            <button type="submit" class="btn btn-dark m-3" >
                Распределить
            </button>
        </div>
    </form>


</@t.template>
