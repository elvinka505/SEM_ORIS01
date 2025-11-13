<#assign title="Вход - Travel Planner">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Quicksand', sans-serif;
            background: linear-gradient(135deg, #FFB6E1 0%, #FFD6E8 50%, #FFF0F6 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .login-container {
            background: white;
            padding: 50px;
            border-radius: 15px;
            box-shadow: 0 20px 60px rgba(255, 20, 147, 0.3);
            width: 100%;
            max-width: 420px;
            border: 3px solid #FFB6E1;
        }

        .login-container h1 {
            color: #ff1493;
            margin-bottom: 30px;
            text-align: center;
            font-size: 2.5em;
            font-family: 'Fredoka One', cursive;
            text-transform: uppercase;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            color: #ff1493;
            margin-bottom: 8px;
            font-weight: 600;
            font-size: 1.1em;
        }

        .form-group input {
            width: 100%;
            padding: 14px;
            border: 2px solid #FFB6E1;
            border-radius: 10px;
            font-size: 1em;
            transition: all 0.3s;
            font-family: 'Quicksand', sans-serif;
        }

        .form-group input:focus {
            outline: none;
            border-color: #ff1493;
            box-shadow: 0 0 10px rgba(255, 20, 147, 0.2);
        }

        button {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 1.1em;
            font-weight: 600;
            cursor: pointer;
            margin-top: 20px;
            transition: all 0.3s;
            text-transform: uppercase;
            letter-spacing: 1px;
            box-shadow: 0 8px 15px rgba(255, 20, 147, 0.3);
        }

        button:hover {
            transform: translateY(-3px);
            box-shadow: 0 12px 25px rgba(255, 20, 147, 0.5);
        }

        button:active {
            transform: translateY(-1px);
        }

        .links {
            text-align: center;
            margin-top: 25px;
            display: flex;
            gap: 15px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .links a {
            color: #ff1493;
            text-decoration: none;
            margin: 0;
            font-weight: 600;
            transition: all 0.3s;
        }

        .links a:hover {
            text-decoration: underline;
            color: #ff69b4;
        }

        .error {
            background: linear-gradient(135deg, rgba(255, 192, 203, 0.5) 0%, rgba(255, 182, 225, 0.3) 100%);
            color: #ff1493;
            padding: 14px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
            border: 2px solid #FFB6E1;
            font-weight: 600;
        }

        .success {
            background: linear-gradient(135deg, rgba(144, 238, 144, 0.4) 0%, rgba(152, 251, 152, 0.3) 100%);
            color: #28a745;
            padding: 14px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
            border: 2px solid #90EE90;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h1>✈️ Вход 💕</h1>

    <#if error??>
        <div class="error">
            ❌ Ошибка входа. Проверьте email и пароль
        </div>
    </#if>

    <#if success??>
        <div class="success">
            ✅ Добро пожаловать!
        </div>
    </#if>

    <form action="/travelplanner/login" method="POST">
        <div class="form-group">
            <label for="email">📧 Email:</label>
            <input type="email" id="email" name="email" placeholder="your@email.com" required>
        </div>

        <div class="form-group">
            <label for="password">🔐 Пароль:</label>
            <input type="password" id="password" name="password" placeholder="••••••••" required>
        </div>

        <button type="submit">💗 Войти</button>
    </form>

    <div class="links">
        <a href="/travelplanner/">🏠 На главную</a>
        <span style="color: #FFB6E1;">•</span>
        <a href="/travelplanner/register">✨ Регистрация</a>
    </div>
</div>
</body>
</html>
