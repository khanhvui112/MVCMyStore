$(document).ready(function () {
    loadFooter();
})


function loadFooter() {
    const url = 'http://localhost:8080/Home/footer'
    fetch(url)
        .then((resp) => resp.json()) // Transform the data into json
        .then(function (data) {


            data.forEach(function (footer, index) {

                    $(".footer-middle .container .row#footer").append(`
                     <div class="col-md-3 col-sm-6">
                        <!--Column1-->
                        <div class="footer-pad">
                            <h4 id="${footer.footerId}">${footer.footerName}</h4>
                            <ul class="list-unstyled" id="ul-${footer.footerId}"}">
                            </ul>
                        </div>
                    </div>
                    `);
                    if(footer.footerDetails.footer_Id === footer.footer_Id){
                        footer.footerDetails.forEach(function (d2){
                            $("#ul-"+footer.footerId).append(`
                            <li><a id="${d2.footerDetailId}" href="${d2.footerDetailLink}">${d2.footerDetailName}</a></li>
                        `)
                        });
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