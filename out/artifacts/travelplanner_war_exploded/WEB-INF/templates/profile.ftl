<#import "layout/base.ftl" as layout>

<@layout.page title="Мой профиль">

    <section class="bt-section">
        <h1 class="bt-section-title">Мой профиль</h1>
        <p class="bt-section-subtitle">
            Здесь живут твои данные и бронирования 💖
        </p>

        <div class="bt-card">

            <h3>@${user.username}</h3>
            <p>✉ ${user.email}</p>
            <p>Роль: ${user.role}</p>

            <h3>Что можно сделать</h3>
            <ul>
                <li><a href="${request.contextPath}/tours">Посмотреть туры ✈️</a></li>
                <li><a href="${request.contextPath}/">Вернуться на главную</a></li>
            </ul>
        </div>

        <div class="bt-section" style="margin-top: 40px;">

            <h2 class="bt-section-title">Мои бронирования</h2>

            <#if booking?? && booking == "ok">
                <p class="bt-success">Тур успешно забронирован! ✨</p>
            </#if>

            <#if bookings?has_content>
                <ul>
                    <#list bookings as b>
                        <li>
                            Тур ID: ${b.tourId} — статус: ${b.status}
                        </li>
                    </#list>
                </ul>
            <#else>
                <p>Ты ещё ничего не бронировал. Самое время исправить 💖</p>
                <a class="bt-btn" href="${request.contextPath}/tours">СМОТРЕТЬ ТУРЫ</a>
            </#if>

        </div>

    </section>

</@layout.page>
