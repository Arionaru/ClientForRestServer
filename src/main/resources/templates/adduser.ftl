<#import "common.ftl" as c>
<@c.page>
    <form method="post" action="/user/add">
        <div class="form-group col-sm-6">
            <label for="exampleFormControlInput1">Имя</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" name="firstName">
        </div>
        <div class="form-group col-sm-6">
            <label for="exampleFormControlInput1">Фамилия</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" name="lastName">
        </div>
        <div class="form-group col-sm-6">
            <label for="exampleFormControlTextarea1">Адрес</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="address"></textarea>
        </div>
        <div class="form-group col-sm-6">
            <label for="exampleFormControlTextarea1">Обо мне</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="aboutMe"></textarea>
        </div>
        <button class="btn btn-primary" type="submit">Сохранить</button>


    </form>
</@c.page>