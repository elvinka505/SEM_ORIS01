<#include "/layout.ftl">
<@page title="Вход">
    <div style="max-width:520px;margin:20px auto;">
        <div class="bp-form">
            <h2>Вход</h2>
            <form action="${ctx!'/'}login" method="post">
                <input class="bp-input" name="login" placeholder="Логин" required>
                <input class="bp-input" name="password" type="password" placeholder="Пароль" required>
                <div style="display:flex;gap:8px;">
                    <button class="bp-btn" type="submit">Войти</button>
                    <a class="bp-btn ghost" href="${ctx!'/'}register">Регистрация</a>
                </div>
            </form>
        </div>
    </div>
</@page>
