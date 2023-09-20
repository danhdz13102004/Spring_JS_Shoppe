
function clickClose() {
    document.querySelector('#success').classList.add('close');
}

function checkEmpty() {
    fetch(Urls) 
        .then(function(response) {
            return response.json();
        })
        .then(function(value) {
            return value.length;
        })
        .then(function(value) {
            if(value === 0) {
                var a = document.querySelector('.header__cart-img-nocart.close');
                var b = document.querySelector('.header__cart-list-msg.close');
                if(!(a===null)) {
                    a.classList.remove('close');
                }
                if(!(b===null)) {
                    b.classList.remove('close');
                }
                document.querySelector('.header__cart-list-have-item').classList.add('close');
            }
            else {
                document.querySelector('.header__cart-img-nocart').classList.add('close');
                document.querySelector('.header__cart-list-msg').classList.add('close');
                document.querySelector('.header__cart-list-have-item').classList.remove('close');
                
            }
        })
}


function showRegister() {
    document.querySelector('.modal').classList.add('open_modal');
    document.querySelector('.modal_overlay').classList.add('open');
    document.querySelector('.modal_register').classList.add('open');
}

function showLogin() {
    document.querySelector('.modal').classList.add('open_modal');
    document.querySelector('.modal_overlay').classList.add('open');
    document.querySelector('.modal_login').classList.add('open');
}

function closeRegister() {
    document.querySelector('.modal').classList.remove('open_modal');
    document.querySelector('.modal_overlay').classList.remove('open');
    document.querySelector('.modal_register').classList.remove('open');
}
function closeLogin() {
    document.querySelector('.modal').classList.remove('open_modal');
    document.querySelector('.modal_overlay').classList.remove('open');
    document.querySelector('.modal_login').classList.remove('open');
}
var a = document.querySelector('.modal_overlay')
a.onclick = function() {
    closeRegister();
    closeLogin();
}