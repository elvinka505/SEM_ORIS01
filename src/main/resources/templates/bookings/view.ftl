<#assign title="Бронирование - Travel Planner">
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
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 20px 60px rgba(255, 20, 147, 0.2);
            max-width: 600px;
            border: 3px solid #FFB6E1;
        }
        h1 { font-family: 'Fredoka One', cursive; color: #ff1493; margin-bottom: 30px; text-align: center; }
        .info { margin: 20px 0; padding: 15px; background: #FFF0F6; border-radius: 10px; border-left: 4px solid #ff1493; }
        .info strong { color: #ff1493; }
        a {
            display: inline-block;
            padding: 12px 25px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            margin-top: 20px;
        }
        a:hover { transform: translateY(-2px); }
    </style>
</head>
<body>
<div class="container">
    <h1>📅 Бронирование #${booking.id}</h1>
    <div class="info">
        <strong>👤 Пользователь:</strong> ${booking.userName}
    </div>
    <div class="info">
        <strong>✈️ Тур:</strong> ${booking.tourName}
    </div>
    <div class="info">
        <strong>💰 Цена:</strong> ${booking.tourPrice} руб.
    </div>
    <div class="info">
        <strong>📅 Дата бронирования:</strong> ${booking.bookingDate?string("dd.MM.yyyy")}
    </div>
    <a href="/travelplanner/bookings">Назад к бронированиям</a>
</div>
</body>
</html>
