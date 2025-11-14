<#assign title="Туры - Travel Planner">
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
            padding: 20px;
            background-attachment: fixed;
        }

        .header {
            text-align: center;
            margin-bottom: 40px;
            color: #ff1493;
            text-shadow: 2px 2px 4px rgba(255, 192, 203, 0.5);
        }

        .header h1 {
            font-family: 'Fredoka One', cursive;
            font-size: 3em;
            margin-bottom: 10px;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        .header p {
            font-size: 1.2em;
            color: #ff69b4;
            font-weight: bold;
        }

        .nav-buttons {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-bottom: 30px;
            flex-wrap: wrap;
        }

        .nav-buttons a {
            padding: 12px 25px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            text-decoration: none;
            border-radius: 50px;
            font-weight: bold;
            transition: all 0.3s;
            box-shadow: 0 5px 15px rgba(255, 20, 147, 0.3);
            border: 2px solid white;
        }

        .nav-buttons a:hover {
            transform: scale(1.1) translateY(-3px);
            box-shadow: 0 10px 25px rgba(255, 20, 147, 0.5);
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        .tours-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 25px;
            margin-bottom: 40px;
        }

        .tour-card {
            background: white;
            border-radius: 20px;
            padding: 25px;
            box-shadow: 0 15px 40px rgba(255, 20, 147, 0.15);
            transition: all 0.3s;
            border: 3px solid #FFB6E1;
            position: relative;
            overflow: hidden;
        }

        .tour-card::before {
            content: '';
            position: absolute;
            top: -50%;
            right: -50%;
            width: 200px;
            height: 200px;
            background: radial-gradient(circle, rgba(255, 192, 203, 0.3) 0%, transparent 70%);
            border-radius: 50%;
        }

        .tour-card:hover {
            transform: translateY(-15px);
            box-shadow: 0 25px 50px rgba(255, 20, 147, 0.3);
            border-color: #ff1493;
        }

        .tour-card h2 {
            color: #ff1493;
            font-family: 'Fredoka One', cursive;
            font-size: 1.8em;
            margin-bottom: 15px;
            text-transform: uppercase;
        }

        .tour-info {
            margin: 15px 0;
            font-size: 1.1em;
            color: #333;
        }

        .tour-info strong {
            color: #ff69b4;
        }

        .price {
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            padding: 10px 15px;
            border-radius: 15px;
            font-weight: bold;
            display: inline-block;
            margin: 15px 0;
            font-size: 1.2em;
        }

        .tour-description {
            color: #666;
            line-height: 1.6;
            margin: 15px 0;
            font-size: 1em;
        }

        .btn-book {
            width: 100%;
            padding: 15px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            border: none;
            border-radius: 15px;
            font-size: 1.1em;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s;
            margin-top: 15px;
            text-transform: uppercase;
            letter-spacing: 1px;
            box-shadow: 0 8px 15px rgba(255, 20, 147, 0.3);
        }

        .btn-book:hover {
            transform: scale(1.02);
            box-shadow: 0 12px 25px rgba(255, 20, 147, 0.5);
        }

        .btn-book:active {
            transform: scale(0.98);
        }

        .emoji {
            font-size: 1.5em;
            margin-right: 5px;
        }

        .no-tours {
            text-align: center;
            padding: 50px;
            background: white;
            border-radius: 20px;
            border: 3px dashed #ff1493;
            color: #ff69b4;
            font-size: 1.3em;
        }

        @media (max-width: 768px) {
            .header h1 {
                font-size: 2em;
            }

            .tours-grid {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>✨ 💕 Туры 💕 ✨</h1>
        <p>Выбери свой идеальный тур!</p>
    </div>

    <div class="nav-buttons">
        <a href="/travelplanner/">На главную</a>
        <a href="/travelplanner/users">Пользователи</a>
    </div>

    <#if tours?? && tours?size gt 0>
        <div class="tours-grid">
            <#list tours as tour>
                <div class="tour-card">
                    <h2><span class="emoji">✈️</span>${tour.name}</h2>

                    <div class="tour-info">
                        <strong>📍 Локация:</strong> ${tour.destination}
                    </div>

                    <#if tour.startDate??>
                        <div class="tour-info">
                            <strong>📅 Дата:</strong> ${tour.startDate?string("dd.MM.yyyy")}
                        </div>
                    </#if>

                    <div class="price">
                        💰 ${tour.price} руб.
                    </div>

                    <p class="tour-description">
                        ${tour.description!"Удивительный тур ждёт тебя! 🌟"}
                    </p>

                    <#if tour.availableSeats??>
                        <div class="tour-info">
                            <strong>💺 Мест:</strong> ${tour.availableSeats}
                        </div>
                    </#if>

                    <button class="btn-book">Забронировать сейчас! 💗</button>
                </div>
            </#list>
        </div>
    <#else>
        <div class="no-tours">
            <p>😭 Прости, туров нет, но скоро появятся! ✨</p>
        </div>
    </#if>
</div>
</body>
</html>
