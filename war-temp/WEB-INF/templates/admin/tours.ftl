<#import "/layout/base.ftl" as layout>
<@layout.page title="Управление турами">
    <h1>Управление турами</h1>
    <a class="btn" href="${request.contextPath}/admin/tours/add">Добавить тур</a>

    <#if tours?has_content>
        <table class="admin-table">
            <thead><tr><th>ID</th><th>Название</th><th>Направление</th><th>Цена</th><th>Действия</th></tr></thead>
            <tbody>
            <#list tours as t>
                <tr>
                    <td>${t.id}</td>
                    <td>${t.name!''}</td>
                    <td>${t.destination!''}</td>
                    <td>${t.price?string["#,##0.00"]} ₽</td>
                    <td>
                        <a href="${request.contextPath}/admin/tours/edit?id=${t.id}">Редактировать</a>
                        <button onclick="deleteItem('tours', ${t.id})">Удалить</button>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <p>Туров нет.</p>
    </#if>
</@layout.page>
