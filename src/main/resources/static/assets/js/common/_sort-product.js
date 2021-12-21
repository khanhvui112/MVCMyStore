$(document).ready(function () {
    $(".facet").on({
        mouseenter: function () {
            $(this).find('.sub').show();
        },
        mouseleave: function () {
            $(this).find('.sub').hide();
        }
    });
    loadSort();
    loadCategory();
    loadManufacturer();

})

async function getDataAsy(url) {
    const response = await fetch(url);
    return await response.json();
}

function getData(url) {

    return fetch(url)
        .then((response) => {
            return response.json().then((data) => {
                return data.data;
            }).catch((err) => {
                console.log(err);
            })
        });

}

let dataSort = [
    {
        "name": "-price",
        "sortName": "Giá cao đến thấp"
    },
    {
        "name": "price",
        "sortName": "Giá thấp đến cao"
    },
    {
        "name": "top20",
        "sortName": "Mới cập nhật"
    },
    {
        "name": "topView",
        "sortName": "Xem nhiều nhất"
    }
]

function loadSort() {
    let url = (location.pathname + location.search).substr(1);

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const nameSort = urlParams.get('sort')


    const urlFinal = url;
    let saveUrl;

    //reset page url
    let urlReset;
    if (url.includes('?sort') && url.includes('&') === false) {
        url = url.substr(0, url.indexOf('?'));
        urlReset = url + '?';
    }

    if (url.includes('?')) {

        if (url.includes('?sort') && urlFinal.includes('&') === false) {
            url = location.pathname + '?';
        } else {
            url = '/' + urlFinal + '&';
        }

        if (url.includes('&sort')) {
            url = urlFinal.substr(0, urlFinal.indexOf('&sort') + 1);
        }

        if (urlFinal.includes('?sort') && urlFinal.includes('&')) {
            saveUrl = urlFinal.substr(urlFinal.indexOf('&'));
            url = urlFinal.substr(0, urlFinal.indexOf('?sort') + 1);
        }

    } else {

        if (urlReset !== undefined) {
            url = urlReset;
        } else {
            url = location.pathname + '?';
        }

    }

    if(url.includes('page') ){
        url = "/products?";
    }

    if(url.includes("page")){
        url = url.substr(0, urlFinal.indexOf('page') +1);
    }

    if(saveUrl !== undefined && saveUrl.includes("page")){
        let valuePage = urlParams.get("page");
        let replace = "&page="+ valuePage;
        saveUrl = saveUrl.replaceAll(replace,"");
    }

    dataSort.forEach(function (sort) {

        if ( nameSort !== null && sort.name === nameSort ) {
            const fillCategory = $('#fill-sort');
            fillCategory.text(sort.sortName + ' ');
            fillCategory.append(`<i class="fa fa-angle-down"></i>`);
        }

        let params = {'sort': sort.name};



        let urlReal;



        /*
        * not save url if indexPage have value
        * */
        if (saveUrl !== undefined) {
            urlReal = url + $.param(params) + saveUrl;
        } else {
            urlReal = url + $.param(params);
        }
        if(sort.name === 'top20'){
            $(".sort ul").append(`<li><a href="/products?sort=top20">${sort.sortName}</a></li>`)
        }else {
            $(".sort ul").append(`<li><a href="${urlReal}">${sort.sortName}</a></li>`)
        }

    })


}

function loadCategory() {
    let url = (location.pathname + location.search).substr(1);

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const titleCategory = urlParams.get('category')


    const urlFinal = url;
    let saveUrl;

    //reset page url
    let urlReset;
    if (url.includes('?page') && url.includes('&') === false) {
        url = url.substr(0, url.indexOf('?'));
        urlReset = url + '?';
    }

    if (url.includes('?')) {

        if (url.includes('?category') && urlFinal.includes('&') === false) {
            url = location.pathname + '?';
        } else {
            url = '/' + urlFinal + '&';
        }

        if (url.includes('&category')) {
            url = urlFinal.substr(0, urlFinal.indexOf('&category') + 1);
        }

        if (urlFinal.includes('?category') && urlFinal.includes('&')) {
            saveUrl = urlFinal.substr(urlFinal.indexOf('&'));
            url = urlFinal.substr(0, urlFinal.indexOf('?category') + 1);
        }

    } else {

        if (urlReset !== undefined) {
            url = urlReset;
        } else {
            url = location.pathname + '?';
        }

    }

    if(url.includes("page") || saveUrl !== undefined && saveUrl.includes("page")){
        url = url.substr(0, urlFinal.indexOf('page') +1);
    }


    if(saveUrl !== undefined && saveUrl.includes("page")){
        let valuePage = urlParams.get("page");
        let replace = "&page="+ valuePage;
        saveUrl = saveUrl.replaceAll(replace,"");
    }

    getDataAsy('/api/v1/categorys').then(c => {
        c.data.forEach(function (cate) {

            if ((cate.caId === Number(titleCategory))) {
                $('#title-category').text((cate.caName).toUpperCase());

                const fillCategory = $('#fill-category');
                fillCategory.text(cate.caName + ' ');
                fillCategory.append(`<i class="fa fa-angle-down"></i>`);

            }

            let params = {'category': cate.caId};

            let urlReal;
            if (saveUrl !== undefined) {
                urlReal = url + $.param(params) + saveUrl;
            } else {
                urlReal = url + $.param(params);
            }

            $(".category ul").append(`<li><a href="${urlReal}">${cate.caName}</a></li>`)
        })

    })

}

function loadManufacturer() {

    let url = (location.pathname + location.search).substr(1)

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const titleManufacturer = urlParams.get('manufacturer')

    const urlFinal = url;
    let saveUrl;

    //reset page url
    let urlReset;
    if (url.includes('?page') && url.includes('&') === false) {
        url = url.substr(0, url.indexOf('?'));
        urlReset = url + '?';
    }

    if (url.includes('?')) {


        if (url.includes('?manufacturer') && urlFinal.includes('&') === false) {
            url = location.pathname + '?';
        } else {
            url = '/' + urlFinal + '&';
        }

        if (url.includes('&manufacturer')) {
            url = urlFinal.substr(0, urlFinal.indexOf('&manufacturer') + 1);
        }

        if (urlFinal.includes('?manufacturer') && urlFinal.includes('&')) {
            saveUrl = urlFinal.substr(urlFinal.indexOf('&'));
            url = urlFinal.substr(0, urlFinal.indexOf('?manufacturer') + 1);
        }

    } else {
        if (urlReset !== undefined) {
            url = urlReset;
        } else {
            url = location.pathname + '?';
        }
    }

    if(url.includes("page")){
        url = url.substr(0, urlFinal.indexOf('page') +1);
    }

    if(saveUrl !== undefined && saveUrl.includes("page")){
        let valuePage = urlParams.get("page");
        let replace = "&page="+ valuePage;
        saveUrl = saveUrl.replaceAll(replace,"");
    }

    getDataAsy('/api/v1/manufacturers').then(c => {
        c.data.forEach(function (ma) {

            if ((ma.maId === Number(titleManufacturer))) {

                const fillCategory = $('#fill-manufacturer');
                fillCategory.text(ma.maName + ' ');
                fillCategory.append(`<i class="fa fa-angle-down"></i>`);

            }

            let params = {'manufacturer': ma.maId};

            let urlReal;
            if (saveUrl !== undefined) {
                urlReal = url + $.param(params) + saveUrl;
            } else {
                urlReal = url + $.param(params);
            }

            $(".manufacturer ul").append(`<li><a href="${urlReal}">${ma.maName}</a></li>`)
        })

    })

}
