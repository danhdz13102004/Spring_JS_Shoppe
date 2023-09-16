
var currentURL = window.location.href;
function sortPrice(query) {
    console.log(currentURL);
    const url = currentURL + "categories?sort=newprice" + query;

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

// Gọi hàm để lấy dữ liệu từ URL khi cần
