<#assign title="Редактировать пользователя - Travel Planner">
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
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background: white;
            padding: 50px;
            border-radius: 15px;
            box-shadow: 0 20px 60px rgba(255, 20, 147, 0.2);
            max-width: 500px;
            border: 3px solid #FFB6E1;
        }
        h1 { font-family: 'Fredoka One', cursive; color: #ff1493; margin-bottom: 30px; text-align: center; }
        .form-group { margin-bottom: 20px; }
        label { display: block; color: #555; margin-bottom: 8px; font-weight: 600; }
        input { width: 100%; padding: 12px; border: 2px solid #FFB6E1; border-radius: 8px; }
        input:focus { outline: none; border-color: #ff1493; }
        button { width: 100%; padding: 12px; background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%); color: white; border: none; border-radius: 8px; font-weight: 600; cursor: pointer; margin-top: 20px; }
        button:hover { transform: translateY(-2px); }
    </style>
</head>
<body>
<div class="container">
    <h1>✏️ Редактировать пользователя</h1>
    <form action="/travelplanner/users" method="POST">
        <div class="form-group">
            <label>Имя:</label>
            <input type="text" name="firstname" required>
        </div>
        <div class="form-group">
            <label>Фамилия:</label>
            <input type="text" name="lastname" required>
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" required>
        </div>
        <button type="submit">Сохранить 💗</button>
    </form>
</div>
</body>
</html>
