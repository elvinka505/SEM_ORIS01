<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>✨ Travel Planner - Путешествуй с нами</title>
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
    <h1>✨ Добро пожаловать в Travel Planner ✨</h1>

    <div class="card" style="text-align: center; margin: 3rem 0; padding: 3rem;">
        <h2 style="margin: 0 0 1rem 0;">🌍 Откройте мир путешествий</h2>
        <p style="font-size: 1.2rem; color: var(--text-gray); margin-bottom: 2rem;">
            Найдите идеальное путешествие, забронируйте свой рай и поделитесь впечатлениями
        </p>
        <a href="/travelplanner/tours" class="btn" style="font-size: 1.1rem;">🛫 Начать путешествие</a>
    </div>

    <h2 style="text-align: center; margin-top: 4rem;">🌟 Популярные направления</h2>

    <div class="tours-grid">
        <div class="card">
            <div style="font-size: 3rem; text-align: center;">🏝️</div>
            <h3>Мальдивы</h3>
            <p style="color: var(--text-gray); line-height: 1.6;">Кристально чистые воды и белоснежные пляжи. Рай на земле!</p>
        </div>

        <div class="card">
            <div style="font-size: 3rem; text-align: center;">🗼</div>
            <h3>Париж</h3>
            <p style="color: var(--text-gray); line-height: 1.6;">Город любви, искусства и моды. Очаровательно и романтично!</p>
        </div>

        <div class="card">
            <div style="font-size: 3rem; text-align: center;">🏔️</div>
            <h3>Альпы</h3>
            <p style="color: var(--text-gray); line-height: 1.6;">Горные вершины, свежий воздух и приключения. Энергия максимум!</p>
        </div>
    </div>

    <div class="card" style="background: linear-gradient(135deg, rgba(255,20,147,0.05) 0%, rgba(255,192,203,0.05) 100%); text-align: center; margin: 3rem 0; padding: 2rem;">
        <h2>✨ Почему выбирают нас?</h2>
        <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(150px, 1fr)); gap: 2rem; margin-top: 2rem;">
            <div>💎 Премиум качество</div>
            <div>💰 Лучшие цены</div>
            <div>🌟 24/7 Поддержка</div>
            <div>✈️ Безопасность</div>
        </div>
    </div>
</main>

<footer>
    <p>💖 Travel Planner 2025 - Путешествуй с нами! 💖</p>
</footer>

<script src="/travelplanner/static/js/main.js"></script>
</body>
</html>
```

---

## 📄 ФАЙЛ 4: tours/list.ftl

```ftl
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>🌍 Все туры - Travel Planner</title>
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
    <h1>🌍 Выбери свой идеальный тур</h1>

    <div class="filters">
        <input type="text" id="tour-search" placeholder="🔍 Поиск тура..." style="padding: 1rem; border: 2px solid #ffc0cb; border-radius: 12px; font-size: 1rem;">
    </div>

    <div class="tours-grid">
        <#list tours as tour>
            <div class="tour-card" data-price="${tour.price?c}">
                <div style="font-size: 2.5rem; margin-bottom: 1rem; text-align: center;">🌴</div>
                <h3 class="tour-name">${tour.name}</h3>
                <p class="tour-description">${tour.description!""}</p>

                <div style="margin: 1rem 0; padding: 1rem; background: #fff0f6; border-radius: 10px; border-left: 4px solid #ff1493;">
                    <strong style="color: #ff1493;">📍 Направление:</strong> ${tour.destination!"Не указано"}
                </div>

                <div class="tour-price">💰 ${tour.price?string("0.00")} ₽</div>

                <a href="/travelplanner/tours/${tour.id?c}" class="btn" style="width: 100%; text-align: center; margin-top: 1rem;">
                    ✨ ПОДРОБНЕЕ
                </a>
            </div>
        </#list>
    </div>

    <#if tours?size == 0>
        <div class="card" style="text-align: center; padding: 3rem;">
            <h2 style="margin-bottom: 1rem;">😔 Нет доступных туров</h2>
            <p>Попробуйте пересмотреть критерии поиска</p>
        </div>
    </#if>
</main>

<footer>
    <p>💖 Travel Planner 2025 - Путешествуй с нами!</p>
</footer>

<script src="/travelplanner/static/js/main.js"></script>
</body>
</html>