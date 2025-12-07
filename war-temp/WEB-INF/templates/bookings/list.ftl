<#import "/layout/base.ftl" as layout>
<@layout.page title="Мои брони">
    <h1>Мои бронирования</h1>

    <#if bookings?has_content>
        <ul class="bookings-list">
            <#list bookings as b>
                <li class="tour-card booking-item">
                    <h3>${b.tourName!''}</h3>
                    <p>Дата брони: ${b.booking_date}</p>
                    <p>Статус: ${b.status!''}</p>
                    <#if b.canCancel?? && b.canCancel == true>
                        <button onclick="adminPostConfirm('${request.contextPath}/api/bookings/cancel', { id: ${b.id} }, 'Бронирование отменено')">Отменить</button>
                    </#if>
                </li>
            </#list>
        </ul>
    <#else>
        <p>У вас пока нет бронирований.</p>
    </#if>
</@layout.page>
