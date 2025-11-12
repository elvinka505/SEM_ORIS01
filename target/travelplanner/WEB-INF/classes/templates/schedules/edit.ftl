<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактировать расписание</title>
</head>
<body>
<h1>Редактировать расписание</h1>
<form action="schedules?action=edit" method="post">
    <input type="hidden" name="id" value="${schedule.id}">
    ID Тура: <input type="number" name="tourId" value="${schedule.tourId}" required><br>
    Дата: <input type="date" name="date" value="${schedule.date}" required><br>
    Свободных мест: <input type="number" name="slots" value="${schedule.slots}" required><br>
    <input type="submit" value="Сохранить">
</form>
<br>
<a href="schedules">Назад к списку</a>
</body>
</html>
