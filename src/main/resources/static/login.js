const inputField = document.getElementById('email');
const label = document.querySelector('.inputbox label');

inputField.addEventListener('input', () => {
    if (inputField.value !== '') {
        label.classList.add('active');
    } else {
        label.classList.remove('active');
    }
});