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
    getCategory();


})

function getCategory() {
    const url = '/api/v1/category/find-all'
    fetch(url)
        .then((resp) => resp.json()) // Transform the data into json
        .then(function (data) {
            data = JSON.parse(JSON.stringify(data));
            for (let p in data) {
                let o = data[p];
                $(".header_main>div>.main-menu").append(`
                 <li>
                        <i></i>
                        <a id="product-id-${o.caId}" href="${o.caLink}">${o.caName}</a>
                 </li>
                `)
            }


        });
}


function getProduct() {
    const url = '/api/v1/products'
    fetch(url)
        .then((resp) => resp.json()) // Transform the data into json
        .then(function (data) {
            data = JSON.parse(JSON.stringify(data));
            for (let p in data) {
                let o = data[p];
                let price = priceSale(o);
                $(".lts-products")
                    .append(`
                        <div class="item">` +
                        (o.sale_code !== null ? `<div class="sticker sticker-left">
                               <span>-${o.sale_code}</span>
                                </div>` : ``) +
                        `<div class="img">
                              <a href="/product/product-${o.productId}" title="${o.productName}">
                                <img src="${o.imageLink}" alt="${o.productName}" title="${o.productName}">
                              </a>
                            </div>
                            <div class="info">
                              <a href="/product/product-${o.productId}" class="title" title="${o.productName}">${o.productName}</a>
                              <span class="price">` +
                            (o.sale_code === null ? `<strong>${o.price} ₫</strong>`
                            :
                            `<strong>${price} ₫</strong>
                                      <strike>${o.price} ₫</strike>`) +
                        `
                              </span>
                            </div>
                            <button id="product-${o.productId}" type="button" class="btn btn-primary">Mua ngay</button>
                          </div>
                         `)
            }


        });
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
        if(indexC !== -1){
            price = parseInt((data.price).replaceAll(",", ''));
        }else {
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





