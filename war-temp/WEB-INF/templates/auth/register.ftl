<#import "/layout/base.ftl" as layout>
<@layout.page title="Регистрация">
    <h1>Регистрация</h1>

    <div style="color:red">${errormessage!''}</div>

    <form id="registerForm" method="post" action="${request.contextPath}/register" onsubmit="return validateForm('registerForm');">
        <input type="hidden" name="csrfToken" value="${csrfToken!''}">
        <div class="form-row"><label>Имя <input name="first_name" type="text" required></label></div>
        <div class="form-row"><label>Фамилия <input name="last_name" type="text" required></label></div>
        <div class="form-row"><label>Email <input name="email" type="email" required></label></div>
        <div class="form-row"><label>Пароль <input name="password" type="password" required minlength="6"></label></div>
        <button type="submit">Зарегистрироваться</button>
    </form>
</@layout.page>
