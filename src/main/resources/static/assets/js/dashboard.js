$(document).ready(function () {
    $("#home").on("click", function () {
        $(".employee").toggle();

    })
    $("#product").on("click", function () {
        $(".products-hidden").toggle();
    })
    $(".user-profile").on("click", function () {
        $(".dropdown-usermenu").toggle();
    })
    $("#menu_toggle").on('click', function () {
        $("#menu-left").slideToggle('slow');
    });

    $("#navbarDropdown1").on('click', function () {
        $("#notication").toggle();
    });
    $("#all-employee").on('click', function () {

        $("#table-append").toggle();
        drawEmployee(0);
        $(".add-product-container").empty();
    });
    $("#add-product").on('click', function () {
        $("#table-append").hide();
        createProduct();
    });
    $(document).on('click', '#btn-product-add', function () {
        appendForm();
    });

    $(document).on('click', '.first', function () {
        drawEmployee(Number($(this).val()) - 1);
    });

    $(document).on('click', '#btn-product-save', function () {

        $(document).ajaxSend(function () {
            $("#overlay").fadeIn(300);
        });

        let products = getDataForm();

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            processData: false, // tell jQuery not to process the data
            contentType: false, // tell jQuery not to set contentType
            cache: false,
            url: '/api/v1/products',
            data: products,
            success: function (data) {


            },
            fail: function () {
                alert("Danng ky that bai!");
            }
        }).done(function (data) {
            $("#overlay").fadeOut(100);
            if (data.success === false) {
                $('.add-product-container span').text('Thêm sản phẩm thất bại. Đã xảy ra lỗi')
            } else {
                $('.add-product-container span').text('Thêm sản phẩm thành công')
                $('#form-template').trigger("reset");
            }

        });
    });


})

function Pagination(totalPage) {
    //TODO: Pagination
    const body = $(".pagination");
    body.empty();

    for (let i = 0; i < totalPage; i++) {
        if (i === 0) {
            body.append(`
            <li class="page-item">
                <input type="button" class="page-link first" value="${i + 1}"/>
            </li>
        `);
        } else {
            body.append
            (`
               <li class="page-item">
                <input type="button" class="page-link first" value="${i + 1}"/>
               </li>
            `);
        }


    }
}

function drawEmployee(page) {
    const url = '/admin/api-employee/find-all?page=' + page;
    $.ajax({
        url: url,
        type: 'GET',
        success: function (data) {
            if (data.success === true) {
                let page = data.data;
                Pagination(page.totalPages);
                bindingDataEmployee(data);
            } else {
                alert("Đã xảy ra lỗi vui lòng thử lại")
            }

        },
        error: function () {
            window.location = "/accessDenied";
        }
    }).done(function () {
        setTimeout(function () {
            $("#overlay").fadeOut(300);
        }, 100);
    });
}

async function getDataSelect(url) {
    const response = await fetch(url);
    return await response.json();
}

function getDataForm() {
    let data = new FormData();
    let image = $("input[name='imageLink']")[0].files[0];
    data.append('image_Link', image);
    data.append("productName", $("#name-product").val());
    data.append("price", $("#price-product").val());
    data.append("sale_code", $("#sale_code").val());
    data.append("title", $("#title").val());
    data.append("description", $("#description").val());
    data.append("ca_id", $("#category").val());
    data.append("color_id", $("#color").val());
    data.append("ma_id", $("#manufacturer").val());
    data.append("product_detail_id", $("#product_detail_id").val());
    return data;
}

async function getCategory() {
    const url = '/api/v1/categorys';
    const response = await fetch(url);
    return await response.json();
}

function appendForm() {
    $('#form-template').clone().appendTo("#product-padding");
}

