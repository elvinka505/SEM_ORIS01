<#-- layout.ftl -->
<#macro page title="" user=??>
<!doctype html>
<html lang="ru">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>${title?if_exists?html}</title>

  <link rel="stylesheet" href="${ctx!'/'}static/css/blackpink.css">
  <link rel="stylesheet" href="${ctx!'/'}static/css/style.css">
  <script>
    // ctx helper in case not provided
    window._CTX = '${ctx!"/"}';
  </script>
</head>
<body class="bp-body">
  <header class="bp-header">
    <div class="bp-header-inner container">
      <a class="bp-brand" href="${ctx!'/'}">PinkTravel ✦</a>
      <nav class="bp-nav">
        <a class="bp-nav-link" href="${ctx!'/'}">Главная</a>
        <a class="bp-nav-link" href="${ctx!'/tours'}">Туры</a>
        <a class="bp-nav-link" href="${ctx!'/schedules'}">Расписание</a>
        <a class="bp-nav-link" href="${ctx!'/reviews'}">Отзывы</a>
      </nav>
      <div class="bp-actions">
        <#if user??>
          <a class="bp-btn ghost" href="${ctx!'/profile'}">${user.name?html}</a>
          <a class="bp-btn" href="${ctx!'/logout'}">Выйти</a>
        <#else>
          <a class="bp-btn ghost" href="${ctx!'/login'}">Вход</a>
          <a class="bp-btn" href="${ctx!'/register'}">Регистрация</a>
        </#if>
      </div>
    </div>
  </header>

  <main class="bp-main container">
    <#nested/>
  </main>

  <footer class="bp-footer">
    <div class="container">
      <div>© ${.now?string['yyyy']} PinkTravel — aesthetic trips</div>
      <div class="bp-footer-right">Made with ♥</div>
    </div>
  </footer>

  <script>
    async function bpFetch(url, opts = {}) {
      opts.headers = opts.headers || {};
      if (!opts.headers['Content-Type']) opts.headers['Content-Type'] = 'application/json';
      const res = await fetch(url, opts);
      return res;
    }
  </script>
</body>
</html>
</#macro>
