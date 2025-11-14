<#-- profile.ftl -->
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль пользователя | Travel Planner</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<header>
    <h1>Ваш профиль</h1>
</header>

<main>
    <#if message??>
        <div class="alert alert-success">${message}</div>
    </#if>
    <#if error??>
        <div class="alert alert-danger">${error}</div>
    </#if>

    <form action="/profile/update" method="post">
        <input type="hidden" name="csrf_token" value="${csrfToken?html}">

        <div class="form-group">
            <label for="firstName">Имя:</label>
            <input id="firstName" name="firstName" type="text" value="${user.firstName!''}" required>
        </div>

        <div class="form-group">
            <label for="lastName">Фамилия:</label>
            <input id="lastName" name="lastName" type="text" value="${user.lastName!''}" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input id="email" name="email" type="email" value="${user.email!''}" readonly>
        </div>

        <div class="form-group">
            <label for="password">Новый пароль:</label>
            <input id="password" name="password" type="password" placeholder="Оставьте пустым, если не меняете">
        </div>

        <div class="form-group">
            <button type="submit">Сохранить изменения</button>
        </div>
    </form>

    <hr>

    <form action="/logout" method="post">
        <input type="hidden" name="csrf_token" value="${csrfToken?html}">
        <button type="submit">Выйти</button>
    </form>
</main>

<footer>
    <p>&copy; 2025 Travel Planner</p>
</footer>
</body>
</html>
