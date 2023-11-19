<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Наши услуги</h1><hr>
    <form method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-10">
                <label for="searchWord">Поиск</label>
                <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                       id="searchWord">
            </div>
            <br>
            <button style="width: 100%" type="submit" class="btn btn-dark">Найти</button>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list offers as offer>
            <a href="/offer/${offer.id}" class="offer-link">
                <div class="card text-white bg-dark my-3 product">
                    <div class="card-body">
                        <h5 class="card-title">${offer.name}</h5>
                        <p class="card-text">${offer.price} ₽</p>
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Ничего не найдено</h1>
        </#list>
    </div>
</@t.template>
