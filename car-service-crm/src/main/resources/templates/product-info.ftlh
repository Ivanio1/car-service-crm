<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="card-columns">
        <#list images as img>
            <a href="/images/${img.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <img src="/images/${img.id}"
                         class="card-img-top">
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Not found</h1>
        </#list>
    </div>
    <hr>
    <p><h1 style="text-align: center">${product.title} | ${product.price} €</h1></p>
    <p style="text-align: center">${product.description}</p>
    <p style="text-align: center">Seller's phone number: <b>${authorProduct.phoneNumber}</b></p>

    <hr>
    <p style="text-align: center">
        <a href="/user/${authorProduct.id}">
            <img src="/static/images/avatar.png" alt="mdo" width="32" height="32"
                 class="rounded-circle"> ${authorProduct.name}
        </a>
    </p>

</@t.template>
