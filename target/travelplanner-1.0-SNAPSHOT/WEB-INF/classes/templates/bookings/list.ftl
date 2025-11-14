<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>💼 Бронирования - Travel Planner</title>
    <link rel="stylesheet" href="/travelplanner/static/css/style.css">
</head>
<body>

<header>
    <nav class="container">
        <div class="logo">💅 Travel Planner</div>
        <ul>
            <li><a href="/travelplanner/">🏠 Главная</a></li>
            <li><a href="/travelplanner/tours">🌴 Туры</a></li>
            <li><a href="/travelplanner/bookings">💼 Бронирования</a></li>
            <li><a href="/travelplanner/profile">👑 Профиль</a></li>
            <li><a href="#" onclick="logout(event)">👋 Выход</a></li>
        </ul>
    </nav>
</header>

<main class="container">
    <h1>💼 Мои бронирования</h1>

    <#if bookings?? && bookings?size gt 0>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Тур</th>
                <th>Дата бронирования</th>
                <th>Статус</th>
                <th>Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list bookings as booking>
                <tr>
                    <td>${booking.id?c}</td>
                    <td>Тур #${booking.tourId?c}</td>
                    <td>${booking.bookingDate?string("dd.MM.yyyy")}</td>
                    <td style="color: var(--primary-pink); font-weight: 600;">${booking.status}</td>
                    <td>
                        <button onclick="deleteItem('bookings', ${booking.id?c})" class="btn" style="padding: 0.5rem 1rem; font-size: 0.9rem;">
                            🗑️ Отменить
                        </button>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <div class="card" style="text-align: center; padding: 3rem;">
            <h2>😔 У вас нет бронирований</h2>
            <p>Выберите интересующий вас тур и забронируйте путешествие!</p>
            <a href="/travelplanner/tours" class="btn" style="margin-top: 2rem;">🌴 Выбрать тур</a>
        </div>
    </#if>
</main>

<footer>
    <p>💖 Travel Planner 2025 - Путешествуй с нами!</p>
</footer>

<script src="/travelplanner/static/js/main.js"></script>
</body>
</html>