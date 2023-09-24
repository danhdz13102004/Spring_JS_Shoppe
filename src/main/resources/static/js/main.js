// var itemCurrent ;
// import "/show.js";
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
                showItemSmallCart();
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(function(data) {
            console.log("đã tới đây")
            showItemSmallCart();
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
            showItemSmallCart();
            // Xử lý dữ liệu trả về từ controller ở đây
        })
        .catch(function(error) {
            // Xử lý lỗi nếu có
        });
}
