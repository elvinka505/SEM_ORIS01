<#import "/layout/base.ftl" as layout>
<@layout.page title="Панель администратора">
    <h1>Админка</h1>

    <nav class="admin-nav">
        <a href="${request.contextPath}/admin/tours">Туры</a>
        <a href="${request.contextPath}/admin/schedules">Расписания</a>
        <a href="${request.contextPath}/admin/bookings">Бронирования</a>
        <a href="${request.contextPath}/admin/reviews">Отзывы</a>
        <a href="${request.contextPath}/admin/users">Пользователи</a>
    </nav>

    <section class="admin-summary">
        <div>Всего туров: ${counts.tours!?0}</div>
        <div>Всего пользователей: ${counts.users!?0}</div>
        <div>Всего бронирований: ${counts.bookings!?0}</div>
    </section>
</@layout.page>
