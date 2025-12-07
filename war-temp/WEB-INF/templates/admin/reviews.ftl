<#import "/layout/base.ftl" as layout>
<@layout.page title="Отзывы">
    <h1>Отзывы</h1>

    <#if reviews?has_content>
        <ul class="reviews-list">
            <#list reviews as r>
                <li>
                    <strong>${r.userName!''}</strong> — ${r.rating!0}/5<br/>
                    <p>${r.text!''}</p>
                    <small>Тур: ${r.tourName!''}, ${r.created_at}</small>
                    <div><button onclick="deleteItem('reviews', ${r.id})">Удалить</button></div>
                </li>
            </#list>
        </ul>
    <#else>
        <p>Нет отзывов.</p>
    </#if>
</@layout.page>
