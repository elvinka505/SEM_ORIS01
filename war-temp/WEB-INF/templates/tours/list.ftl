<#import "/layout/base.ftl" as layout>
<@layout.page title="Все туры">
    <h1>Туры</h1>

    <div class="controls">
        <a class="btn" href="${request.contextPath}/admin/tours">Админка (туры)</a>
        <input id="tour-search" type="text" placeholder="Поиск туров..." />
    </div>

    <section class="tours-grid">
        <#if tours?has_content>
            <#list tours as tour>
                <article class="tour-card">
                    <h3 class="tour-name">${tour.name?html}</h3>
                    <p class="tour-destination">Направление: ${tour.destination!''?html}</p>
                    <p class="tour-desc">${tour.description?substring(0,160)!''?html}...</p>
                    <div class="tour-meta">
                        <span class="tour-price">${tour.price?string["#,##0.00"]} ₽</span>
                        <div class="tour-actions">
                            <button onclick="bookTour(this, ${tour.id})">Добавить в корзину</button>
                            <a href="${request.contextPath}/tours/${tour.id}">Подробнее</a>
                        </div>
                    </div>
                </article>
            </#list>
        <#else>
            <p>Нет туров.</p>
        </#if>
    </section>
</@layout.page>
