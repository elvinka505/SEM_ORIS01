<#import "/layout/base.ftl" as layout>
<@layout.page title="Редактировать тур">
    <h1>Редактировать тур</h1>

    <form id="editTourForm" method="post" action="${request.contextPath}/admin/tours/edit" onsubmit="return validateForm('editTourForm');">
        <input type="hidden" name="csrfToken" value="${csrfToken!''}">
        <input type="hidden" name="id" value="${tour.id}">
        <div class="form-row">
            <label for="name">Название</label>
            <input id="name" name="name" type="text" value="${tour.name!''}" required>
        </div>
        <div class="form-row">
            <label for="destination">Направление</label>
            <input id="destination" name="destination" type="text" value="${tour.destination!''}" required>
        </div>
        <div class="form-row">
            <label for="price">Цена (₽)</label>
            <input id="price" name="price" type="number" step="0.01" value="${tour.price!0}" required>
        </div>
        <div class="form-row">
            <label for="description">Описание</label>
            <textarea id="description" name="description" required>${tour.description!''}</textarea>
        </div>
        <div class="form-row">
            <label for="img">URL изображения (опц.)</label>
            <input id="img" name="img" type="text" value="${tour.img!''}">
        </div>
        <div class="form-row">
            <button type="submit">Сохранить</button>
            <button type="button" onclick="deleteItem('tours', ${tour.id})">Удалить</button>
        </div>
    </form>
</@layout.page>
