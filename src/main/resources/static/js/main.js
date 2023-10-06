// var itemCurrent ;
// import "/show.js";
var myUrl = window.location.href;
console.log(myUrl);

function focusBtnCurrent(index) {
    var link = ".pagination-item.item" + index;
    var change = document.querySelector(link);
    var current =  document.querySelector(".pagination-item-current");
    if(!(change === current)) {
            current.classList.remove("pagination-item-current");
            change.classList.add("pagination-item-current");
            console.log(current);
            console.log(change);
    }
}
function addToMyWrap(id,quantity) {
    if(quantity === -1) {

    }
    console.log("hehe");
    var url = currentURL + "/cart/add?id=" + id + "&quantity=" + quantity;
    console.log(url);
    fetch(url, {
        method: "GET", // Hoặc "GET" tùy thuộc vào thiết lập của bạn
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(function(response) {
            // console.log("mới tới");
            if (!response.ok) {
                showItemSmallCart(id);
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(function(data) {
            showItemSmallCart(id);
            document.querySelector("")
            // Xử lý dữ liệu trả về từ controller ở đây
        })
        .catch(function(error) {
            // Xử lý lỗi nếu có
        });
}
function deleteById(id,status) {
    var url = currentURL + "/cart/deleteitem?id=" + id;
    console.log(url);
    fetch(url, {
        method: "GET", // Hoặc "GET" tùy thuộc vào thiết lập của bạn
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(function(response) {
            console.log("mới tới");
            if (!response.ok) {
                showItemSmallCart();
                console.log(status);
                if(status === 1) {
                    loadItemToCartList();
                }
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(function(data) {
            console.log("đã tới đây")
            showItemSmallCart(0);
            // Xử lý dữ liệu trả về từ controller ở đây
        })
        .catch(function(error) {
            // Xử lý lỗi nếu có
        });
}

function loadItemToCartList() {
    var url = currentURL + "/api/itemcart";
    console.log(url);
    fetch(url)
        .then(response => {
            if (!response.ok) {
                console.log("Lỗi rồi ae ơi");
                throw new Error("Lỗi khi lấy dữ liệu từ URL");
            }
            return response.json();
        })
        .then(data => {
            var html = "";
            data.forEach(item => {
                console.log(item);
                var price = toPriceString(item.oderItem.price) + '.000đ';
                var myTotal = toPriceString(item.oderItem.price*item.oderItem.quantity ) + '.000đ';
                console.log((price,myTotal));
                html += `<div class="row border-top border-bottom">
                    <div class="row main align-items-center">
                        <div class="col-2"><img class="img-fluid" src="../../static/access/img/${item.category.image}.jpg"></div>
                        <div class="col">
                            <div class="row text-muted">${price}</div>
                            <div  class="row my-col-text">${item.category.name}</div>
                        </div>
                        <div class="col">
                            <span class="my-icon-change-quantity"  onclick="addToMyWrap(${item.category.id}, -1)" > - </span>
                            <span  class="border my-quantity-${item.category.id}">${item.oderItem.quantity}</span>
                            <span class="my-icon-change-quantity"  onclick="addToMyWrap(${item.category.id}, 1)" >+</span>
                        </div>
                        <div   class="col my-total-price-${item.category.id}">${myTotal}</div>
                        <div class="col">
                            <a onclick="deleteById(${item.category.id},1)"  style="color: red;" class="btn-detele-category">X</a>
                        </div>
                    </div>
                </div>`;
            })
            var a = document.querySelector(".my-list");
            var b = document.querySelector(".title-total-item");
            console.log(html);
            if(a !== null) {
                a.innerHTML = html;
            }
            if(b !== null) {
                b.innerText = data.length;
            }
        })
}
function toPriceString(price) {
    price += "";
    var tmp = "";
    var t = 0;
    for(var i = price.length - 1; i >= 0; i--) {
        t++;
        tmp = price[i] + tmp;
        if(t % 3 === 0 && i !== 0) tmp = "." + tmp;
    }
    return tmp;
}

