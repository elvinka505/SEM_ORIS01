<#assign title="Отзыв - Travel Planner">
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
        h1 { font-family: 'Fredoka One', cursive; color: #ff1493; margin-bottom: 10px; }
        .rating { color: #FFD700; font-size: 1.5em; margin: 15px 0; }
        .info { margin: 20px 0; padding: 15px; background: #FFF0F6; border-radius: 10px; border-left: 4px solid #ff1493; }
        .info strong { color: #ff1493; }
        .review-text { margin: 20px 0; padding: 15px; background: #FFF0F6; border-radius: 10px; line-height: 1.8; }
        a {
            display: inline-block;
            padding: 12px 25px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>⭐ ${review.tourName}</h1>
    <div class="rating">${"⭐"?repeat(review.rating)}</div>
    <div class="info">
        <strong>👤 Пользователь:</strong> ${review.userName}
    </div>
    <div class="review-text">
        ${review.text}
    </div>
    <a href="/travelplanner/reviews">Назад к отзывам</a>
</div>
</body>
</html>
