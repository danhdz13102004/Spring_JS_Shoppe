
var currentURL = window.location.protocol + "//" + window.location.host;
// Lấy URL mặc định của trang chủ
var checkSortHigher = false;
var checkSortLower = false;
var indexPage = 1;
var numberElement ;
var limitItem = 1;
var currentPage = 1;
var numberPage = 10;

// Lấy số lượng item để phân trang
function getTotalItem () {
    var url = currentURL + "/categories";
    return  fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Lỗi khi lấy dữ liệu từ URL");
            }
            return response.json();
        })
        .then(data => {
            return data.page.totalElements;
        })
        .then(number => {
            numberElement = number;
        })

}

// Sắp xếp tăng giảm các item
function sortPrice(query) {
    if(query === ",desc") {
        checkSortLower = true;
        checkSortHigher = false;
        document.querySelector(".priceHigher .my-check-icon").classList.remove("show");
        document.querySelector(".priceLower .my-check-icon").classList.add("show");
    }
    else {
        checkSortHigher = true;
        checkSortLower = false;
        document.querySelector(".priceHigher .my-check-icon").classList.add("show");
        document.querySelector(".priceLower .my-check-icon").classList.remove("show");
    }
    // loadItemChangePage(1);
    focusBtnCurrent(1);
    console.log(currentURL);
    const url = currentURL + "/categories?sort=newprice" + query + "&page" + parseInt(currentPage-1) + "&size=" + limitItem;
    console.log(url);
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Lỗi khi lấy dữ liệu từ URL");
            }
            return response.json();
        })
        .then(data => {
            // Xử lý dữ liệu JSON ở đây
            console.log("ok");
            return data._embedded.categories;

        })
        .then(arr => {
            RenderItem(arr);
        })
        .catch(error => {
            // console.error(error);
        });
}

// Render ra các item chính
function RenderItem(arr) {
    var row = document.querySelector(".grid_row.main_row_category");
    html = "";
    arr.forEach(function (item) {
        html += `
        <div class="grid_column-2-4">
                                        <div class="home-product-item">
                                            <div class="home-product-item--img" style="background-image: url(static/access/img/${item.image}.jpg);">
                                            </div>
                                            <div class="home-product-item-contain-title">
                                                <h4 class="home-product-item_name">${item.name}</h4>
                                            </div>
                                            <div class="home-product-item_price">
                                                <span class="home-product-item_price-old">${item.oldpriceShow}đ</span>
                                                <span class="home-product-item_price-current">${item.newpriceShow}đ</span>
                                            </div>
                                            <div class="home-product-item_action">
                                                <span class="home-product-item_like " > 
                                                    <i class="home-product-item_like-empty fa-regular fa-heart"></i>
                                                    <i class="home-product-item_like-fill fa-solid fa-heart"></i>
                                                </span>
                                                <div class="home-product-item_rating">
                                                    <i class="home-product-item_rated fa-sharp fa-solid fa-star"></i>
                                                    <i class="home-product-item_rated fa-sharp fa-solid fa-star"></i>
                                                    <i class="home-product-item_rated fa-sharp fa-solid fa-star"></i>
                                                    <i class="fa-regular fa-star"></i>
                                                    <i class="fa-regular fa-star"></i>
                                                </div>
                                                <span class="home-product-item_sold">88 Đã bán</span>
                                            </div>
                                                <div class="home-product-item_origin">
                                                    <span class="home-product-item_brand">Who?</span>
                                                    <span class="home-product-item_origin-name">Việt Nam</span>
                                                </div>
                                                <div class="home-product-item_favorite">
                                                    <i class="fa-solid fa-check"></i>
                                                    <span class="home-product-item_favorite-status">Yêu thích</span>
                                                </div>
                                                <div class="home-product-item_sale-off">
                                                    <span class="home-product-item_sale-off-percent">10%</span>
                                                    <span class="home-product-item_sale-off-lable">GIẢM</span>
                                                </div>
                                                <div class="container-btn-click">

                                                    <button class="add-my-wrap" onclick="CreaterCategory(1)">Thêm vào giỏ hàng</button>
                                                </div>
                                        </div>
                                    </div>
        `
    })
    console.log(row);
    row.innerHTML = html;
}

