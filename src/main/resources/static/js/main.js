// var itemCurrent ;

function focusBtnCurrent(index) {
    var link = ".pagination-item.item" + index;
    var change = document.querySelector(link);
    var current =  document.querySelector(".pagination-item-current");
    if(!(change === current)) {
            current.classList.remove("pagination-item-current");
            change.classList.add("pagination-item-current");
            console.log(current);
            console.log(change);
            console.log("yess");
    }
}