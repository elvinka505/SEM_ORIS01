<#import "/layout/base.ftl" as layout>
<@layout.page title="${tour.name! 'Тур'}">
    <section class="tour-detail">
        <h1>${tour.name?html}</h1>
        <p class="tour-destination">Направление: ${tour.destination!''?html}</p>
        <p class="tour-created">Добавлен: ${tour.created_at?if_exists}</p>

        <div class="tour-body">
            <div class="tour-description">
                <p>${tour.description!''?html}</p>
                <p><strong>Цена:</strong> ${tour.price?string["#,##0.00"]} ₽</p>
            </div>

            <form onsubmit="event.preventDefault(); bookTour(this.querySelector('button'), ${tour.id});">
                <input type="hidden" name="csrfToken" value="${csrfToken!''}">
                <button type="submit">Добавить в корзину</button>
            </form>

            <#if user?? && (user.role?lower_case == 'admin' || user.role?lower_case == 'moderator')>
                <div class="admin-actions">
                    <a href="${request.contextPath}/admin/tours/edit?id=${tour.id}">Редактировать</a>
                    <button onclick="deleteItem('tours', ${tour.id})">Удалить</button>
                </div>
            </#if>
        </div>
    </section>
</@layout.page>