// Khi chuyển page sẽ load lại các item
function loadItemChangePage(index) {
    currentPage = index;
    loadPage();
    focusBtnCurrent(index);
    showCurrentPage();
    index = parseInt(index) - 1;
    var url = currentURL + "/categories?page=" + index + "&size=" + limitItem ;
    if(checkSortHigher) url += "&sort=newprice";
    else if(checkSortLower) url += "&sort=newprice,desc";

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Lỗi khi lấy dữ liệu từ URL");
            }
            return response.json();
        })
        .then(data => {
            // Xử lý dữ liệu JSON ở đây
            // console.log(1);
            console.log(data._embedded.categories);
            return data._embedded.categories;

        })
        .then(arr => {
            RenderItem(arr);
            // loadPage();
        })
        .catch(error => {
            // console.error(error);
        });

}
// Hàm trả về nút ...
function loadContinue(html) {
    html += "<li class=\"pagination-item\">\n" +
        "                                <a style=\"cursor: default\" href=\"#\" class=\"pagination-item__link\">\n" +
        "                                    ...\n" +
        "                                </a>\n" +
        "                            </li>";
    return html;
}
function loadDetailPage(html,i) {
    if (i === 1) {
    html += `<li class="pagination-item pagination-item-current item${i}" onclick="focusBtnCurrent(${i});  loadItemChangePage(${i})">
                                <a href="#" class="pagination-item__link"  onclick="loadItemChangePage(${i})">
                                    ${i}
                                </a>
                            </li>`;
    }
    else {
        html += `<li class="pagination-item item${i}" onclick="focusBtnCurrent(${i});  loadItemChangePage(${i})">
                                <a href="#" class="pagination-item__link"  onclick="loadItemChangePage(${i})">
                                    ${i}
                                </a>
                            </li>`;
    }

    return html;
}

// Hàm hiện ra trang hiện tại khi user chuyển page
function showCurrentPage() {
    document.querySelector(".home-filter-number .current-page").innerText = currentPage;
    document.querySelector(".home-filter-number .total-page").innerText = "/" + numberPage;
    var btnLeft = document.querySelector(".btn-left");
    var btnRight = document.querySelector(".btn-right");
    if(currentPage === 1 || currentPage=== numberPage) {
        if(currentPage === 1) btnLeft.classList.add("btn-setting");
        if(numberPage === currentPage) btnRight.classList.add("btn-setting");
    }
    else {
        btnLeft.classList.remove("btn-setting");
        btnRight.classList.remove("btn-setting");
    }
    var btn = document.querySelector(".btn-setting");
    if(btn !== null) {
        btn.addEventListener("click",function (event) {
            event.preventDefault();
        });
    }

}
function clickToChangePage(direct) {
    if (direct === "left") {
        if(currentPage > 1) currentPage--;
    }
    else {
        if(currentPage < numberPage) currentPage++;
    }
    showCurrentPage();
    loadItemChangePage(currentPage);
    loadPage();
    focusBtnCurrent(currentPage);
}
function loadPage() {
    // var numberPage = (numberElement%limitItem===0)?numberElement/limitItem:numberElement/limitItem+1;
    var html = "<li class=\"pagination-item pagination-item-icon\">\n" +
        "                                <a onclick=\"clickToChangePage('left')\" href=\"#\" class=\"pagination-item__link\">\n" +
        "                                    <i class=\"pagination-item__icon fa-solid fa-angle-left\"></i>\n" +
        "                                </a>\n" +
        "                            </li>";
    if(currentPage <= 3) {
        for(var i = 1;i<=6;i++) {
            if(i===6) {
                html = loadContinue(html);
                break;
            }
            html = loadDetailPage(html,i);
        }
    }
    else {
        for(var i=1;i<=2;i++){
            html = loadDetailPage(html,i);
        }
        if(currentPage - 2 > 3) {
            html = loadContinue(html);
        }
        for(var i = currentPage-2;i<=currentPage+2;i++) {
            if(i > 2 && i<=numberPage) {
                html = loadDetailPage(html,i);
            }
        }
        if(currentPage + 2 < numberPage) {
            html = loadContinue(html);
        }

    }
    html += "<li class=\"pagination-item pagination-item-icon\">\n" +
        "                                <a onclick=\"clickToChangePage('right')\" href=\"#\" class=\"pagination-item__link\">\n" +
        "                                    <i class=\"pagination-item__icon fa-solid fa-angle-right\"></i>\n" +
        "                                </a>\n" +
        "                            </li>";

   var a =  document.querySelector(".my-list-contain-page");
   a.innerHTML = html;
}
setTimeout(
    function () {
        loadPage();
        showCurrentPage();
    }
    ,100)
showCurrentPage();
getTotalItem();


// Gọi hàm để lấy dữ liệu từ URL khi cần
