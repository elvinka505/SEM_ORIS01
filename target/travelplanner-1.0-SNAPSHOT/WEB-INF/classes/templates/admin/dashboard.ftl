<#assign title="Админ-панель - Travel Planner">
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
            padding: 30px 20px;
        }
        .container { max-width: 1200px; margin: 0 auto; }
        h1 { font-family: 'Fredoka One', cursive; color: #ff1493; text-align: center; margin-bottom: 40px; font-size: 3em; }
        .dashboard-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 25px;
            margin-bottom: 40px;
        }
        .dashboard-card {
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(255, 20, 147, 0.15);
            border: 3px solid #FFB6E1;
            text-align: center;
        }
        .dashboard-card h2 { color: #ff1493; margin-bottom: 15px; }
        .dashboard-card .number { font-size: 2.5em; color: #ff1493; font-weight: bold; }
        .dashboard-card a {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 20px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>👑 Админ-панель 💕</h1>
    <div class="dashboard-grid">
        <div class="dashboard-card">
            <h2>👥 Пользователи</h2>
            <div class="number">${usersCount!0}</div>
            <a href="/travelplanner/users">Управлять</a>
        </div>
        <div class="dashboard-card">
            <h2>✈️ Туры</h2>
            <div class="number">${toursCount!0}</div>
            <a href="/travelplanner/tours">Управлять</a>
        </div>
        <div class="dashboard-card">
            <h2>📅 Бронирования</h2>
            <div class="number">${bookingsCount!0}</div>
            <a href="/travelplanner/bookings">Просмотреть</a>
        </div>
        <div class="dashboard-card">
            <h2>⭐ Отзывы</h2>
            <div class="number">${reviewsCount!0}</div>
            <a href="/travelplanner/reviews">Просмотреть</a>
        </div>
    </div>
    <div style="text-align: center; margin-top: 40px;">
        <a href="/travelplanner/" style="padding: 12px 30px; background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%); color: white; text-decoration: none; border-radius: 8px;">На главную</a>
    </div>
</div>
</body>
</html>
