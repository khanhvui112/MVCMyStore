$(document).ready(function () {

    $('.home-menu-search').keyup(function () {
        let text_search = $(this).val();
        searchHeader(text_search);
    });

    $(document).click(function (e) {
        // Đối tượng container chứa popup
        let container = $(".autocomplete-suggestions");

        // Nếu click bên ngoài đối tượng container thì ẩn nó đi
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
    for (let p in data) {
        $('.autocomplete-suggestion')
            .append(`
            <div class="search-item" onClick="location.href='/products-${data[p].productId}'">
                <div class="img"><img src="${data[p].imageLink}"></div>
                <div class="info">
                    <h2><a src="${data[p].imageLink}">${data[p].productName}</a></h2>
                    <h3> ${data[p].price} ₫<strike></strike></h3>
                </div>
            </div>
            `)
    }


}

function searchHeader(text_search) {
    const url = '/api/v1/products/search?name=' + text_search;
    $.ajax({
        type: 'get',
        url: url
    }).done(function (data) {
        data = data.data;
        drawProductSearch(data);
    });
}