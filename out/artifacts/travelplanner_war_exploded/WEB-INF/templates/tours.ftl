<#import "layout/base.ftl" as layout>

<@layout.page title="Все туры">

    <section class="bt-section">
        <div class="bt-section-header">
            <h1 class="bt-section-title">Все туры</h1>
            <p class="bt-section-subtitle">
                Собрала тебе все побеги в одном месте 💅
            </p>
        </div>

        <div class="bt-tours-grid">
            <#if tours?has_content>
                <#list tours as tour>
                    <article class="bt-tour-card">

                        <#-- КАРТИНКА ТУРА -->
                        <#if tour.imageUrl?? && tour.imageUrl?has_content>
                            <img class="bt-tour-image"
                                 src="${request.contextPath}/static/img/tours/${tour.imageUrl?html}"
                                 alt="${tour.title?html}">
                        </#if>

                        <div class="bt-tour-card-top">
                            <div class="bt-tour-destination">
                                ${tour.destination?html}
                            </div>

                            <h3 class="bt-tour-title">
                                ${tour.title?html}
                            </h3>

                            <p class="bt-tour-description">
                                ${tour.description?html}
                            </p>
                        </div>

                        <div class="bt-tour-meta">
                            <span class="bt-chip">
                                ${tour.durationDays} дней
                            </span>

                            <span class="bt-chip">
                                от ${tour.price} ₽
                            </span>

                            <#if tour.tags?? && tour.tags?has_content>
                                <span class="bt-chip bt-chip-soft">
                                    ${tour.tags?html}
                                </span>
                            </#if>
                        </div>

                        <div class="bt-tour-actions">
                            <#if user??>
                                <form method="post"
                                      action="${request.contextPath}/booking"
                                      class="bt-inline-form">
                                    <input type="hidden" name="tourId" value="${tour.id}">
                                    <button type="submit"
                                            class="bt-btn bt-btn-sm">
                                        Забронировать
                                    </button>
                                </form>
                            <#else>
                                <a href="${request.contextPath}/login"
                                   class="bt-btn bt-btn-sm">
                                    Войти, чтобы забронировать
                                </a>
                            </#if>
                        </div>

                    </article>
                </#list>
            <#else>
                <p>Пока нет ни одного тура. Загляни позже 🌸</p>
            </#if>
        </div>
    </section>

</@layout.page>
