<#import "/layout/base.ftl" as layout>
<@layout.page title="Импорт CSV">
    <h1>Импорт CSV (туры)</h1>

    <form id="csvForm" onsubmit="event.preventDefault(); uploadCsv('csvFile','csvResult');" enctype="multipart/form-data">
        <input type="file" id="csvFile" name="file" accept=".csv" required>
        <button type="submit">Загрузить</button>
    </form>

    <div id="csvResult"></div>

    <p>Ожидаемый формат CSV (без заголовка): <code>name,destination,description,price,img</code></p>
</@layout.page>
