function validateForm() {
            var password1 = document.getElementById('password');
            var password2 = document.getElementById('password2');
            if (password1.value !== password2.value) {
                alert('Пароли не совпадают!');
                return false;
            }
        }