// ----------------------------
// ВАЛИДАЦИЯ ФОРМЫ
// ----------------------------
function validateForm(formId) {
    const form = document.getElementById(formId);
    if (!form) return false;

    const inputs = form.querySelectorAll('input[required], textarea[required], select[required]');
    let isValid = true;

    inputs.forEach(input => {
        if (!input.value.trim()) {
            input.classList.add('error');
            isValid = false;
        } else {
            input.classList.remove('error');
        }
    });

    return isValid;
}

// ----------------------------
// TOAST УВЕДОМЛЕНИЯ
// ----------------------------
function showToast(message, duration = 2500) {
    const toast = document.createElement('div');
    toast.className = 'toast-message';
    toast.innerText = message;

    Object.assign(toast.style, {
        position: 'fixed',
        bottom: '25px',
        right: '25px',
        background: 'linear-gradient(90deg, #6F626A, #F5E7ED)',
        color: '#fff',
        padding: '12px 18px',
        borderRadius: '12px',
        boxShadow: '0 8px 18px rgba(50,30,40,0.3)',
        zIndex: 99999,
        fontFamily: 'Playfair Display, serif'
    });

    document.body.appendChild(toast);
    setTimeout(() => toast.remove(), duration);
}

// ----------------------------
// AJAX: ДОБАВЛЕНИЕ ТУРА В КОРЗИНУ
// ----------------------------
async function bookTour(button, tourId) {
    if (!tourId) return;

    const originalText = button.innerText;
    button.disabled = true;
    button.innerText = 'Добавление...';

    try {
        const response = await fetch('/api/cart/add', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({ tourId })
        });

        const result = await response.json();

        if (result.success) {
            const cartCounter = document.getElementById('cartCount');
            if (cartCounter) cartCounter.textContent = result.count;

            showToast('Тур добавлен в корзину 💕');
        } else {
            showToast('Ошибка: ' + (result.error || 'не удалось добавить'));
        }

    } catch (error) {
        console.error(error);
        showToast('Ошибка сети');
    }

    button.disabled = false;
    button.innerText = originalText;
}

// ----------------------------
// AJAX: ИМПОРТ CSV
// ----------------------------
async function uploadCsv(fileInputId, resultBlockId) {
    const fileInput = document.getElementById(fileInputId);
    const resultBlock = document.getElementById(resultBlockId);
    if (!fileInput.files.length) {
        showToast('Выберите CSV файл');
        return;
    }

    const file = fileInput.files[0];
    const formData = new FormData();
    formData.append('file', file);

    try {
        const response = await fetch('/api/csv/import', {
            method: 'POST',
            body: formData
        });

        const data = await response.json();

        if (data.success) {
            resultBlock.innerHTML = `Импортировано: <b>${data.inserted}</b>. Ошибок: <b>${data.errors}</b>`;
            showToast('CSV успешно загружен ✓');
        } else {
            resultBlock.innerHTML = `Ошибка: ${data.error}`;
            showToast('Ошибка при импорте CSV');
        }

    } catch (error) {
        console.error(error);
        resultBlock.innerHTML = 'Ошибка сети';
        showToast('Ошибка сети');
    }
}

// ----------------------------
// AJAX: POST + confirm (универсальная функция)
// ----------------------------
async function adminPostConfirm(url, payload = {}, successMessage = 'Успешно') {
    if (!confirm('Подтвердить действие?')) return;

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(payload)
        });

        const result = await response.json();

        if (result.success) {
            showToast(successMessage);
            setTimeout(() => location.reload(), 700);
        } else {
            showToast('Ошибка: ' + (result.error || 'не выполнено'));
        }
    } catch (error) {
        console.error(error);
        showToast('Ошибка сети');
    }
}

// ----------------------------
// AJAX DELETE (твоё)
// ----------------------------
async function deleteItem(type, id) {
    if (!confirm('Вы уверены?')) return;

    try {
        const response = await fetch(`/${type}/${id}`, { method: 'DELETE' });
        if (response.ok) {
            showToast('Удалено');
            location.reload();
        }
    } catch (error) {
        console.error('Ошибка:', error);
        showToast('Ошибка удаления');
    }
}

// ----------------------------
// ФИЛЬТР ТУРОВ (твоё)
// ----------------------------
function filterTours() {
    const searchInput = document.getElementById('tour-search');
    const tours = document.querySelectorAll('.tour-card');

    if (!searchInput) return;

    const searchTerm = searchInput.value.toLowerCase();
    tours.forEach(tour => {
        const name = tour.querySelector('.tour-name')?.textContent.toLowerCase() || '';
        tour.style.display = name.includes(searchTerm) ? 'block' : 'none';
    });
}

// ----------------------------
// ИНИЦИАЛИЗАЦИЯ (твоё)
// ----------------------------
document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('tour-search');
    if (searchInput) {
        searchInput.addEventListener('input', filterTours);
    }
});

// ----------------------------
// LOGOUT (твоё)
// ----------------------------
function logout(event) {
    event?.preventDefault();
    if (confirm('Выйти из аккаунта?')) {
        window.location.href = '/travelplanner/logout';
    }
}
