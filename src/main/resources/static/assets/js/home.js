$(document).ready(function () {
    getProduct();
    $('.owl-1').owlCarousel({

        loop: true,
        margin: 0,
        nav: true,
        items: 1,
        smartSpeed: 1000,
        autoplay: true,
        autoplayHoverPause: true,
        navText: ['<i class="fa fa-chevron-left" aria-hidden="true"></i>', '<i class="fa fa-chevron-right" aria-hidden="true"></i>']
    })

})




function getProduct() {
    const url = '/api/v1/products'
    fetch(url)
        .then((resp) => resp.json())
        .then(function (data) {
            if (data.success === true) {
                drawDateProduct(data.data);
            } else {
                alert("Get data fail.")
            }

        });
}

function drawDateProduct(data) {
    data.forEach(function (product){
        let imageLink = null;
        product.imageLink.forEach(function (image) {
            if(image.imageType === 'default'){
                imageLink = image.imageName;
            }
        })
        $(".lts-products")
            .append(`
                        <div class="item">` +
                (product.sale_code !== null && product.sale_code.length > 0 ? `<div class="sticker sticker-left">
                               <span>-${product.sale_code}</span>
                                </div>` : ``) +
                `<div class="img">
                              <a href="/products/${product.productId}" title="${product.productName}">
                                <img src="${imageLink}" alt="${product.productName}" title="${product.productName}">
                              </a>
                            </div>
                            <div class="info">
                              <a href="/product/product-${product.productId}" class="title" title="${product.productName}">${product.productName}</a>
                              <span class="price">
                                ` +
                                (product.sale_code !== null
                                && product.sale_code.length > 0 ?
                                    `<strong>${product.priceSales.toLocaleString()} ₫</strong>
                                    <strike>${product.price.toLocaleString()} ₫</strike>` :
                                    `<strong>${product.price.toLocaleString()} ₫</strong>`) +
                                `
                               </span>
                            </div>
                            <a id="product-${product.productId}" type="button" class="btn btn-primary add-to-cart">Thêm vào giỏ hàng</a>
                          </div>
         `)
    })
    for (let p in data) {

    }
}

function priceSale(data) {
    let price;
    let sale;

    /*
    * if data.price != null
    * */
    if (data.price !== '' && data.sale_code !== null) {
        //replace char ',' after parser to int
        let indexC = data.price.indexOf(',');
        if (indexC !== -1) {
            price = parseInt((data.price).replaceAll(",", ''));
        } else {
            price = parseInt((data.price).replaceAll(".", ''));
        }

        let index;

        if (data.sale_code !== null) {
            index = (data.sale_code).indexOf("%");
        }

        if (index === -1) {
            let saleCode = (data.sale_code).replace('K', '000');
            price = price - parseInt(saleCode);
            return price.toLocaleString();
        }

        /*
        *  data.sale_code != null and have %
        * */
        if (index !== undefined) {


            //replace char '%' after parser to int
            sale = parseInt((data.sale_code).replaceAll("%", ''));

            //sum price after sale
            price = price * (100 - sale) / 100;

            //convert to String
            price = price.toLocaleString();
            return price;
        }

    }
    return null;

}





