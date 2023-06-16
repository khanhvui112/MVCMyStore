$(document).ready(function() {
    getCategory();
});

function getCategory() {
    const url = 'api/v1/categorys'



    fetch(url)
        .then((resp) => resp.json()) // Transform the data into json
        .then(function (data) {
            for (let p in data.data) {

                let o = data.data[p];

                let caId = o.caId;

                let params = { 'category':caId};

                let url = (location.pathname+location.search).substr(1)

                let new_url;
                if(url.length > 1){
                    new_url =  window.location.pathname+'?' + jQuery.param(params);
                }else {
                    new_url =  window.location.pathname+'products?' + jQuery.param(params);
                }


                $(".header_main>div>.main-menu").append(`
                 <li>
                        <i></i>
                        <a id="product-id-${o.caId}" href="${new_url}">${o.caName}</a>
                 </li>
                `)
            }


        });
}