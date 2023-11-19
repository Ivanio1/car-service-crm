<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="card-columns">
        <#list images as img>
            <a href="/images/${img.id}" class="product-link">
                <div class="card text-white bg-dark my-3 offer">
                    <img src="/images/${img.id}"
                         class="card-img-top">
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
    <hr>
    <p><h1 style="text-align: center">${offer.name} | ${offer.price} ₽</h1></p>
    <p style="text-align: center">${offer.description}</p>
    <hr>

</@t.template>
