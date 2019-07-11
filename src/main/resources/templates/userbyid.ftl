<#import "common.ftl" as c>
<@c.page>
    <form method="post" action="/user/save">
        <div class="form-group col-sm-6">
            <label for="exampleFormControlInput1">Имя</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" name="firstName" value="${user.firstName?if_exists}">
        </div>
        <div class="form-group col-sm-6">
            <label for="exampleFormControlInput1">Фамилия</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" name="lastName" value="${user.lastName?if_exists}">
        </div>
        <div class="form-group col-sm-6">
            <label for="exampleFormControlTextarea1">Адрес</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="address">${user.address?if_exists}</textarea>
        </div>
        <div class="form-group col-sm-6">
            <label for="exampleFormControlTextarea1">Обо мне</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="aboutMe">${user.aboutMe?if_exists}</textarea>
        </div>
        <input type="hidden" value="${user.id}" name="userId">
        <button class="btn btn-primary" type="submit">Сохранить</button>

    </form>
    <form method="post" action="/user/delete">
    <div class="form-group mt-3">
        <button class="btn btn-secondary" type="submit">Удалить</button>
        <input type="hidden" value="${user.id}" name="userId">
    </div>
    </form>

</@c.page>