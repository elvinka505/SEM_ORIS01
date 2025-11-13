<#assign title="Редактировать тур - Travel Planner">
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
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .container {
            background: white;
            padding: 50px;
            border-radius: 15px;
            box-shadow: 0 20px 60px rgba(255, 20, 147, 0.2);
            max-width: 600px;
            border: 3px solid #FFB6E1;
        }
        h1 {
            font-family: 'Fredoka One', cursive;
            color: #ff1493;
            margin-bottom: 30px;
            text-align: center;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            color: #555;
            margin-bottom: 8px;
            font-weight: 600;
        }
        input, textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #FFB6E1;
            border-radius: 8px;
            font-size: 1em;
            font-family: 'Quicksand', sans-serif;
        }
        input:focus, textarea:focus {
            outline: none;
            border-color: #ff1493;
        }
        button {
            width: 100%;
            padding: 12px;
            background: linear-gradient(135deg, #ff1493 0%, #ff69b4 100%);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 1.1em;
            font-weight: 600;
            cursor: pointer;
            margin-top: 20px;
        }
        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 20px rgba(255, 20, 147, 0.3);
        }
        a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #ff1493;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>✏️ Редактировать тур 💕</h1>
    <form action="/travelplanner/tours" method="POST">
        <div class="form-group">
            <label>Название:</label>
            <input type="text" name="name" required>
        </div>
        <div class="form-group">
            <label>Локация:</label>
            <input type="text" name="destination" required>
        </div>
        <div class="form-group">
            <label>Цена:</label>
            <input type="number" name="price" required>
        </div>
        <div class="form-group">
            <label>Описание:</label>
            <textarea name="description" rows="4"></textarea>
        </div>
        <button type="submit">Сохранить изменения 💗</button>
    </form>
    <a href="/travelplanner/tours">Назад к турам</a>
</div>
</body>
</html>
