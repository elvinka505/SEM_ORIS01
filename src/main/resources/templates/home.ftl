<#assign title="Travel Planner - Главная">
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

        .container {
            text-align: center;
            background: white;
            padding: 60px 40px;
            border-radius: 25px;
            box-shadow: 0 20px 60px rgba(255, 20, 147, 0.2);
            max-width: 600px;
            border: 3px solid #FFB6E1;
        }

        h1 {
            font-family: 'Fredoka One', cursive;
            font-size: 3em;
            color: #ff1493;
            margin-bottom: 20px;
            text-transform: uppercase;
            letter-spacing: 2px;
            text-shadow: 2px 2px 4px rgba(255, 20, 147, 0.2);
        }

        p {
            color: #666;
            font-size: 1.1em;
            margin-bottom: 40px;
            line-height: 1.8;
        }

        .buttons {
            display: flex;
            gap: 15px;
            justify-content: center;
            flex-wrap: wrap;
        }

        button, a {
            padding: 15px 30px;
            border: none;
            border-radius: 50px;
            font-size: 1em;
            font-weight: bold;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: all 0.3s;
            text-transform: uppercase;
            letter-spacing: 1px;
            box-shadow: 0 8px 15px rgba(255, 20, 147, 0.2);
        }

        button:hover, a:hover {
            transform: scale(1.1) translateY(-3px);
            box-shadow: 0 12px 25px rgba(255, 20, 147, 0.4);
        }

        .btn-primary {
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
        }

        .btn-secondary {
            background: linear-gradient(135deg, #ff69b4 0%, #ffb6d9 100%);
            color: white;
        }

        .btn-outline {
            border: 2px solid #ff1493;
            color: #ff1493;
            background: white;
        }

        .features {
            margin-top: 50px;
            text-align: left;
        }

        .feature-item {
            margin: 15px 0;
            padding: 15px;
            background: linear-gradient(135deg, rgba(255, 192, 203, 0.3) 0%, rgba(255, 182, 225, 0.2) 100%);
            border-left: 4px solid #ff1493;
            border-radius: 10px;
            color: #333;
        }

        .feature-item strong {
            color: #ff1493;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>✈️ Travel Planner 💕</h1>
    <p>Добро пожаловать в мир путешествий! Выбирай удивительные туры, бронируй места и исследуй новые горизонты с нами! ✨</p>

    <div class="buttons">
        <a href="/travelplanner/login" class="btn-primary">💗 Вход</a>
        <a href="/travelplanner/register" class="btn-secondary">✨ Регистрация</a>
        <a href="/travelplanner/tours" class="btn-outline">🌍 Все туры</a>
    </div>

    <div class="features">
        <div class="feature-item">
            <strong>🗺️ Огромный выбор</strong> - Туры по всему миру ждут тебя!
        </div>
        <div class="feature-item">
            <strong>💰 Лучшие цены</strong> - Доступные путешествия для всех!
        </div>
        <div class="feature-item">
            <strong>⭐ Отзывы</strong> - Читай отзывы других путешественников!
        </div>
        <div class="feature-item">
            <strong>🎁 Спецпредложения</strong> - Выигрывай призы и скидки!
        </div>
    </div>
</div>
</body>
</html>
