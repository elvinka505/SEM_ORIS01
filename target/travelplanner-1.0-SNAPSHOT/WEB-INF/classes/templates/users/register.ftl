<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>📝 Регистрация - Travel Planner</title>
    <link rel="stylesheet" href="/travelplanner/static/css/style.css">
</head>
<body style="display: flex; align-items: center; justify-content: center; min-height: 100vh; padding: 2rem;">

<div class="card" style="max-width: 450px; width: 100%;">
    <h1 style="text-align: center; margin-top: 0;">📝 Регистрация</h1>

    <#if error??>
        <div class="alert alert-danger">${error}</div>
    </#if>

    <form action="/travelplanner/register" method="POST">
        <div class="form-group">
            <label for="firstName">👤 Имя</label>
            <input type="text" id="firstName" name="firstName" required>
        </div>

        <div class="form-group">
            <label for="lastName">👤 Фамилия</label>
            <input type="text" id="lastName" name="lastName" required>
        </div>

        <div class="form-group">
            <label for="email">📧 Email</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">🔑 Пароль (минимум 6 символов)</label>
            <input type="password" id="password" name="password" minlength="6" required>
        </div>

        <button type="submit" class="btn" style="width: 100%;">Создать аккаунт</button>
    </form>

    <p style="text-align: center; margin-top: 1.5rem; color: var(--text-gray);">
        Уже есть аккаунт? <a href="/travelplanner/login" style="color: var(--primary-pink); font-weight: 700; text-decoration: none;">Войти</a>
    </p>
</div>

<script src="/travelplanner/static/js/main.js"></script>
</body>
</html>