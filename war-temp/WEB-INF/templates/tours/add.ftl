<#import "/layout/base.ftl" as layout>
<@layout.page title="Добавить тур">
    <h1>Добавить тур</h1>

    <form id="addTourForm" method="post" action="${request.contextPath}/admin/tours/add" onsubmit="return validateForm('addTourForm');">
        <input type="hidden" name="csrfToken" value="${csrfToken!''}">
        <div class="form-row">
            <label for="name">Название</label>
            <input id="name" name="name" type="text" required>
        </div>
        <div class="form-row">
            <label for="destination">Направление</label>
            <input id="destination" name="destination" type="text" required>
        </div>
        <div class="form-row">
            <label for="price">Цена (₽)</label>
            <input id="price" name="price" type="number" step="0.01" required>
        </div>
        <div class="form-row">
            <label for="description">Описание</label>
            <textarea id="description" name="description" required></textarea>
        </div>
        <div class="form-row">
            <label for="img">URL изображения (опц.)</label>
            <input id="img" name="img" type="text">
        </div>
        <div class="form-row">
            <button type="submit">Создать</button>
        </div>
    </form>
</@layout.page>
