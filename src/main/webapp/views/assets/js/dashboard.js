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
    $("#menu_toggle").on('click', function (){
        $("#menu-left").slideToggle('slow');
    });

    $("#navbarDropdown1").on('click', function (){
        $("#notication").toggle();
    });
    $("#all-employee").on('click', function () {
        const url = 'http://localhost:8080/Home/admin-dashboard/admin/all-employee'
        $.ajax({
            url: url,
            type: 'GET',
            success: function (data) {
                let parsedData = JSON.parse(data);
                bindingDataEmployee(parsedData);
            }
        });
    });

})

function bindingDataEmployee(data) {
    showTable();
    $('#datatable tbody').empty();
    data.forEach(function (employee, index) {
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
               <td class="td-roleName" id="roleName">${employee.roleName}</td>  
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

function showTable() {
    $("#datatable").toggle();
}
