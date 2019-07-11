<#import "common.ftl" as c>
<@c.page>
    <div class="cursor-pointer btn">
    <table class="table table-hover cursor-pointer">
        <tr>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Адрес</th>
            <th>Обо мне</th>
        </tr>
        <#list users as user>

        <tr onclick="document.location = '/user/${user.id}';">
        <td>${user.firstName?if_exists}</td>
        <td>${user.lastName?if_exists}</td>
        <td>${user.address?if_exists}</td>
        <td style="white-space: normal;">${user.aboutMe?if_exists}</td>
        </tr>


        </#list>
    </table>
    </div>

</@c.page>

