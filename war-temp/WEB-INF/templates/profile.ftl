<#import "/layout/base.ftl" as layout>
<@layout.page title="Профиль">
    <h1>Профиль</h1>

    <p>Имя: ${user.first_name!''} ${user.last_name!''}</p>
    <p>Email: ${user.email!''}</p>
    <p>Роль: ${user.role!''}</p>

    <h2>Мои брони</h2>
    <#if bookings?has_content>
        <ul>
            <#list bookings as b>
                <li>${b.tourName!''} — ${b.booking_date} — ${b.status!''}</li>
            </#list>
        </ul>
    <#else>
        <p>Пока нет бронирований.</p>
    </#if>
</@layout.page>
