<#-- /WEB-INF/templates/layout/base.ftl -->
<#macro page title="">
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>${title?if_exists?html}</title>
        <link rel="stylesheet" href="${request.contextPath}/static/css/style.css">
    </head>
    <body>
    <header class="site-header">
        <nav class="nav">
            <a class="logo" href="${request.contextPath}/">TravelPlanner</a>
            <div class="nav-links">
                <a href="${request.contextPath}/tours">Туры</a>
                <a href="${request.contextPath}/schedules">Расписания</a>
                <a href="${request.contextPath}/bookings">Мои брони</a>
                <#if user??>
                    <a href="${request.contextPath}/profile">Профиль</a>
                    <a href="${request.contextPath}/logout" onclick="logout(event)">Выйти</a>
                <#else>
                    <a href="${request.contextPath}/login">Вход</a>
                    <a href="${request.contextPath}/register">Регистрация</a>
                </#if>
                <a href="${request.contextPath}/csv/import">Импорт CSV</a>
            </div>
            <div class="nav-cart">
                <a href="${request.contextPath}/cart">Корзина <span id="cartCount">${cartCount!?0}</span></a>
            </div>
        </nav>
    </header>

    <main class="container">
        <#nested/>
    </main>

    <footer class="site-footer">
        <div>© ${.now?date?string["yyyy"]} TravelPlanner</div>
    </footer>

    <script src="${request.contextPath}/static/js/main.js"></script>
    </body>
    </html>
</#macro>
