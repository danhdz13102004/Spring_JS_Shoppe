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
            console.log("mới tới");
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
function deleteById(id) {
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
                throw new Error("Lỗi khi lấy dữ liệu từ URL");
            }
            return response.json();
        })
        .then(data => {
            var html = "";
            data.forEach(item => {
                html += `<div class="row border-top border-bottom">
                    <div class="row main align-items-center">
                        <div class="col-2"><img class="img-fluid" th:src="'../../static/access/img/' + ${item.img} + '.jpg'"></div>
                        <div class="col">
                            <div th:text="${item.price} + '.000đ' " class="row text-muted"></div>
                            <div th:text="${item.name}" class="row my-col-text"></div>
                        </div>
                        <div class="col">
                            <span class="my-icon-change-quantity"  th:onclick="'addToMyWrap(' + ${item.id} + ', -1)'" > - </span>
                            <span  class="border " th:text="${item.getQuantity()}"></span>
                            <span class="my-icon-change-quantity"  th:onclick="'addToMyWrap(' + ${item.id} + ', 1)'" >+</span>
                        </div>
                        <div  th:text="${item.total + '.000đ'}" class="col "></div>
                        <div class="col">
                            <a style="color: red;" class="btn-detele-category">X</a>
                        </div>
                    </div>
                </div>`;
            })
            var a = document.querySelector(".my-list");
            if(a !== null) {
                a.innerHTML = html;
            }
        })
}
function changeQuantity() {

}
// if(1) {
//     var aUrl = currentURL + "/cart/detail";
//     var bUrl = currentURL + "/cart/detail?";
//     if(myUrl === aUrl || myUrl === bUrl) {
//         loadItemToCartList();
//     }
// }
// loadItemToCartList();
