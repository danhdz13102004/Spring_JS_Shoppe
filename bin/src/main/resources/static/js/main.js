var Urls  = "http://localhost:3000/course"
var my_data = [
    {
        "id": 1,
        "name" : "Tai nghe Gaming chiến game cực đã",
        "price" : "990.000đ"
    },
    {
        "id": 2,
        "name" : "Bộ thun polo ngực form rộng",
        "price" : "99.000đ"
    },
    {
        "id": 3,
        "name" : "Bộ thun màu xanh form oversize",
        "price" : "1.000đ"
    },
    {
        "id": 4,
        "name" : "Set Đồ Nữ áo kiểu sơ mi",
        "price" : "80.000đ"
    },
    {
        "id": 5,
        "name" : "Bộ quần đùi nam siêu thoáng mát",
        "price" : "90.000đ"
    }
]



function start() {
        showCategory();
        checkEmpty();   
        getCourses(addCoursetoWrap);
}
start();
function getCourses(callback) {
    fetch(Urls)
        .then(function(response){
            return response.json();
        })
        .then(callback)
}
function ResetSL() {
    getCourses(function(course) {
        document.querySelector(".header_cart-badge-contain").innerHTML = `<span class="header_cart-badge">${course.length}</span>`
    })
}

function addCoursetoWrap(course) {
    var html = "";
    course.forEach(function(value) {
        html+= `<li class="header__cart-list-item cart-list-item-${value.id}">
        <img class="header__cart-list-item-img" src="./access/img/img-${value.id}.jpg" alt="">
        <div class="header__cart-list-detail">
            <h3 class="header__cart-list-name">${value.name}</h3>
            <h4>Phân loại : Bạc</h4>
        </div>

        <div class="header__cart-list-number">
            <div class="number-price">
                <h3>${value.price}</h3>
                <h4>x${value.sl}</h4>
            </div>
            <button class="header__cart-list-btn-delete"  onclick="btnDetele(${value.id})">Xóa</button>
        </div>
    </li>
        `
    })
    document.querySelector(".header__cart-list-set").innerHTML = html;
    ResetSL();
    checkEmpty(); 
}

function new_form(data,callback) {
    var oj = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            // 'Content-Type': 'application/x-www-form-urlencoded',
          },
          body: JSON.stringify(data)
    }
    fetch(Urls,oj)
        .then(function(response){
            return response.json();
        })
        .then(callback);
}
function handleChangeForm(data,id,callback) {
    var oj = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            // 'Content-Type': 'application/x-www-form-urlencoded',
          },
          body: JSON.stringify(data)
    }
    fetch(Urls + '/' + id,oj) 
        .then(function(response) {
            return response.json();
        }) 
        .then(callback);
}
function handleDeleteFrom(data,id,callback) {
    var oj = {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            // 'Content-Type': 'application/x-www-form-urlencoded',
          },
          body: JSON.stringify(data)
    }
    fetch(Urls + '/' + id,oj) 
        .then(function(response) {
            return response.json();
        }) 
        .then(callback);
}
function findCategory(id) {
    fetch(Urls)
    .then(function(response) {
        return response.json();
    })
    .then(function(value) {
        return value.forEach(function(key) {
            if(key.id === id) {
                return key;
            }
        })
    })
    .then(function(value) {
        return value;
    })
}

function CreaterCategory(id) {
        var cou_add = my_data.find(function(value) {
            return value.id === id;    
        })
        fetch(Urls) 
        .then(function(response) {
            return response.json();
        })
        .then(function(value) {
            var cou_have = value.find(function(key) {
                return key.id === id;
            }) 
            if(cou_have === undefined) {
                var data = {
                    name : cou_add.name,
                    price: cou_add.price,
                    id : cou_add.id,
                    sl: 1
                }
                new_form(data,function() {
                    getCourses(addCoursetoWrap);
                })
            }
            else {
                var sl1 = cou_have.sl + 1;
                var data =  {
                    name: cou_have.name,
                    price: cou_have.price,
                    sl: sl1
                }
                handleChangeForm(data,cou_have.id,function() {
                    getCourses(addCoursetoWrap);
                })
            }
            showInform();   
        })

}
function showInform() {
    var a = document.querySelector('#success.close');
    if(!(a===null)) {
        a.classList.remove('close');
    }
    var html = `<div class="toast toast--success">
    <div class="toast_icon">
        <i class="fa-solid fa-circle-check"></i>
    </div>
    <div class="toast_body">
        <h3 class="toast_title">
            Thành Công
        </h3>
        <p class="toast_msg">
            Bạn đã thêm sản phẩn vào giỏ hàng
        </p>
    </div>
    <div class="toast_close" onclick="clickClose()">
        <i class="fa-solid fa-xmark"></i>
    </div>
</div> `
    document.querySelector("#success").innerHTML = html;
    setTimeout(function() {
        document.querySelector("#success").classList.add('close');
    },3000)

}


function btnDetele(id) {
    var cou_add = my_data.find(function(value) {
        return value.id === id;
    })
    handleDeleteFrom(cou_add,id,function() {
        document.querySelector(`.cart-list-item-${id}`).remove();
        checkEmpty(); 
        ResetSL();
    })
}


function showCategory() {
    html = "";
    my_data.forEach(function(item) {
        html+= `
        <div class="grid_column-2-4">
                                        <div class="home-product-item">
                                            <div class="home-product-item--img" style="background-image: url(./access/img/img-${item.id}.jpg);">
                                            </div>
                                            <h4 class="home-product-item_name">${item.name}</h4>
                                            <div class="home-product-item_price">
                                                <span class="home-product-item_price-old">1.200.000đ</span>
                                                <span class="home-product-item_price-current">${item.price}</span>
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

                                                    <button class="add-my-wrap" onclick="CreaterCategory(${item.id})">Thêm vào giỏ hàng</button>
                                                </div>
                                        </div>
                                    </div>
        `
    });
    document.querySelector('.main_row_category').innerHTML = html;

}

function priceHigher() {
    // console.log(this);
    my_data.sort(function(a, b) {
        var x = a.price;
        var y = b.price;
    
        if (x.length != y.length) {
            if (x.length < y.length) return -1;
            return 1;
        } else {
            if (x < y) return -1;
            return 1;
        }
    }); 
    showCategory();
}
function priceLower() {
    // console.log(this);
    my_data.sort(function(a, b) {
        var x = a.price;
        var y = b.price;
    
        if (x.length != y.length) {
            if (x.length < y.length) return 1;
            return -1;
        } else {
            if (x < y) return 1;
            return -1;
        }
    }); 
    showCategory();
}