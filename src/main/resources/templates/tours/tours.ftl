<#include "/layout.ftl">
<@page title="Туры" user=user>
    <header style="display:flex;align-items:center;justify-content:space-between;margin-bottom:12px;">
        <h1>Туры</h1>
        <form action="${ctx!'/tours/search'}" method="get">
            <input class="bp-input" type="text" name="q" placeholder="Город, направление..." style="width:280px; display:inline-block;">
        </form>
    </header>

    <div id="tours-container" class="bp-grid">
        <#if tours?has_content>
            <#list tours as tour>
                <div class="bp-card">
                    <div class="bp-card__media" style="background-image:url('${ctx!"/"}static/img/tours/${tour.image}');"></div>
                    <div class="bp-badge">♥ ${tour.likes?default(0)}</div>
                    <div class="bp-card__body">
                        <div class="bp-card__title">${tour.title}</div>
                        <div class="bp-card__meta"><span>${tour.location}</span><span>${tour.price} ₽</span></div>
                        <div style="margin-top:10px;">
                            <a class="bp-btn" href="${ctx!'/tours/' + tour.id}">Подробнее</a>
                            <button class="bp-btn ghost" onclick="toggleWishlist(${tour.id}, this)">${tour.inWishlist?then('В вишлисте','В вишлист')}</button>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <div class="bp-form">Туров не найдено</div>
        </#if>
    </div>

    <script>
        async function toggleWishlist(id, btn){
            const res = await bpFetch('${ctx!"/"}api/wishlist/toggle', { method: 'POST', body: JSON.stringify({ tourId: id }) });
            if(res.ok){
                const j = await res.json();
                btn.innerText = j.inWishlist ? 'В вишлисте' : 'В вишлист';
            } else alert('Ошибка');
        }
    </script>
</@page>
