<#import "blocks/template.ftlh" as t>
<@t.template user1>
    <h1 style="text-align: center">Изменить пользователя - ${user.name}</h1><hr>
    <form action="/admin/user/edit" method="post">
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" onclick="toggleTextField()"  ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-dark">Сохранить</button>
        <br> <br>
    </form>

    <form action="/admin/add/employee" method="post">
        <hr>
        <div id="snilsField" style="display: none;">
            <p>Сначала сохраните роль пользователя!</p>
            <label>СНИЛС: <input type="text" name="snils" pattern="^\d{3}-\d{3}-\d{3} \d{2}$" required></label>
        </div>
        <br>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button id="saveButton" type="submit" class="btn btn-dark" style="display: none;">Сохранить сотрудника</button>
    </form>

    <form action="/admin/add/worker" method="post">
        <div id="specializationField" style="display: none;">
            <hr>
            <p>Сначала сохраните роль пользователя и его СНИЛС при роли EMPLOYEE!</p>
            <label>Специализация рабочего: <input type="text" name="specialization" required></label>
        </div>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button id="saveButton1" type="submit" class="btn btn-dark" style="display: none;">Сохранить работника</button>
    </form>

    <form action="/admin/add/manufacturer" method="post">
        <div id="detailspecializationField" style="display: none;">
            <hr>
            <p>Сначала сохраните роль пользователя и его СНИЛС при роли EMPLOYEE!</p>
            <label>Специализация изготовителя деталей: <input type="text" name="detail_specialization" required></label>
        </div>
        <br>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button id="saveButton2" type="submit" class="btn btn-dark" style="display: none;">Сохранить изготовителя</button>
    </form>
    <script>
        window.addEventListener("load", toggleTextField);
        function toggleTextField() {
            var checkbox = document.querySelector('input[name="ROLE_EMPLOYEE"]');
            var textField = document.getElementById("snilsField");
            var saveButton = document.getElementById("saveButton");

            var checkbox1 = document.querySelector('input[name="ROLE_WORKER"]');
            var textField1 = document.getElementById("specializationField");
            var saveButton1 = document.getElementById("saveButton1");

            var checkbox2 = document.querySelector('input[name="ROLE_MANUFACTURER"]');
            var textField2 = document.getElementById("detailspecializationField");
            var saveButton2 = document.getElementById("saveButton2");

            if (checkbox.checked) {
                textField.style.display = "block";
                saveButton.style.display = "block";
            } else {
                textField.style.display = "none";
                saveButton.style.display = "none";
            }

            if (checkbox1.checked) {
                textField1.style.display = "block";
                saveButton1.style.display = "block";
            } else {
                textField1.style.display = "none";
                saveButton1.style.display = "none";
            }

            if (checkbox2.checked) {
                textField2.style.display = "block";
                saveButton2.style.display = "block";
            } else {
                textField2.style.display = "none";
                saveButton2.style.display = "none";
            }
        }
    </script>
</@t.template>