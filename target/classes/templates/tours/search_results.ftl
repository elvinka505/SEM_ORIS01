<#assign title="Результаты поиска - Travel Planner">
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
            max-width: 1200px;
            margin: 0 auto;
        }
        h1 {
            font-family: 'Fredoka One', cursive;
            color: #ff1493;
            text-align: center;
            margin-bottom: 30px;
        }
        .tours-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 25px;
        }
        .tour-card {
            background: white;
            padding: 25px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(255, 20, 147, 0.15);
            border: 3px solid #FFB6E1;
        }
        .tour-card h2 {
            color: #ff1493;
            font-family: 'Fredoka One', cursive;
            margin-bottom: 15px;
        }
        .tour-card:hover {
            transform: translateY(-10px);
        }
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
    <h1>🔍 Результаты поиска 💕</h1>
    <#if tours?? && tours?size gt 0>
        <div class="tours-grid">
            <#list tours as tour>
                <div class="tour-card">
                    <h2>${tour.name}</h2>
                    <p>📍 ${tour.destination}</p>
                    <p>💰 ${tour.price} руб.</p>
                    <a href="/travelplanner/tours/${tour.id}">Подробнее</a>
                </div>
            </#list>
        </div>
    <#else>
        <p style="text-align: center; color: #ff1493; font-size: 1.2em;">Туры не найдены 😭</p>
    </#if>
</div>
</body>
</html>
