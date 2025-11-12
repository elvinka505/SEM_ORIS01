<#assign title="Просмотр пользователя - Travel Planner">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Quicksand', sans-serif;
            background: linear-gradient(135deg, #FFB6E1 0%, #FFD6E8 50%, #FFF0F6 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 20px 60px rgba(255, 20, 147, 0.2);
            border: 3px solid #FFB6E1;
        }
        h1 { font-family: 'Fredoka One', cursive; color: #ff1493; margin-bottom: 30px; }
        .info { margin: 20px 0; padding: 15px; background: #FFF0F6; border-radius: 10px; border-left: 4px solid #ff1493; }
        .info strong { color: #ff1493; }
        a {
            display: inline-block;
            padding: 10px 20px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>👤 ${user.firstName} ${user.lastName}</h1>
    <div class="info">
        <strong>📧 Email:</strong> ${user.email}
    </div>
    <div class="info">
        <strong>👑 Роль:</strong> ${user.role}
    </div>
    <a href="/travelplanner/users">Назад к пользователям</a>
</div>
</body>
</html>
