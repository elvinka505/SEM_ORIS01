<#assign title="Пользователи - Travel Planner">
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
            padding: 30px 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        h1 {
            font-family: 'Fredoka One', cursive;
            color: #ff1493;
            text-align: center;
            margin-bottom: 30px;
            font-size: 2.5em;
            text-transform: uppercase;
        }

        .nav {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-bottom: 30px;
            flex-wrap: wrap;
        }

        .nav a {
            padding: 12px 25px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            text-decoration: none;
            border-radius: 50px;
            font-weight: bold;
            transition: all 0.3s;
            box-shadow: 0 5px 15px rgba(255, 20, 147, 0.2);
        }

        .nav a:hover {
            transform: translateY(-3px);
            box-shadow: 0 10px 25px rgba(255, 20, 147, 0.4);
        }

        table {
            width: 100%;
            background: white;
            border-collapse: collapse;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 15px 40px rgba(255, 20, 147, 0.15);
            border: 3px solid #FFB6E1;
        }

        th {
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            padding: 20px;
            text-align: left;
            font-weight: bold;
            font-size: 1.1em;
            text-transform: uppercase;
        }

        td {
            padding: 15px 20px;
            border-bottom: 2px solid #FFD6E8;
            color: #333;
        }

        tr:hover {
            background: #FFF0F6;
        }

        .role-user {
            background: #FFE4F0;
            color: #ff1493;
            padding: 5px 10px;
            border-radius: 10px;
            font-weight: bold;
        }

        .role-admin {
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            padding: 5px 10px;
            border-radius: 10px;
            font-weight: bold;
        }

        .no-users {
            text-align: center;
            padding: 50px;
            background: white;
            border-radius: 15px;
            border: 3px dashed #ff1493;
            color: #ff69b4;
            font-size: 1.3em;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>👯‍♀️ Наша команда 💕</h1>

    <div class="nav">
        <a href="/travelplanner/">На главную</a>
        <a href="/travelplanner/tours">Туры</a>
    </div>

    <#if users?? && users?size gt 0>
        <table>
            <thead>
            <tr>
                <th>👤 ID</th>
                <th>📛 Имя</th>
                <th>📧 Email</th>
                <th>👑 Роль</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>#${user.id}</td>
                    <td>${user.firstName} ${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>
                        <#if user.role == "ADMIN">
                            <span class="role-admin">👑 АДМИН</span>
                        <#else>
                            <span class="role-user">✨ ПОЛЬЗОВАТЕЛЬ</span>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <div class="no-users">
            <p>😭 Пока что нет пользователей, но ты можешь быть первой! ✨</p>
        </div>
    </#if>
</div>
</body>
</html>