function createProduct() {
    $(".right_col .row .container").empty();
    $(".right_col .row").append(
        `
        <div class="container add-product-container">

          <h4 class="mb-3 text-center">THÊM SẢN PHẨM</h4>
          <span id="notication-add-product" style="color:red; text-align: center; display: block;"></span>
      <div >
         <div class="col align-self-center" id="product-padding">
         <form class="needs-validation" id="form-template" novalidate="">
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="firstName">Tên sản phẩm</label>
                <input type="text" class="form-control" name="productName" id="name-product" placeholder="Nhập tên sản phẩm...">
              </div>
               <div class="col-md-3 mb-3">
                <label for="firstName">Giá</label>
                <input type="text" class="form-control" name="price" id="price-product" placeholder="Nhập giá sản phẩm...">
              </div>
              <div class="col-md-3 mb-3">
                <label for="sale_code">Giá giảm</label>
                <input type="text" class="form-control" name="sale_code" id="sale_code" placeholder="Nhập giá giảm...">
              </div>
            </div>

            <div class="mb-3">
              <label for="username">Tiêu đề</label>
              <div class="input-group">
                <textarea class="form-control" name="title" id="title" rows="3"></textarea>
              </div>
            </div>
            <div class="mb-3">
              <label for="username">Miêu tả</label>
              <div class="input-group">
                <textarea class="form-control" name="description" id="description" rows="3"></textarea>
              </div>
            </div>

            <div class="row">
              <div class="col-md-4 mb-3">
                <label for="country">Danh mục</label>
                <select class="custom-select d-block w-100 category-select" name="ca_id" id="category" required="">
                  <option value="">Chọn...</option>
                ` +
        getCategory().then(c => {
            c.data.forEach(function (cate) {
                $(".category-select").append(`<option value="${cate.caId}">${cate.caName}</option>`)
            })

        })
        + `
                </select>
              </div>
              <div class="col-md-4 mb-3">
                <label for="state">Màu sắc</label>
                <select class="custom-select d-block w-100 color-select" name="color_id" id="color" required="">
                  <option value="">Chọn...</option>
                  ` +
        getDataSelect('/api/v1/colors').then(c => {
            c.data.forEach(function (d) {
                $(".color-select").append(`<option value="${d.colorId}">${d.colorName}</option>`)
            })
        })
        + `
                </select>
              </div>
              <div class="col-md-4 mb-3">
                <label for="state">Nhà sản xuất</label>
                <select class="custom-select d-block w-100 manufacturer-select" name="ma_id" id="manufacturer" required="">
                  <option value="">Chọn...</option>` +
        getDataSelect('/api/v1/manufacturers').then(c => {
            c.data.forEach(function (d) {
                $(".manufacturer-select").append(`<option value="${d.maId}">${d.maName}</option>`)
            })
        })
        + `
                </select>
              </div>
            </div>
            
            <div class="row">
              <div class="col-md-4 mb-3">
                <label for="country">Chi tiết sản phẩm</label>
                <select class="custom-select d-block w-100 product-detail-select" id="product_detail_id" name="product_detail_id" required="">
                  <option value="">Chọn...</option>
                 ` +
        getDataSelect('/api/v1/product-details').then(c => {
            c.data.forEach(function (d) {
                $(".product-detail-select")
                    .append(`
                                    <option value="${d.productDetailId}">
                                    ${d.screenTechnology !== null ? d.screenTechnology : ``} 
                                    ${d.resolution !== null ? (`, ` + d.resolution) : ''}
                                    ${d.operatingSystem !== null ? (`, ` + d.operatingSystem) : ``} 
                                    ${d.mobileNetwork !== null ? (`, ` + d.mobileNetwork) : ``}  
                                    ${d.manufactureOfDate !== null ? (`, ` + d.manufactureOfDate) : ``}
                                    </option>`)
            })
        })
        + `
                </select>
              </div>
              <div class="col-md-4 mb-3">
                <label  class="form-label" for="customFile">Hình ảnh</label>
                <input name="imageLink" type="file" class="form-control" id="customFile" />
              </div>
            </div>            
            <hr class="mb-4">
          </form>
         </div>
         <div class="text-center">
           <button id="btn-product-save" class="inline-block btn btn-primary btn-lg" type="submit">Lưu </button>
           <button id="btn-product-cancel" class="inline-block btn btn-primary btn-lg" type="submit">Hủy </button>
         </div>
        </div>
      </div>
    </div>
        `
    )
}

function bindingSelect(data) {
    data.forEach(function (d) {
        `<option value="${d.caId}">${d.caName}</option>">`
    });
}

function bindingDataEmployee(data) {
    data = data.data;
    $('#datatable tbody').empty();
    data = data.content
    data.forEach(function (employee) {
        $('#datatable tbody').append(
            `<tr id="employee-${employee.empId}">
               <td class="td-firstName" id="firstName">${employee.firstName}</td>
               <td class="td-lastName" id="lastName">${employee.lastName}</td>
               <td class="td-userName">${employee.userName}</td>
               <td class="td-email id="email">${employee.email}</td>
               <td class="td-phone" id="phone">${employee.phone}</td>
               <td class="td-dateOfBirth" id="dateOfBirth">${employee.dateOfBirth}</td>
               <td class="td-gender" id="gender">${employee.gender}</td>
               <td class="td-address" id="dateOfBirth">${employee.address}</td>
               <td class="td-department" id="department">${employee.depName}</td>  
               <td class="td-roleName" id="roleName">${employee.roles}</td>  
               <td id="edit-employee-${employee.empId}">
                  <i style="color: blue;" class="fa fa-edit btn-edit" style="font-size:20px"></i>
               </td>
               <td id="remove-employee-${employee.empId}">
                  <i style="color: red;" class="fa fa-trash btn-delete" style="font-size:20px"></i>
               </td>
            </tr>`
        );

    });


}

