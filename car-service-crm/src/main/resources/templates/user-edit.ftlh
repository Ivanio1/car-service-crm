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
        <button id="saveButton" type="submit" class="btn btn-dark" style="display: none;">Сохранить работника</button>
        <br>
    </form>
    <script>
        window.addEventListener("load", toggleTextField);
        function toggleTextField() {
            var checkbox = document.querySelector('input[name="ROLE_EMPLOYEE"]');
            var textField = document.getElementById("snilsField");
            var saveButton = document.getElementById("saveButton");

            if (checkbox.checked) {
                textField.style.display = "block";
                saveButton.style.display = "block";
            } else {
                textField.style.display = "none";
                saveButton.style.display = "none";
            }
        }
    </script>
</@t.template>