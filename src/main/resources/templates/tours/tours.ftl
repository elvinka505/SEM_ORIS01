<#-- tours.ftl -->
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список туров | Travel Planner</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<header>
    <h1>Доступные туры</h1>
    <nav>
        <a href="/profile">Профиль</a> |
        <a href="/logout" onclick="logout(event)">Выход</a>
    </nav>
</header>

<main>
    <#if tours?size == 0>
        <p>Нет доступных туров.</p>
    <#else>
        <table class="tours-table">
            <thead>
            <tr>
                <th>Название</th>
                <th>Описание</th>
                <th>Цена</th>
                <th>Действие</th>
            </tr>
            </thead>
            <tbody>
            <#list tours as tour>
                <tr>
                    <td>${tour.name}</td>
                    <td>${tour.description}</td>
                    <td>${tour.price} ₽</td>
                    <td>
                        <form action="/tours/book" method="post">
                            <input type="hidden" name="csrf_token" value="${csrfToken?html}">
                            <input type="hidden" name="tourId" value="${tour.id}">
                            <button type="submit">Забронировать</button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</main>

<footer>
    <p>&copy; 2025 Travel Planner</p>
</footer>

<script>
    function logout(event) {
        event.preventDefault();
        fetch('/logout', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: 'csrf_token=${csrfToken?url}'
        }).then(() => window.location.href = '/login');
    }
</script>
</body>
</html>
