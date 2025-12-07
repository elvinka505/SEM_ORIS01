<#import "/layout/base.ftl" as layout>
<@layout.page title="Управление бронированиями">
    <h1>Бронирования</h1>

    <#if bookings?has_content>
        <table class="admin-table">
            <thead><tr><th>ID</th><th>Пользователь</th><th>Тур</th><th>Дата</th><th>Статус</th><th>Действия</th></tr></thead>
            <tbody>
            <#list bookings as b>
                <tr>
                    <td>${b.id}</td>
                    <td>${b.userEmail!''}</td>
                    <td>${b.tourName!''}</td>
                    <td>${b.booking_date}</td>
                    <td>${b.status!''}</td>
                    <td>
                        <form onsubmit="event.preventDefault(); adminPostConfirm('${request.contextPath}/api/bookings/update',{id:${b.id}, status:'confirmed'}, 'Подтверждено');">
                            <button type="submit">Подтвердить</button>
                        </form>
                        <button onclick="deleteItem('bookings', ${b.id})">Удалить</button>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <p>Бронирований нет.</p>
    </#if>
</@layout.page>
