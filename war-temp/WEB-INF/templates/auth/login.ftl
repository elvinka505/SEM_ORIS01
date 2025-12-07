<#import "/layout/base.ftl" as layout>
<@layout.page title="Вход">
    <h1>Вход</h1>

    <div style="color:red">${errormessage!''}</div>

    <form method="post" action="${request.contextPath}/login">
        <input type="hidden" name="csrfToken" value="${csrfToken!''}">
        <div class="form-row"><label>Email/login <input name="login" type="text" required></label></div>
        <div class="form-row"><label>Пароль <input name="password" type="password" required></label></div>
        <button type="submit">Войти</button>
    </form>
    <p>Нет аккаунта? <a href="${request.contextPath}/register">Регистрация</a></p>
</@layout.page>
