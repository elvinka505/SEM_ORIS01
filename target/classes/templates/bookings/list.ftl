<#assign title="Бронирования - Travel Planner">
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
        h1 { font-family: 'Fredoka One', cursive; color: #ff1493; text-align: center; margin-bottom: 30px; font-size: 2.5em; }
        .nav { display: flex; justify-content: center; gap: 15px; margin-bottom: 30px; flex-wrap: wrap; }
        .nav a {
            padding: 12px 25px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            text-decoration: none;
            border-radius: 50px;
            font-weight: bold;
            transition: all 0.3s;
        }
        .nav a:hover { transform: translateY(-3px); }
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
        }
        td {
            padding: 15px 20px;
            border-bottom: 2px solid #FFD6E8;
            color: #333;
        }
        tr:hover { background: #FFF0F6; }
    </style>
</head>
<body>
<div class="container">
    <h1>📅 Бронирования 💕</h1>
    <div class="nav">
        <a href="/travelplanner/">На главную</a>
        <a href="/travelplanner/tours">Туры</a>
    </div>
    <#if bookings?? && bookings?size gt 0>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>👤 Пользователь</th>
                <th>✈️ Тур</th>
                <th>📅 Дата</th>
                <th>👁️ Просмотр</th>
            </tr>
            </thead>
            <tbody>
            <#list bookings as booking>
                <tr>
                    <td>#${booking.id}</td>
                    <td>${booking.userName}</td>
                    <td>${booking.tourName}</td>
                    <td>${booking.bookingDate?string("dd.MM.yyyy")}</td>
                    <td><a href="/travelplanner/bookings/${booking.id}" style="color: #ff1493;">Подробнее</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <p style="text-align: center; color: #ff1493; font-size: 1.2em;">Бронирований нет 😭</p>
    </#if>
</div>
</body>
</html>
