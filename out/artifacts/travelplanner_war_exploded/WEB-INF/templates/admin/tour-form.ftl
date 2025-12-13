<#import "../layout/base.ftl" as layout>
<@layout.page title=(tour??)?then("Редактирование тура", "Новый тур")>

    <section class="bt-section">
        <div class="bt-section-header">
            <h1 class="bt-section-title">
                <#if tour??>
                    Редактирование тура «${tour.title?html}»
                <#else>
                    Новый тур
                </#if>
            </h1>
            <p class="bt-section-subtitle">
                Заполни данные, и тур появится в каталоге у пользователей.
            </p>
        </div>

        <#if error??>
            <div class="bt-alert bt-alert-error">
                ${error?html}
            </div>
        </#if>

        <form method="post"
              action="${request.contextPath}/admin/tours"
              class="bt-form bt-card">

            <input type="hidden" name="action" value="save">

            <#if tour??>
                <input type="hidden" name="id" value="${tour.id}">
            </#if>

            <div class="bt-form-group">
                <label for="title" class="bt-label">Название тура</label>
                <input type="text" id="title" name="title" class="bt-input" required
                       value="<#if tour??>${tour.title?html}</#if>">
            </div>

            <div class="bt-form-group">
                <label for="destination" class="bt-label">Направление</label>
                <input type="text" id="destination" name="destination" class="bt-input" required
                       value="<#if tour??>${tour.destination?html}</#if>">
            </div>

            <div class="bt-form-group">
                <label for="price" class="bt-label">Цена</label>
                <input type="number" step="0.01" min="0"
                       id="price" name="price"
                       class="bt-input" required
                       value="<#if tour??>${tour.price}</#if>">
            </div>

            <div class="bt-form-group">
                <label for="durationDays" class="bt-label">Длительность (дней)</label>
                <input type="number" min="1"
                       id="durationDays" name="durationDays"
                       class="bt-input" required
                       value="<#if tour??>${tour.durationDays}</#if>">
            </div>

            <div class="bt-form-group">
                <label for="tags" class="bt-label">Теги (через запятую)</label>
                <input type="text" id="tags" name="tags" class="bt-input"
                       value="<#if tour??>${tour.tags!""}</#if>">
            </div>

            <div class="bt-form-group">
                <label for="description" class="bt-label">Описание</label>
                <textarea id="description" name="description" rows="5"
                          class="bt-textarea" required><#if tour??>${tour.description!""}</#if></textarea>
            </div>

            <div class="bt-form-actions">
                <a href="${request.contextPath}/admin/tours" class="bt-btn bt-btn-ghost">
                    Отмена
                </a>
                <button type="submit" class="bt-btn bt-btn-primary">
                    Сохранить
                </button>
            </div>
        </form>
    </section>

</@layout.page>
