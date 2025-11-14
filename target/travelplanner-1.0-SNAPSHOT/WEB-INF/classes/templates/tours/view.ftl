<#assign title="Просмотр тура - Travel Planner">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
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
            max-width: 800px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 20px 60px rgba(255, 20, 147, 0.2);
            border: 3px solid #FFB6E1;
        }
        h1 {
            font-family: 'Fredoka One', cursive;
            color: #ff1493;
            margin-bottom: 30px;
        }
        .tour-info {
            margin: 20px 0;
            padding: 15px;
            background: #FFF0F6;
            border-radius: 10px;
            border-left: 4px solid #ff1493;
        }
        .tour-info strong {
            color: #ff1493;
        }
        .price {
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            padding: 15px;
            border-radius: 10px;
            font-size: 1.5em;
            font-weight: bold;
            margin: 20px 0;
        }
        .buttons {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        a, button {
            flex: 1;
            padding: 12px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            border: none;
            border-radius: 8px;
            text-decoration: none;
            text-align: center;
            font-weight: bold;
            cursor: pointer;
        }
        a:hover, button:hover {
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
<div class="container">
    <h1>✈️ ${tour.name}</h1>
    <div class="tour-info">
        <strong>📍 Локация:</strong> ${tour.destination}
    </div>
    <div class="price">💰 ${tour.price} руб.</div>
    <div class="tour-info">
        <strong>📝 Описание:</strong> ${tour.description}
    </div>
    <div class="buttons">
        <a href="/travelplanner/tours">Назад</a>
    </div>
</div>
</body>
</html>
