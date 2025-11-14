<#include "/layout.ftl">
<@page title="Главная" user=user>
    <section style="margin-bottom:18px;">
        <h1 class="fade-up">Путешествуй стильно — PinkTravel</h1>
        <p class="fade-up" style="margin-top:8px; color:var(--muted)">Подбор лучших туров в Pink-Glam эстетике</p>
    </section>

    <section>
        <h2>Популярные туры</h2>
        <div class="bp-grid">
            <#list popularTours?default([]) as tour>
                <div class="bp-card fade-up">
                    <div class="bp-card__media" style="background-image:url('${ctx!"/"}static/img/tours/${tour.image}');"></div>
                    <div class="bp-badge">♥ ${tour.likes?default(0)}</div>
                    <div class="bp-card__body">
                        <div class="bp-card__title">${tour.title}</div>
                        <div class="bp-card__meta"><span>${tour.location}</span><span>${tour.price} ₽</span></div>
                        <div style="margin-top:10px;">
                            <a class="bp-btn" href="${ctx!'/tours/' + tour.id}">Подробнее</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </section>
</@page>
