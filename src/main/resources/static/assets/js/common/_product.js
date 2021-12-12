$(document).ready(function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const pageId = urlParams.get('page');

    if(pageId !== null){
        let pageId1 = Number(pageId);
        $(`#page-${pageId1}`).css({
            'color': 'navy',
            'background': 'lightblue'
        });
    }

})