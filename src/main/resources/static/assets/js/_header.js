$(document).ready(function () {
    loadMenu();
})


function loadMenu() {
    const url = '/api/v1/menu/find-all'
    fetch(url)
        .then((resp) => resp.json()) // Transform the data into json
        .then(function (data) {

            const username = $("#username").text();
            data.forEach(function (menu, index) {
                if(menu.meName === 'Trang chủ'){
                    $(".site-logo a").attr('href',menu.meLink);
                }
                if(username !== '' && menu.meName === 'Đăng nhập'){
                    $('nav ul').append(
                        `<li id="useraccount">
                             <i style="color: aliceblue;" class="fa fa-user-circle"></i>
                             <a id="user-section" style=" margin-left: unset;" class="nav-link"></a>
                             <ul class="dropdown arrow-top">
                                    <li><a href="#team-section" class="nav-link">Thông tin tài khoản</a></li>
                                    <li><a href="#pricing-section" class="nav-link">Giỏ hàng</a></li>
                                    <li><a href="/home/logout" class="nav-link">Đăng xuất</a></li>
                                </ul>
                        </li>
                   `
                    );
                    $("#user-section").text(username);
                }
                else{
                    $('nav ul').append(
                        `<li><a href="${menu.meLink}" class="nav-link">${menu.meName}</a></li>
                   `
                    );
                    $("#user-section").text('');
                }

            });

        });
}

function menuDrop() {
    // <li className="has-children">
    //     <a href="#about-section" className="nav-link">About Us</a>
    //     <ul className="dropdown arrow-top">
    //         <li><a href="#team-section" className="nav-link">Team</a></li>
    //         <li><a href="#pricing-section" className="nav-link">Pricing</a></li>
    //         <li><a href="#faq-section" className="nav-link">FAQ</a></li>
    //         <li className="has-children">
    //             <a href="#">More Links</a>
    //             <ul className="dropdown">
    //                 <li><a href="#">Menu One</a></li>
    //                 <li><a href="#">Menu Two</a></li>
    //                 <li><a href="#">Menu Three</a></li>
    //             </ul>
    //         </li>
    //     </ul>
    // </li>
}
