<#assign title="Отзывы - Travel Planner">
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
        .nav { display: flex; justify-content: center; gap: 15px; margin-bottom: 30px; }
        .nav a {
            padding: 12px 25px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            text-decoration: none;
            border-radius: 50px;
        }
        .reviews-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 25px;
        }
        .review-card {
            background: white;
            padding: 25px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(255, 20, 147, 0.15);
            border: 3px solid #FFB6E1;
        }
        .review-card h3 { color: #ff1493; font-family: 'Fredoka One', cursive; margin-bottom: 15px; }
        .rating { color: #FFD700; font-size: 1.3em; margin: 10px 0; }
        .review-text { color: #666; line-height: 1.6; margin: 15px 0; }
    </style>
</head>
<body>
<div class="container">
    <h1>⭐ Отзывы 💕</h1>
    <div class="nav">
        <a href="/travelplanner/">На главную</a>
        <a href="/travelplanner/tours">Туры</a>
    </div>
    <#if reviews?? && reviews?size gt 0>
        <div class="reviews-grid">
            <#list reviews as review>
                <div class="review-card">
                    <h3>${review.tourName}</h3>
                    <p style="color: #ff1493;"><strong>👤 ${review.userName}</strong></p>
                    <div class="rating">${"⭐"?repeat(review.rating)}</div>
                    <div class="review-text">${review.text}</div>
                </div>
            </#list>
        </div>
    <#else>
        <p style="text-align: center; color: #ff1493; font-size: 1.2em;">Отзывов нет 😭</p>
    </#if>
</div>
</body>
</html>
