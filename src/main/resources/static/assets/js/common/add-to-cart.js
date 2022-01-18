$(document).ready(function() {

    $(document).on('click','.add-to-cart',function(e) {
        let id = $(this).attr('id');
        id = id.substring(id.indexOf("-")+1);
        $.ajax({
            url:'/cart/'+id,
            type: 'POST',

        }).done(function(data){
            $('.show-cart').text(data)
            alert('Them san pham thanh cong!')
/*            location.reload();*/
        })

    });
})
