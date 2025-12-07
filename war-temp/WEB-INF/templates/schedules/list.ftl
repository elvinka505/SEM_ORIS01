<#import "/layout/base.ftl" as layout>
<@layout.page title="Расписания">
    <h1>Расписания</h1>
    <a class="btn" href="${request.contextPath}/admin/schedules">Админ: расписания</a>

    <#if schedules?has_content>
        <table class="table">
            <thead><tr><th>ID</th><th>Тур</th><th>Дата начала</th><th>Дата конца</th><th>Локация</th><th>Места</th></tr></thead>
            <tbody>
            <#list schedules as s>
                <tr>
                    <td>${s.id}</td>
                    <td>${s.tourName!''}</td>
                    <td>${s.start_date}</td>
                    <td>${s.end_date}</td>
                    <td>${s.location!''}</td>
                    <td>${s.available_seats!0}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <p>Нет расписаний.</p>
    </#if>
</@layout.page>
