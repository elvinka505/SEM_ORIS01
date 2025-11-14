<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>🔐 Вход - Travel Planner</title>
    <link rel="stylesheet" href="/travelplanner/static/css/style.css">
</head>
<body style="display: flex; align-items: center; justify-content: center; min-height: 100vh; padding: 2rem;">

<div class="card" style="max-width: 450px; width: 100%;">
    <h1 style="text-align: center; margin-top: 0;">🔐 Вход в аккаунт</h1>

    <#if error??>
        <div class="alert alert-danger">${error}</div>
    </#if>

    <form action="/travelplanner/login" method="POST">
        <div class="form-group">
            <label for="email">📧 Email</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">🔑 Пароль</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit" class="btn" style="width: 100%;">Войти</button>
    </form>

    <p style="text-align: center; margin-top: 1.5rem; color: var(--text-gray);">
        Нет аккаунта? <a href="/travelplanner/register" style="color: var(--primary-pink); font-weight: 700; text-decoration: none;">Зарегистрируйтесь</a>
    </p>
</div>

<script src="/travelplanner/static/js/main.js"></script>
</body>
</html>