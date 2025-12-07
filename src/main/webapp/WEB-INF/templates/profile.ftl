<#import "layout/base.ftl" as layout>

<@layout.page title="Мой профиль">

    <section class="profile-section">
        <div class="profile-header">
            <div class="profile-avatar">
                <span class="profile-avatar-initial">
                    ${(user.username!'?')?substring(0, 1)?upper_case}
                </span>
            </div>
            <div class="profile-info">
                <h1 class="page-title">Мой профиль</h1>
                <p class="profile-tagline">
                    Здесь живут твои данные и бронирования 💖
                </p>
            </div>
        </div>

        <div class="profile-grid">
            <div class="profile-card">
                <h2 class="profile-name">
                    @${user.username!'noname'}
                </h2>
                <p class="profile-email">
                    ✉ ${user.email!'no-email@example.com'}
                </p>
                <p class="profile-role">
                    Роль:
                    <span class="badge badge-secondary">
                        ${user.role!"USER"}
                    </span>
                </p>
            </div>

            <div class="profile-card profile-actions-card">
                <h3>Что можно сделать</h3>
                <ul class="profile-actions-list">
                    <li>
                        <a href="${request.contextPath}/tours" class="bt-link">
                            Посмотреть туры и выбрать новый ✈️
                        </a>
                    </li>
                    <li>
                        <a href="${request.contextPath}/" class="bt-link">
                            Вернуться на главную
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="profile-bookings">
            <div class="profile-bookings-header">
                <h2>Мои бронирования</h2>

                <#if bookings?has_content>
                    <span class="badge badge-light">
                        Всего: ${bookings?size}
                    </span>
                </#if>
            </div>

            <#if success??>
                <div class="bt-alert bt-alert-success">
                    ${success?html}
                </div>
            </#if>

            <#if bookings?has_content>
                <table class="table bookings-table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Тур</th>
                        <th>Создано</th>
                        <th>Статус</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list bookings as b>
                        <tr>
                            <td>${b?index + 1}</td>
                            <td>Тур #${b.tourId}</td>
                            <td>
                                <#if b.createdAt??>
                                    ${b.createdAt?string("dd.MM.yyyy HH:mm")}
                                <#else>
                                    —
                                </#if>
                            </td>
                            <td>
                                <span class="badge badge-primary">
                                    ${b.status!"NEW"}
                                </span>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            <#else>
                <div class="profile-empty">
                    <p>Ты ещё ничего не бронировала. Самое время исправить 💖</p>
                    <a href="${request.contextPath}/tours" class="bt-btn bt-btn-primary">
                        Смотреть туры
                    </a>
                </div>
            </#if>
        </div>
    </section>

</@layout.page>
