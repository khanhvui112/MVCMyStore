$(document).ready(function () {

    $('.home-menu-search').keyup(function () {
        let text_search = $(this).val();
        searchHeader(text_search);
    });

    $(document).click(function (e) {
        // show
        let container = $(".autocomplete-suggestions");

        // hide if move out
        if (!container.is(e.target) && container.has(e.target).length === 0) {
            $('.autocomplete-suggestions .autocomplete-suggestion').empty();
            container.hide();
        }
    });
})

function drawProductSearch(data) {
    let append = $('.autocomplete-suggestions');
    append.show();
    $('.autocomplete-suggestion').empty();

    data.forEach(function (product){
        let imageLink = null;
        product.imageLink.forEach(function (i){
            let type = i.imageType;
            if(type === 'default'){
                imageLink = i.imageName;
            }
        })
        $('.autocomplete-suggestion')
            .append(`
            <div class="search-item" onClick="location.href='/products/${product.productId}'">
                <div class="img"><img src="${imageLink}"></div>
                <div class="info">
                    <h2><a src="${imageLink}">${product.productName}</a></h2>
                    ` +
                    (product.sale_code !== null
                    && product.sale_code.length > 0 ?
                        `<h3>${product.priceSales.toLocaleString()} ₫</h3>
                                        <strike>${product.price.toLocaleString()} ₫</strike>` :
                        `<h3>${product.price.toLocaleString()} ₫</h3>`) +
                    `
                </div>
            </div>
            `)
    })

}

function searchHeader(text_search) {
    const url = 'api/v1/products/search?name=' + text_search;
    $.ajax({
        type: 'get',
        url: url
    }).done(function (data) {
        data = data.data;
        drawProductSearch(data);
    });
}